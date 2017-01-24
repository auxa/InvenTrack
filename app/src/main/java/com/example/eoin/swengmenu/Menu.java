package com.example.eoin.swengmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class  Menu extends AppCompatActivity {

    public Button but1;
    public Button but2;
    public Button but3;
    public Button but4;

    public void init() {
        but1 = (Button) findViewById(R.id.but1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy = new Intent(Menu.this, ObjectOptions.class);
                startActivity(toy);
            }
        });

        but2 = (Button) findViewById(R.id.but2);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy2 = new Intent(Menu.this, Project.class);
                startActivity(toy2);
            }
        });

        but3 = (Button) findViewById(R.id.but3);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy3 = new Intent(Menu.this, IndivOptions.class);
                startActivity(toy3);
            }
        });

        but4 = (Button) findViewById(R.id.but4);
        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy4 = new Intent(Menu.this, SearchOptions.class);
                startActivity(toy4);
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
    }
}