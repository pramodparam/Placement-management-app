package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.transition.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.CompaniesViewHolder> {

    CompaniesList companiesList;

    public CompaniesAdapter(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    private ArrayList<User> userArrayList;

    public class CompaniesViewHolder extends RecyclerView.ViewHolder {
        TextView cname,cdate,cloc,course;
        Button breg;
        public CompaniesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            cname=itemView.findViewById(R.id.company1);
            cdate=itemView.findViewById(R.id.company2);
            cloc=itemView.findViewById(R.id.company3);
            course=itemView.findViewById(R.id.company4);
            breg=itemView.findViewById(R.id.registerforComp);
        }
    }


    @NonNull
    @NotNull
    @Override
    public CompaniesAdapter.CompaniesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.companieslist,parent,false);
        return new CompaniesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CompaniesAdapter.CompaniesViewHolder holder, int position) {
        String name = userArrayList.get(position).getCoName();
        holder.cname.setText("Company Name:" + " " + name);
        String date = userArrayList.get(position).getrDate();
        holder.cdate.setText("Recqruitment date:" + " " + date);
        String loc = userArrayList.get(position).getLocation();
        holder.cloc.setText("Location:" + " " + loc);
        String course = userArrayList.get(position).getDepartment();
        holder.course.setText("Department:" + " " + course);
        holder.breg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent press=new Intent(holder.breg.getContext(),RegisterCompany.class);
                holder.breg.getContext().startActivity(press);
            }
        });


    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    /*public CompaniesAdapter(CompaniesList companiesList, ArrayList<User> userArrayList) {
        this.companiesList = companiesList;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public CompaniesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        LayoutInflater v=LayoutInflater.from(companiesList.getBaseContext());
        View view=v.inflate(R.layout.activity_companies_list,parent,false);
        return new CompaniesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CompaniesViewHolder holder, int position) {

    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull CompaniesViewHolder holder, int position) {
        holder.cName.setText(userArrayList.get(position).getCoName());
        holder.cdate.setText(userArrayList.get(position).getrDate());
        holder.clocation.setText(userArrayList.get(position).getLocation());
        holder.cCourse.setText(userArrayList.get(position).getDepartment());

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }*/
}
