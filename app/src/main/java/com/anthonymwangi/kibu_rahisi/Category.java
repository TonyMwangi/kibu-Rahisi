package com.anthonymwangi.kibu_rahisi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.anthonymwangi.kibu_rahisi.ui.lecture_rooms.LectureRoomsActivity;

public class Category extends AppCompatActivity {
    CardView cardGroundfloor, cardFirstFloor, cardsecond, cardThirdFloor;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_category);
        cardGroundfloor = findViewById(R.id.cardGroundfloor);
        cardFirstFloor = findViewById(R.id.cardFirstFloor);
        cardsecond = findViewById(R.id.cardSecondFloor);
        cardThirdFloor = findViewById(R.id.cardThirdFloor);

        cardGroundfloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LectureRoomsActivity.class).putExtra("floor", "ground floor"));
            }
        });
        cardFirstFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LectureRoomsActivity.class).putExtra("floor", "first floor"));
            }
        });
        cardsecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LectureRoomsActivity.class).putExtra("floor", "second floor"));
            }
        });
        cardThirdFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LectureRoomsActivity.class).putExtra("floor", "third floor"));
            }
        });
    }
}