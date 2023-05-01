package com.anthonymwangi.kibu_rahisi.datamodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.anthonymwangi.kibu_rahisi.R;
import com.anthonymwangi.kibu_rahisi.model.LectureRooms;
import com.anthonymwangi.kibu_rahisi.ui.lecture_rooms.LectureRoomsAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class OfficeDataModel extends AppCompatActivity {

    RecyclerView recyclerView;
    LectureRoomsAdapter adapter;
    ArrayList<LectureRooms> lectureRoomsArrayList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_data_model);

        String floor = getIntent().getStringExtra("floor");

        recyclerView = findViewById(R.id.recyclerViewoffices);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        userLevelMethod();
        lectureRoomsArrayList = new ArrayList<>();
        adapter = new LectureRoomsAdapter(this, lectureRoomsArrayList);


        recyclerView.setAdapter(adapter);
        EventChangeListener(floor);

    }

    private void userLevelMethod() {
        String id;
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();
        id = user.getUid();
        DocumentReference df = db.collection("Users").document(id);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess" + documentSnapshot.getData());
                //identify the user access level
                if(documentSnapshot.getString("isAdmin") != null){
                    userLevel = "admin";
                }
                if(documentSnapshot.getString("isUser") != null){
                    userLevel = "user";
                }
            }
        });
    }

    private void EventChangeListener(String floor) {
        if(floor.equalsIgnoreCase("ground floor")) {
            db.collection("OfficesGroundFloor").orderBy("name", Query.Direction.ASCENDING).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                lectureRoomsArrayList.add(documentSnapshot.toObject(LectureRooms.class));
                            }
                            adapter = new LectureRoomsAdapter(OfficeDataModel.this, lectureRoomsArrayList, "office", userLevel);
                            recyclerView.setAdapter(adapter);
                        }
                    });
        }
        if(floor.equalsIgnoreCase("first floor")) {
            db.collection("OfficesFirstFloor").orderBy("name", Query.Direction.ASCENDING).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                lectureRoomsArrayList.add(documentSnapshot.toObject(LectureRooms.class));
                            }
                            adapter = new LectureRoomsAdapter(OfficeDataModel.this, lectureRoomsArrayList, "office", userLevel);
                            recyclerView.setAdapter(adapter);
                        }
                    });
        }
        if(floor.equalsIgnoreCase("second floor")) {
            db.collection("OfficesSecondFloor").orderBy("name", Query.Direction.ASCENDING).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                lectureRoomsArrayList.add(documentSnapshot.toObject(LectureRooms.class));
                            }
                            adapter = new LectureRoomsAdapter(OfficeDataModel.this, lectureRoomsArrayList, "office", userLevel);
                            recyclerView.setAdapter(adapter);
                        }
                    });
        }
        if(floor.equalsIgnoreCase("third floor")) {
            db.collection("OfficesThirdFloor").orderBy("name", Query.Direction.ASCENDING).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                lectureRoomsArrayList.add(documentSnapshot.toObject(LectureRooms.class));
                            }
                            adapter = new LectureRoomsAdapter(OfficeDataModel.this, lectureRoomsArrayList, "office", userLevel);
                            recyclerView.setAdapter(adapter);
                        }
                    });
        }
    }
}