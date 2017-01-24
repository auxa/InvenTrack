package com.example.eoin.swengmenu;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static com.example.eoin.swengmenu.R.id.damButton11;
import static com.example.eoin.swengmenu.R.id.mButton;

public class DamageObject extends AppCompatActivity {

    int barNum;
    DatabaseHelper db;
    Button barcodeBut;
    Button home;
    String barcodeContent12;
    Button butDam11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage_object);
        db = new DatabaseHelper(getApplicationContext());

        barcodeBut =(Button) findViewById(R.id.button2);
        final Activity activity = this;
        barcodeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        butDam11 = (Button) findViewById(R.id.damButton11);
        butDam11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean temp = db.damageObject(barcodeContent12);
                temp=true;
                if (temp == true) {
                    Toast.makeText(DamageObject.this, "Damaged", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DamageObject.this, "Not Damaged", Toast.LENGTH_SHORT).show();
                }

                Intent toy = new Intent(DamageObject.this, Menu.class);
                startActivity(toy);
            }


        });

        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent toy = new Intent(DamageObject.this, Menu.class);
                startActivity(toy);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result.getContents() == null){
            Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
        }
        else {
            barcodeContent12 = result.getContents();
            Toast.makeText(this, barcodeContent12, Toast.LENGTH_LONG).show();

        }
    }
}
