package com.example.eoin.swengmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Project extends AppCompatActivity {

    public Button mButton;
    Button home;
    EditText name;
    String myName;
    EditText date;
    String myDate;
    EditText individuals;
    String myIndividuals;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        db = new DatabaseHelper(getApplicationContext());

        mButton = (Button) findViewById(R.id.mButton3);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                name   = (EditText)findViewById(R.id.editText2);
                myName = name.getText().toString();
                if(myName.isEmpty()){

                    Toast.makeText(Project.this, "No name entered", Toast.LENGTH_LONG).show();

                    Intent toy = new Intent(Project.this, Menu.class);
                    startActivity(toy);

                }
                date   = (EditText)findViewById(R.id.editText3);
                myDate = date.getText().toString();
                if(myDate.isEmpty()){
                    Toast.makeText(Project.this, "No date entered", Toast.LENGTH_LONG).show();

                    Intent toy = new Intent(Project.this, Menu.class);
                    startActivity(toy);
                }
                char[] tmp = myDate.toCharArray();
                if(tmp.length==11 && !(Character.isDigit(tmp[0]) && Character.isDigit(tmp[1])
                        && tmp[2] == '-' && Character.isLetter(tmp[3]) && Character.isLetter(tmp[4]) &&
                        Character.isLetter(tmp[5]) && tmp[6] == '-' && Character.isDigit(tmp[7]) && Character.isDigit(tmp[8]) &&
                        Character.isDigit(tmp[9]) && Character.isDigit(tmp[10]))){
                    tmp[3] = Character.toUpperCase(tmp[3]);
                    tmp[4] = Character.toUpperCase(tmp[4]);
                    tmp[5] = Character.toUpperCase(tmp[5]);


                    Toast.makeText(Project.this, "Date format invalid", Toast.LENGTH_LONG).show();

                    Intent toy = new Intent(Project.this, Menu.class);
                    startActivity(toy);
                }
                SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
                long milliseconds = 0;
                try {
                    Date d = f.parse(myDate);
                    milliseconds = d.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                individuals   = (EditText)findViewById(R.id.editText6);
                myIndividuals = individuals.getText().toString();

                if(milliseconds==0){
                    //Some invalid date exception handling
                }else {
                    ProjectFunc newProject = new ProjectFunc(myName, milliseconds);
                    long projectId =  db.createProject(newProject);
                   // newProject.setProject_id(projectId);

                    Toast.makeText(Project.this, "Project ID: " + projectId, Toast.LENGTH_LONG).show();

                    //Used for assign people to a project when a project is to be created
                    Scanner scan = new Scanner(myIndividuals);
                    scan.useDelimiter(", ");

                    while (scan.hasNext()) {
                        String holder = scan.next();
                        int place = Integer.parseInt(holder);

                        long a = db.assignWork(place, (int) projectId);
                    }
                }
                Intent toy = new Intent(Project.this, Menu.class);
                startActivity(toy);
            }
        });

        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(Project.this, Menu.class);
                startActivity(toy);
            }
        });
    }
}