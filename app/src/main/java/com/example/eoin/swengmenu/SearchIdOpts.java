package com.example.eoin.swengmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchIdOpts extends AppCompatActivity {

    public Button but1;
    public Button but2;
    public Button but3;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_id_opts);

        but1 = (Button) findViewById(R.id.but1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy = new Intent(SearchIdOpts.this, SearchIdProjects.class);
                startActivity(toy);
            }
        });

        but2 = (Button) findViewById(R.id.but2);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy2 = new Intent(SearchIdOpts.this, SearchIdObjects.class);
                startActivity(toy2);
            }
        });
        but3 = (Button) findViewById(R.id.but3);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toy3 = new Intent(SearchIdOpts.this, SearchIdIndividual.class);
                startActivity(toy3);
            }
        });
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(SearchIdOpts.this, Menu.class);
                startActivity(toy);
            }
        });
    }
}
