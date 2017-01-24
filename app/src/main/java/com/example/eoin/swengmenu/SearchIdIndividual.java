package com.example.eoin.swengmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SearchIdIndividual extends AppCompatActivity {
    Button mButton;
    Button home;

    EditText iD;

    String myID;
    DatabaseHelper db;
    TextView stringTextView;
    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_id_individual);

        stringTextView = (TextView) findViewById(R.id.resultView);

        db = new DatabaseHelper(getApplicationContext());

        mButton = (Button) findViewById(R.id.jenson);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                iD = (EditText) findViewById(R.id.editText15);
                myID = iD.getText().toString();

                if(myID.isEmpty()){
                    stringTextView.setText("No ID entered");
                }else{
                    temp = Integer.parseInt(myID);
                    IndividualFunc objects = db.getIndividual(temp);
                    if (objects != null) {
                        stringTextView.setText(stringTextView.getText() + objects.toString());

                    } else {
                        stringTextView.setText("No matches found!");
                    }
                }

            }
        });
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(SearchIdIndividual.this, Menu.class);
                startActivity(toy);
            }
        });
    }
}