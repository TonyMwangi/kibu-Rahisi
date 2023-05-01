package com.anthonymwangi.kibu_rahisi.ui.lecture_rooms;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anthonymwangi.kibu_rahisi.R;
import com.anthonymwangi.kibu_rahisi.model.Classes;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;


public class NotificationFragment extends Fragment {
Context context;
   View view;
    FirebaseFirestore db;
    RecyclerView recyclerView;
    ClassesAdapter classesAdapter;
    ArrayList<Classes> classes;
    public NotificationFragment() {
        // Required empty public constructor
    }
public NotificationFragment(Context context){
        this.context = context;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notification, container, false);

        db = FirebaseFirestore.getInstance();
        recyclerView = view.findViewById(R.id.notification_recyclerView);

        classes = new ArrayList<>();
        classesAdapter = new ClassesAdapter(context, classes);
        recyclerView.setAdapter(classesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);




        db.collection("ScheduleClass").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for(DocumentChange dc : value.getDocumentChanges()){

                    if(dc.getType() == DocumentChange.Type.ADDED || dc.getType() == DocumentChange.Type.MODIFIED) {
                        classes.add(dc.getDocument().toObject(Classes.class));

                    }
                }
                classesAdapter = new ClassesAdapter(context, classes);
                recyclerView.setAdapter(classesAdapter);
                classesAdapter.notifyDataSetChanged();
            }
        });



        return view;
    }
}