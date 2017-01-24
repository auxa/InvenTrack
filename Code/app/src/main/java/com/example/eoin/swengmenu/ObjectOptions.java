package com.example.eoin.swengmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ObjectOptions extends AppCompatActivity {
    public Button but5;
    public Button but6;
    public Button but7;
    Button home;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_object_options);

            but5 = (Button) findViewById(R.id.but5);
            but5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent toy = new Intent(ObjectOptions.this, Object.class);
                    startActivity(toy);
                }
            });

            but6 = (Button) findViewById(R.id.but6);
            but6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent toy2 = new Intent(ObjectOptions.this, DamageObject.class);
                    startActivity(toy2);
                }
            });
            but7 = (Button) findViewById(R.id.but7);
            but7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent toy3 = new Intent(ObjectOptions.this, DisplayDamaged.class);
                    startActivity(toy3);
                }
            });
            home = (Button) findViewById(R.id.home);
            home.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    Intent toy = new Intent(ObjectOptions.this, Menu.class);
                    startActivity(toy);
                }
            });
        }


}
