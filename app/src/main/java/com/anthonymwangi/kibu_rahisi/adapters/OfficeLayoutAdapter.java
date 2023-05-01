package com.anthonymwangi.kibu_rahisi.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anthonymwangi.kibu_rahisi.R;
import com.anthonymwangi.kibu_rahisi.ScheduleClass;
import com.anthonymwangi.kibu_rahisi.model.OfficeLayoutModel;

import java.util.ArrayList;

public class OfficeLayoutAdapter extends RecyclerView.Adapter<OfficeLayoutAdapter.MyViewHolder> {

    Context context;
    ArrayList<OfficeLayoutModel> OfficeLayoutArrayList;
    String mycontext, userLevel;

    public OfficeLayoutAdapter(Context context, ArrayList<OfficeLayoutModel> OfficeLayoutArrayList, String mycontext, String userLevel) {
        this.context = context;
        this.mycontext = mycontext;
        this.userLevel = userLevel;
        this.OfficeLayoutArrayList = OfficeLayoutArrayList;
    }
    public OfficeLayoutAdapter(Context context, ArrayList<OfficeLayoutModel> OfficeLayoutArrayList) {
        this.context = context;
        this.OfficeLayoutArrayList = OfficeLayoutArrayList;
    }

    @NonNull
    @Override
    public OfficeLayoutAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.officelayout_recyclerview_row
                ,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OfficeLayoutAdapter.MyViewHolder holder, int position) {
        OfficeLayoutModel officeLayoutModel = OfficeLayoutArrayList.get(position);

        holder.name.setText(officeLayoutModel.getName());
        holder.description.setText(officeLayoutModel.getDescription());
        holder.status.setText(officeLayoutModel.getStatus());
        holder.capacity.setText(officeLayoutModel.getCapacity());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mycontext.equalsIgnoreCase("laboratory") && userLevel.equalsIgnoreCase("admin")){
                    if(holder.status.getText().toString().equalsIgnoreCase("free")){
                        context.startActivity(new Intent(context, ScheduleClass.class));
                    }
                    else if(holder.status.getText().toString().equalsIgnoreCase("broken")){
                        Toast.makeText(context, "You cannot schedule class here. Laboratory is broken"
                                , Toast.LENGTH_LONG).show();
                    } else if(holder.status.getText().toString().equalsIgnoreCase("occupied")){
                        Toast.makeText(context, "You cannot schedule class here. Laboratory is occupied"
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
                } else if(mycontext.equalsIgnoreCase("washroom") && userLevel.equalsIgnoreCase("admin")){
                    if(holder.status.getText().toString().equalsIgnoreCase("usable")){
                        Toast.makeText(context, " washroom is usable"
                                , Toast.LENGTH_LONG).show();
                    } else if(holder.status.getText().toString().equalsIgnoreCase("broken")){
                        Toast.makeText(context, " Washroom is broken and therefore not in use"
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
        return OfficeLayoutArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, description, status, capacity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView);
            description = itemView.findViewById(R.id.descriptionTextView);
            status = itemView.findViewById(R.id.statusTextView);
            capacity = itemView.findViewById(R.id.capacityTextView);
        }
    }
}
