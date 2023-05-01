package com.anthonymwangi.kibu_rahisi.ui.lecture_rooms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anthonymwangi.kibu_rahisi.Office;
import com.anthonymwangi.kibu_rahisi.R;
import com.anthonymwangi.kibu_rahisi.ScheduleClass;
import com.anthonymwangi.kibu_rahisi.model.LectureRooms;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class LectureRoomsAdapter extends RecyclerView.Adapter<LectureRoomsAdapter.MyViewHolder> {

    Context context;
    ArrayList<LectureRooms> lectureRoomsArrayList;
    String mycontext, userLevel, collection, className;


    public LectureRoomsAdapter(Context context, ArrayList<LectureRooms> lectureRoomsArrayList, String mycontext, String userLevel) {
        this.context = context;
        this.mycontext = mycontext;
        this.userLevel = userLevel;
        this.lectureRoomsArrayList = lectureRoomsArrayList;
    }

    public LectureRoomsAdapter(Context context, ArrayList<LectureRooms> lectureRoomsArrayList, String mycontext, String userLevel, String collection) {
        this.context = context;
        this.lectureRoomsArrayList = lectureRoomsArrayList;
        this.mycontext = mycontext;
        this.userLevel = userLevel;
        this.collection = collection;
    }

    public LectureRoomsAdapter(Context context, ArrayList<LectureRooms> lectureRoomsArrayList) {
        this.context = context;
        this.lectureRoomsArrayList = lectureRoomsArrayList;
    }


    @NonNull
    @Override
    public LectureRoomsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.lectureroom_recyclerview_row
                ,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LectureRoomsAdapter.MyViewHolder holder, int position) {

       LectureRooms lectureRooms = lectureRoomsArrayList.get(position);

       holder.name.setText(lectureRooms.getName());
       holder.status.setText(lectureRooms.getStatus());
       holder.capacity.setText(lectureRooms.getCapacity());

       className = holder.name.getText().toString();

       if(holder.status.getText().toString().equalsIgnoreCase("occupied")){
           holder.itemView.findViewById(R.id.dividerView).setBackgroundColor(Color.parseColor("#FF0000"));
       }else if(holder.status.getText().toString().equalsIgnoreCase("broken")){
           holder.itemView.findViewById(R.id.dividerView).setBackgroundColor(Color.parseColor("#FFFF00"));
       }else{
           holder.itemView.findViewById(R.id.dividerView).setBackgroundColor(Color.parseColor("#00FF00"));
       }
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @SuppressLint("ResourceAsColor")
           @Override
           public void onClick(View view) {
               if(mycontext.equalsIgnoreCase("lecture") && userLevel.equalsIgnoreCase("admin")){
                   if(holder.status.getText().toString().equalsIgnoreCase("free")){

                       Intent intent = new Intent(context, ScheduleClass.class );
                       Bundle extras = new Bundle();
                       extras.putString("className", className);
                       extras.putString("collection", collection);
                       intent.putExtras(extras);
                       context.startActivity(intent);
                   }
                   else if(holder.status.getText().toString().equalsIgnoreCase("broken")){
                       Toast.makeText(context, "You cannot schedule class here. Class is broken"
                               , Toast.LENGTH_LONG).show();
                   } else if(holder.status.getText().toString().equalsIgnoreCase("occupied")){
                       Toast.makeText(context, "You cannot schedule class here. Class is occupied"
                               , Toast.LENGTH_LONG).show();
                   }else {
                       Toast.makeText(context, "status is unrecognized. contact admin through 0710925530"
                               , Toast.LENGTH_LONG).show();
                   }
               } else if(mycontext.equalsIgnoreCase("office") && userLevel.equalsIgnoreCase("admin")){
                   if(holder.status.getText().toString().equalsIgnoreCase("active")){
                       Toast.makeText(context, " Office is active. No admin roles defined yet"
                               , Toast.LENGTH_LONG).show();
                   } else if(holder.status.getText().toString().equalsIgnoreCase("closed")){
                       Toast.makeText(context, " Office is closed. No admin roles defined yet"
                               , Toast.LENGTH_LONG).show();
                   }else {
                       Toast.makeText(context, "status is unrecognized. contact admin through 0710925530"
                               , Toast.LENGTH_LONG).show();
                   }
               } else {
                       Toast.makeText(context,
                               "We are sorry Users are only allowed to view. No other role is assigned by admin"
                           , Toast.LENGTH_LONG).show();
               }
           }
       });
    }
    @Override
    public int getItemCount() {
        return lectureRoomsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, status, capacity;
        View vw;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            status = itemView.findViewById(R.id.statusTextView);
            capacity = itemView.findViewById(R.id.capacityTextView);
            vw = itemView.findViewById(R.id.dividerView);
        }
    }
}
