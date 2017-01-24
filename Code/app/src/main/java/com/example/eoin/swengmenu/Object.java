package com.example.eoin.swengmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.widget.Toast;
import android.content.Intent;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Object extends AppCompatActivity {

    Button mButton, barcodeBut;
    Button home;

    EditText name;
    EditText individual;
    EditText projects;

    String myName;
    String myIndividual;
    String myProjects;
    String barcodeContent;

    int idNumber;
    int projectId =0;
    int barNum;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);
        db = new DatabaseHelper((getApplicationContext()));

        mButton = (Button) findViewById(R.id.mButton2);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                name   = (EditText)findViewById(R.id.editText);
                myName = name.getText().toString();
                individual   = (EditText)findViewById(R.id.editText4);
                myIndividual = individual.getText().toString();
                idNumber = Integer.parseInt(myIndividual);
                projects   = (EditText)findViewById(R.id.editText5);
                myProjects = projects.getText().toString();
                if(!myProjects.isEmpty()) { projectId= Integer.parseInt(myProjects);}

                if(myProjects==null || myProjects.equals("") || myProjects.equals(null)){
                    db.addObject(barcodeContent,idNumber, myName, -1);
                }
                else{
                    db.addObject(barcodeContent, idNumber, myName,  projectId);
                }


                //need to have an extra page for damaging the object



                Intent toy = new Intent(Object.this, Menu.class);
                startActivity(toy);
            }
        });
        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(Object.this, Menu.class);
                startActivity(toy);
            }
        });

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result.getContents() == null){
            Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
        }
        else {
            barcodeContent = result.getContents();
            Toast.makeText(this, barcodeContent, Toast.LENGTH_LONG).show();
        }
    }
}