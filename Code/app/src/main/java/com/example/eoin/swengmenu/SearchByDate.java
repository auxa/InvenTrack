package com.example.eoin.swengmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class SearchByDate extends AppCompatActivity {

    Button mButton;
    Button home;

    EditText date;

    String myDate;
    DatabaseHelper db;
    TextView stringTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_date);
        stringTextView = (TextView) findViewById(R.id.resultView);

        db = new DatabaseHelper(getApplicationContext());

        mButton = (Button) findViewById(R.id.jenson);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                date = (EditText) findViewById(R.id.searchView2);
                myDate = date.getText().toString();

                if(myDate.isEmpty()){
                    Toast.makeText(SearchByDate.this, "No date entered", Toast.LENGTH_LONG).show();

                    Intent toy = new Intent(SearchByDate.this, Menu.class);
                    startActivity(toy);
                }
                else {
                    SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
                    long milliseconds = 0;
                    try {
                        Date d = f.parse(myDate);
                        milliseconds = d.getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                        stringTextView.setText("Incorrect Date Format Entered");
                    }

                    List<ObjectFunc> objects = db.getObjectsDue(milliseconds);
                    if (!objects.isEmpty()) {
                        for (int i = 0; i < objects.size(); i++) {
                            stringTextView.setText(stringTextView.getText() + objects.get(i).toString());
                        }
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

                Intent toy = new Intent(SearchByDate.this, Menu.class);
                startActivity(toy);
            }
        });
    }
}
