package com.example.eoin.swengmenu;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Individual extends AppCompatActivity {

    Button mButton;
    Button home;

    EditText name;
    String myName;
    DatabaseHelper db;
    long temp;
    TextView stringTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual);
        db= new DatabaseHelper(getApplicationContext());

        mButton = (Button) findViewById(R.id.mButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                name   = (EditText)findViewById(R.id.editText7);
                myName = name.getText().toString();

                if(myName.isEmpty()){

                    Toast.makeText(Individual.this, "No name entered", Toast.LENGTH_LONG).show();

                    Intent toy = new Intent(Individual.this, Menu.class);
                    startActivity(toy);

                }
                else {
                    IndividualFunc newInd = new IndividualFunc(myName);
                    temp = db.createIndiviual(newInd);
                    newInd.setInd_id((int) temp);
                    Toast.makeText(Individual.this, "ID number: " + temp, Toast.LENGTH_LONG).show();
                    Intent toy = new Intent(Individual.this, Menu.class);
                    startActivity(toy);
                }

            }
        });
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(Individual.this, Menu.class);
                startActivity(toy);
            }
        });
    }
}
