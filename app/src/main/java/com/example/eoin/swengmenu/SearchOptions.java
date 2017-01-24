package com.example.eoin.swengmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchOptions extends AppCompatActivity {
    public Button but1;
    public Button but2;
    public Button but3;
    public Button but4;
    public Button but5;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_options);

        but1 = (Button) findViewById(R.id.but1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy = new Intent(SearchOptions.this, SearchByBar.class);
                startActivity(toy);
            }
        });

        but2 = (Button) findViewById(R.id.but2);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy2 = new Intent(SearchOptions.this, SearchByDate.class);
                startActivity(toy2);
            }
        });
        but3 = (Button) findViewById(R.id.but3);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy3 = new Intent(SearchOptions.this, SearchByPandD.class);
                startActivity(toy3);
            }
        });
        but4 = (Button) findViewById(R.id.but4);
        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy4 = new Intent(SearchOptions.this, SearchIdOpts.class);
                startActivity(toy4);
            }
        });
        but5 = (Button) findViewById(R.id.but5);
        but5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy5 = new Intent(SearchOptions.this, SearchByProjectId.class);
                startActivity(toy5);
            }
        });
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(SearchOptions.this, Menu.class);
                startActivity(toy);
            }
        });
    }
}
