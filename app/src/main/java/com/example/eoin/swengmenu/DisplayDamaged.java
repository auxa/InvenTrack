package com.example.eoin.swengmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class DisplayDamaged extends AppCompatActivity {
    TextView stringTextView;
    DatabaseHelper db;
    Button home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_damaged);

        stringTextView = (TextView) findViewById(R.id.resultView);
        db = new DatabaseHelper(getApplicationContext());


        List<ObjectFunc> objects = db.getDamaged();
        if(!objects.isEmpty()) {
            for (int i = 0; i < objects.size(); i++) {
                stringTextView.setText(stringTextView.getText() + objects.get(i).toString());
            }
        }
        else{
            stringTextView.setText("No matches found!");
        }

        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(DisplayDamaged.this, Menu.class);
                startActivity(toy);
            }
        });
    }
}
