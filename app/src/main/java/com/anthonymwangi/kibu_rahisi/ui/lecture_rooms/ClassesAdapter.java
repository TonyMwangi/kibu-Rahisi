package com.anthonymwangi.kibu_rahisi.ui.lecture_rooms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anthonymwangi.kibu_rahisi.R;
import com.anthonymwangi.kibu_rahisi.model.Classes;

import java.util.ArrayList;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.MyViewHolder> {
    Context context;
    ArrayList<Classes> classes;

    public ClassesAdapter(Context context, ArrayList<Classes> classes) {
        this.context = context;
        this.classes = classes;
    }

    @NonNull
    @Override
    public ClassesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.notification_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesAdapter.MyViewHolder holder, int position) {
        holder.course.setText(classes.get(position).getClassName());
        holder.startTime.setText(classes.get(position).getStartTime());
        holder.endTime.setText(classes.get(position).getEndTime());
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView course, startTime, endTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.coursecode_notification);
            startTime = itemView.findViewById(R.id.startTime_notification);
            endTime = itemView.findViewById(R.id.endTime_notification);
        }
    }
}
