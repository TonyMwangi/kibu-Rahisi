package com.anthonymwangi.kibu_rahisi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Classroom_FirstFloor extends AppCompatActivity {

    Button groundFloorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_first_floor);
        groundFloorButton = findViewById(R.id.groundFloorButton);

        groundFloorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Classrom_GroundFloor.class));
            }
        });

    }
}