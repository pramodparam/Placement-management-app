package com.example.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DisplayStudentAdapter extends RecyclerView.Adapter<DisplayStudentAdapter.DisplayViewHolder> {

DisplayPlacementDetail displayPlacementDetail;
    ArrayList<DisplayStudent> displayStudentArrayList;


    public DisplayStudentAdapter(ArrayList<DisplayStudent> displayStudentArrayList) {
        this.displayStudentArrayList = displayStudentArrayList;
    }

    public class DisplayViewHolder extends RecyclerView.ViewHolder {
        TextView pdname1,pdusn1,pdemail1,pdcomp1,pdcaddress,pdcourse1;

        public DisplayViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            pdname1=itemView.findViewById(R.id.tv1);
            pdusn1=itemView.findViewById(R.id.tv2);
            pdemail1=itemView.findViewById(R.id.tv3);
            pdcomp1=itemView.findViewById(R.id.tv4);
            pdcaddress=itemView.findViewById(R.id.tv5);
            pdcourse1=itemView.findViewById(R.id.tv6);

        }
    }

    @NonNull
    @NotNull
    @Override
    public DisplayStudentAdapter.DisplayViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.displayplacedstudents,parent,false);
        return new DisplayStudentAdapter.DisplayViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DisplayStudentAdapter.DisplayViewHolder holder, int position) {

                String name = displayStudentArrayList.get(position).getPdName();
        holder.pdname1.setText("Student Name:" + " " + name);
        String date = displayStudentArrayList.get(position).getPdUsn();
        holder.pdusn1.setText("Student USN:" + " " + date);
        String loc = displayStudentArrayList.get(position).getPdEmail();
        holder.pdemail1.setText("Email Address:" + " " + loc);
        String course = displayStudentArrayList.get(position).getPdCompName();
        holder.pdcomp1.setText("Company Name:" + " " + course);
        String compad = displayStudentArrayList.get(position).getPdCompAddress();
        holder.pdcaddress.setText("Company Address:" + " " + compad);
        String dep = displayStudentArrayList.get(position).getPdDepart();
        holder.pdcourse1.setText("Student Course:" + " " + dep);

    }

    @Override
    public int getItemCount() {
        return displayStudentArrayList.size();
    }

}
