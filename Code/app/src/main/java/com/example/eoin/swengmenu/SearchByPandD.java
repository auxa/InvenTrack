package com.example.eoin.swengmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SearchByPandD extends AppCompatActivity {

    Button sButton;
    Button home;

    EditText project;
    EditText person;
    EditText date;
    DatabaseHelper db;
    String myDate;
    String myProject;
    String myPerson;
    TextView stringTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_pand_d);
        stringTextView = (TextView) findViewById(R.id.textView3);


        db = new DatabaseHelper(getApplicationContext());




        sButton = (Button) findViewById(R.id.jenson);
        sButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                date = (EditText) findViewById(R.id.editText12);
                myDate = date.getText().toString();

                SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
                long milliseconds = 0;
                try {
                    Date d = f.parse(myDate);
                    milliseconds = d.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                person = (EditText) findViewById(R.id.editText99);
                myPerson = person.getText().toString();

                project= (EditText) findViewById(R.id.editText11);
                myProject = project.getText().toString();

                List<ObjectFunc> references = new LinkedList<ObjectFunc>();
                if(myProject.isEmpty()){

                    int personID = Integer.parseInt(myPerson);
                    references =db.searchPersonAndDate(personID, milliseconds);

                }else{

                    int projectID = Integer.parseInt(myProject);
                    references = db.searchProjectAndDate(projectID, milliseconds);
                }

                if(!references.isEmpty()) {
                    for (int i = 0; i < references.size(); i++) {
                        stringTextView.setText(stringTextView.getText() + references.get(i).toString());
                    }
                }
                else{
                    stringTextView.setText("No matches found!");
                }

               // Intent toy = new Intent(SearchByPandD.this, SearchOptions.class);
               // startActivity(toy);

            }
        });

        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(SearchByPandD.this, Menu.class);
                startActivity(toy);
            }
        });

    }
}
