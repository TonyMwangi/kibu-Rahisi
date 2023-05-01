package com.anthonymwangi.kibu_rahisi;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.anthonymwangi.kibu_rahisi.model.LectureRooms;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ScheduleClass extends AppCompatActivity {

    EditText className, programme, yearOfStudy, courseCode, startTime, endTime;
    Button scheduleClassButton, test;
    FirebaseFirestore db;
    String classn, pro, yof, ccode, stime, etime, adapterClassName, adapterCollection, status;
    private  static final  String TAG = "ScheduleClass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_class);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        adapterClassName = extras.getString("className");
        adapterCollection = extras.getString("collection");

        className = findViewById(R.id.className);
        programme = findViewById(R.id.programme);
        yearOfStudy = findViewById(R.id.yearOfStudy);
        courseCode = findViewById(R.id.courseCode);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);

//        test = findViewById(R.id.scheduletest);
//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: " + adapterClassName.toString());
//                Log.d(TAG, "onClick: " + adapterCollection.toString());
//            }
//        });

//          <Button
//        android:id="@+id/scheduletest"
//        android:layout_width="0dp"
//        android:layout_height="wrap_content"
//        android:layout_marginStart="16dp"
//        android:layout_marginTop="16dp"
//        android:layout_marginEnd="16dp"
//        android:background="@drawable/rounded_button_selector"
//        android:text="Test"
//        android:textColor="@color/white"
//        app:layout_constraintEnd_toEndOf="parent"
//        app:layout_constraintStart_toStartOf="parent"
//        app:layout_constraintTop_toBottomOf="@id/scheduleClassButton" />

        disableEditText(startTime, endTime);
        scheduleClassButton = findViewById(R.id.scheduleClassButton);

        db = FirebaseFirestore.getInstance();

        findViewById(R.id.endTimePicker).setOnClickListener(v->{pickTime("end time");});
        findViewById(R.id.startTimePicker).setOnClickListener(v->{pickTime("start time");});

        scheduleClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput() == true) {
                    saveInput();
                }

            }
        });
    }

    private void disableEditText(EditText startTime, EditText endTime) {
        startTime.setFocusable(false);
        startTime.setCursorVisible(false);
        endTime.setFocusable(false);
        endTime.setCursorVisible(false);
    }

    private void saveInput() {
        Map<String, Object> hashMap = new HashMap<>();

        hashMap.put("className", classn);
        hashMap.put("programme", pro);
        hashMap.put("yearOfStudy", yof);
        hashMap.put("courseCode", ccode);
        hashMap.put("startTime", stime);
        hashMap.put("endTime", etime);

        db.collection("ScheduleClass").add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                //updateClassStatus();
                Toast.makeText(ScheduleClass.this, "Details saved successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ScheduleClass.this, "Failed to save data"+e.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
            }
        });

    }

//    private void updateClassStatus() {
//        db.collection("" + adapterCollection.toString()).whereEqualTo("name", adapterClassName)
//                .add(new LectureRooms()).add
//    }

    private boolean checkInput() {
        if(className.getText().toString().isEmpty()){
            className.setError("Please enter a class name");
            return false;
        }else if(programme.getText().toString().isEmpty()){
            programme.setError("Please enter a programme");
            return false;
        }else if(yearOfStudy.getText().toString().isEmpty()){
            yearOfStudy.setError("Please enter a year of study");
            return false;
        }else if(courseCode.getText().toString().isEmpty()){
            courseCode.setError("Please enter a course code");
            return false;
        }else if(startTime.getText().toString().isEmpty()){
            startTime.setError("Please enter a start time");
            return false;
        }else if(endTime.getText().toString().isEmpty()){
            endTime.setError("Please enter an end time");
            return false;
        }else{
            //classn, pro, yof, ccode, stime, etime
            classn = className.getText().toString();
            pro = programme.getText().toString();
            yof = yearOfStudy.getText().toString();
            ccode = courseCode.getText().toString();
            stime = startTime.getText().toString();
            etime = endTime.getText().toString();
            return true;
        }
    }

    @SuppressLint("DefaultLocale")
    private void pickTime(String e) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePicker = new TimePickerDialog(ScheduleClass.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                if(e.equalsIgnoreCase("end time")) {
                    if(timePicker.getHour()<12){
                        endTime.setText(timePicker.getHour() + ":" + timePicker.getMinute()+" AM");
                    }else {
                        endTime.setText(timePicker.getHour()-12 + ":" + timePicker.getMinute()+" PM");
                    }
                }else{
                    if(timePicker.getHour()<12){
                        startTime.setText(timePicker.getHour() + ":" + timePicker.getMinute()+" AM");
                    }else {
                        startTime.setText(timePicker.getHour()-12 + ":" + timePicker.getMinute()+" PM");
                    }
                }
            }
        }, hour, minute, true);
        timePicker.show();
    }
}