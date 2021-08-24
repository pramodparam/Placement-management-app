package com.example.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StudentAdapter  extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    RegisterCompany registerCompany;
    private final ArrayList<Student> studentArrayList;

    public StudentAdapter(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }
    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView pname,pemail,pcomp,pcourse;
        public StudentViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            pname=itemView.findViewById(R.id.register1);
            pemail=itemView.findViewById(R.id.register2);
            pcomp=itemView.findViewById(R.id.register3);
            pcourse=itemView.findViewById(R.id.register4);
        }
    }

    @NonNull
    @NotNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.studentregisterdlist,parent,false);
        return new StudentViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StudentAdapter.StudentViewHolder holder, int position) {
        String name=studentArrayList.get(position).getName();
        holder.pname.setText("Student Name:"+" "+name);
        String em=studentArrayList.get(position).getEmail();
        holder.pemail.setText("Email:"+" "+em);
        String loc=studentArrayList.get(position).getCompname();
        holder.pcomp.setText("Company Name:"+" "+loc);
        String course=studentArrayList.get(position).getCourse();
        holder.pcourse.setText("Department:"+" "+course);

    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }
}


