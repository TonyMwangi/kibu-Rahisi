package com.anthonymwangi.kibu_rahisi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.anthonymwangi.kibu_rahisi.datamodel.LaboratoryDataModel;
import com.anthonymwangi.kibu_rahisi.datamodel.WashroomDataModel;

public class Washroom extends AppCompatActivity {

    CardView cardGroundFloor, cardFirstFloor, cardSecondFloor, cardThirdFloor;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        cardGroundFloor = findViewById(R.id.cardGroundfloor);
        cardFirstFloor = findViewById(R.id.cardFirstFloor);
        cardSecondFloor = findViewById(R.id.cardSecondFloor);
        cardThirdFloor = findViewById(R.id.cardThirdFloor);

        cardGroundFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(Washroom.this);
                progressDialog.setMessage("Wait");
                progressDialog.setTitle("Loading...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10000);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }).start();

                startActivity(new Intent(getApplicationContext(), WashroomDataModel.class).putExtra("floor", "ground floor"));
            }
        });
        cardFirstFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), WashroomDataModel.class).putExtra("floor", "first floor"));
            }
        });
        cardSecondFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), WashroomDataModel.class).putExtra("floor", "second floor"));
            }
        });
        cardThirdFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), WashroomDataModel.class).putExtra("floor", "third floor"));
            }
        });
    }
}