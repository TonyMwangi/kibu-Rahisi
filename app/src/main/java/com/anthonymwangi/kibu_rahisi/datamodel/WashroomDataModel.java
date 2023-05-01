package com.anthonymwangi.kibu_rahisi.datamodel;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anthonymwangi.kibu_rahisi.R;
import com.anthonymwangi.kibu_rahisi.adapters.OfficeLayoutAdapter;
import com.anthonymwangi.kibu_rahisi.model.LectureRooms;
import com.anthonymwangi.kibu_rahisi.model.OfficeLayoutModel;
import com.anthonymwangi.kibu_rahisi.ui.lecture_rooms.LectureRoomsAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class WashroomDataModel extends AppCompatActivity {

    RecyclerView recyclerView;
    OfficeLayoutAdapter adapter;
    ArrayList<OfficeLayoutModel> OfficeLayoutArrayList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userLevel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washroom_data_model);

        String floor = getIntent().getStringExtra("floor");

        recyclerView = findViewById(R.id.recyclerViewwashrooms);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userLevelMethod();
        OfficeLayoutArrayList = new ArrayList<>();
        adapter = new OfficeLayoutAdapter(this, OfficeLayoutArrayList);


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
            db.collection("WashroomsGroundFloor").orderBy("name", Query.Direction.ASCENDING).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                OfficeLayoutArrayList.add(documentSnapshot.toObject(OfficeLayoutModel.class));
                            }
                            adapter = new OfficeLayoutAdapter(WashroomDataModel.this, OfficeLayoutArrayList, "washroom", userLevel);
                            recyclerView.setAdapter(adapter);
                        }
                    });
        }
        if(floor.equalsIgnoreCase("first floor")) {
            db.collection("WashroomsGroundFloor").orderBy("name", Query.Direction.ASCENDING).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                OfficeLayoutArrayList.add(documentSnapshot.toObject(OfficeLayoutModel.class));
                            }
                            adapter = new OfficeLayoutAdapter(WashroomDataModel.this, OfficeLayoutArrayList, "washroom", userLevel);
                            recyclerView.setAdapter(adapter);
                        }
                    });
        }
        if(floor.equalsIgnoreCase("second floor")) {
            db.collection("WashroomsGroundFloor").orderBy("name", Query.Direction.ASCENDING).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                OfficeLayoutArrayList.add(documentSnapshot.toObject(OfficeLayoutModel.class));
                            }
                            adapter = new OfficeLayoutAdapter(WashroomDataModel.this, OfficeLayoutArrayList, "washroom", userLevel);
                            recyclerView.setAdapter(adapter);
                        }
                    });
        }
        if(floor.equalsIgnoreCase("third floor")) {
            db.collection("WashroomsGroundFloor").orderBy("name", Query.Direction.ASCENDING).get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                OfficeLayoutArrayList.add(documentSnapshot.toObject(OfficeLayoutModel.class));
                            }
                            adapter = new OfficeLayoutAdapter(WashroomDataModel.this, OfficeLayoutArrayList, "washrooms", userLevel);
                            recyclerView.setAdapter(adapter);
                        }
                    });
        }
    }
}
