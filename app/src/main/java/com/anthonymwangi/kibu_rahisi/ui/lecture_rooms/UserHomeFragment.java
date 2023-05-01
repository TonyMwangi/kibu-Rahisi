package com.anthonymwangi.kibu_rahisi.ui.lecture_rooms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.anthonymwangi.kibu_rahisi.Category;
import com.anthonymwangi.kibu_rahisi.Laboratory;
import com.anthonymwangi.kibu_rahisi.Lecture_room;
import com.anthonymwangi.kibu_rahisi.Office;
import com.anthonymwangi.kibu_rahisi.R;
import com.anthonymwangi.kibu_rahisi.Washroom;

public class UserHomeFragment extends Fragment {
    Context context;
    ImageView imageView5;
    View view; CardView cardLectureRoom,cardLaboratory, cardOffice, cardWashRoom;

    public UserHomeFragment() {
        // Required empty public constructor
    }

   public UserHomeFragment(Context context){
        this.context = context;

   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_user_home, container, false);

        imageView5 = view.findViewById(R.id.imageView5);

        cardLectureRoom = view.findViewById(R.id.userCardLectureRoom);
        cardLaboratory = view.findViewById(R.id.userCardLaboratory);
        cardOffice = view.findViewById(R.id.userCardOffice);
        cardWashRoom = view.findViewById(R.id.userCardWashRoom);

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Lecture_room.class));
            }
        });

        cardOffice.setOnClickListener(v->{startActivity(new Intent(context, Office.class));});

        cardLectureRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Category.class));
            }
        });
        cardLaboratory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Laboratory.class));
            }
        });
        cardWashRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(context, Washroom.class));
            }
        });

        return view;
    }
}