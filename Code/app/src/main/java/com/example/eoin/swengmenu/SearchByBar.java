package com.example.eoin.swengmenu;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

public class SearchByBar extends AppCompatActivity {
    Button barcodeBut;
    Button home;

    DatabaseHelper db;
    String barcodeContent;
    TextView stringTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_bar);
        stringTextView = (TextView) findViewById(R.id.resultView);

        db = new DatabaseHelper(getApplicationContext());

        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent toy = new Intent(SearchByBar.this, Menu.class);
                startActivity(toy);
            }
        });

        barcodeBut =(Button) findViewById(R.id.barBut);
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




                //Intent toy = new Intent(SearchByBar.this, SearchOptions.class);
                //startActivity(toy);
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
            ObjectFunc objects = db.searchBarcode(barcodeContent);
            if( objects != null){
                if(objects.getBarcode()!=null){
                    stringTextView.setText(stringTextView.getText() + objects.toString());
                }else{
                    stringTextView.setText("The item corresponding to this barcode is damaged!");
                }

            }
            else{
                stringTextView.setText("No matches found!");
            }
        }
    }
}
