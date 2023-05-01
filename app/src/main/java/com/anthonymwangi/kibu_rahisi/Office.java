package com.anthonymwangi.kibu_rahisi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.anthonymwangi.kibu_rahisi.datamodel.OfficeDataModel;

public class Office extends AppCompatActivity {
    CardView cardGroundFloor, cardFirstFloor, cardSecondFloor, cardThirdFloor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_category);
        cardGroundFloor = findViewById(R.id.cardGroundfloor);
        cardFirstFloor = findViewById(R.id.cardFirstFloor);
        cardSecondFloor = findViewById(R.id.cardSecondFloor);
        cardThirdFloor = findViewById(R.id.cardThirdFloor);

        cardGroundFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OfficeDataModel.class).putExtra("floor", "ground floor"));
            }
        });
        cardFirstFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OfficeDataModel.class).putExtra("floor", "first floor"));
            }
        });
        cardSecondFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OfficeDataModel.class).putExtra("floor", "second floor"));
            }
        });
        cardThirdFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OfficeDataModel.class).putExtra("floor", "third floor"));
            }
        });
    }
}