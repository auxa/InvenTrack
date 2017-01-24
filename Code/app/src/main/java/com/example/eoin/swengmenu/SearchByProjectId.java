package com.example.eoin.swengmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchByProjectId extends AppCompatActivity {
    Button mButton;
    Button home;


    EditText ID;

    String myID;
    DatabaseHelper db;
    TextView stringTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_project_id);

        stringTextView = (TextView) findViewById(R.id.textView5);

        db = new DatabaseHelper(getApplicationContext());

        mButton = (Button) findViewById(R.id.jenson);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                ID = (EditText) findViewById(R.id.editText10);
                myID = ID.getText().toString();

                if(myID.isEmpty()){
                    stringTextView.setText("Nothing entered, try again");
                }
                else {

                    ProjectFunc objects = db.getProject(myID);
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

                Intent toy = new Intent(SearchByProjectId.this, Menu.class);
                startActivity(toy);
            }
        });
    }
}

