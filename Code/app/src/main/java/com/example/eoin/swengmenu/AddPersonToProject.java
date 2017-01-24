package com.example.eoin.swengmenu;

import android.content.Intent;
import android.net.ParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddPersonToProject extends AppCompatActivity {
    Button mButton;
    Button home;

    EditText name;
    String myName;
    int temp;

    EditText proj;
    String myProj;
    int temp2;
    DatabaseHelper db;
    TextView stringTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person_to_project);
        db= new DatabaseHelper(getApplicationContext());
        stringTextView = (TextView) findViewById(R.id.textView4);

        mButton = (Button) findViewById(R.id.mButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                name   = (EditText)findViewById(R.id.editText8);
                myName = name.getText().toString();

               proj = (EditText)findViewById(R.id.editText9);
               myProj = proj.getText().toString();

                
                if(myName.isEmpty() && myProj.isEmpty()){
                    stringTextView.setText("No person or project entered, try again");
                }
                else if(myName.isEmpty()){
                    stringTextView.setText("No person entered, try again");
                }
                else if(myProj.isEmpty()) {
                    stringTextView.setText("No project entered, try again");
                }
                else {
                    temp = Integer.parseInt(myName);
                    temp2 = Integer.parseInt(myProj);
                    db.assignWork(temp, temp2);


                    Intent toy = new Intent(AddPersonToProject.this, Menu.class);
                    startActivity(toy);
                }
            }
        });
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(AddPersonToProject.this, Menu.class);
                startActivity(toy);
            }
        });
    }
}
