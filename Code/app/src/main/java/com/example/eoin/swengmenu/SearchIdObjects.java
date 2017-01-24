package com.example.eoin.swengmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class SearchIdObjects extends AppCompatActivity {
    Button mButton;
    Button home;

    EditText ID;

    String myID;
    int temp;
    DatabaseHelper db;
    TextView stringTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_id_objects);

        stringTextView = (TextView) findViewById(R.id.resultView);

        db = new DatabaseHelper(getApplicationContext());

        mButton = (Button) findViewById(R.id.jenson);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ID = (EditText) findViewById(R.id.editText14);
                myID = ID.getText().toString();
                temp = Integer.parseInt(myID);

                List<ObjectFunc> objects = db.getObject(temp);

                if(!objects.isEmpty()) {
                    for (int i = 0; i < objects.size(); i++) {
                        stringTextView.setText(stringTextView.getText() + objects.get(i).toString());
                    }
                }
                else{
                    stringTextView.setText("No matches found!");
                }
            }
        });
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(SearchIdObjects.this, Menu.class);
                startActivity(toy);
            }
        });
    }
}