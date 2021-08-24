package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class CompaniesList extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView companyListRecycle;
    ArrayList<User> userArrayList;
    CompaniesAdapter companiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies_list);
        userArrayList=new ArrayList<>();
        setUpRecyclerView();
        setUpFirebase();
        loadDataFromFirebase();
    }

    public void setUpFirebase() {
        db=FirebaseFirestore.getInstance();
    }

    public  void setUpRecyclerView() {
        companyListRecycle= findViewById(R.id.companyListRecycle);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
       companyListRecycle.setLayoutManager(layoutManager);
        companyListRecycle.setHasFixedSize(true);
        companyListRecycle.setItemAnimator(new DefaultItemAnimator());

    }

    public void loadDataFromFirebase() {

        if(userArrayList.size()>0)
            userArrayList.clear();
        db.collection("Company Details").orderBy("Company Name").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot query: Objects.requireNonNull(task.getResult())){
                    User user=new User(query.getString("Company Name"),query.getString("Req date"),
                            query.getString("Location"),query.getString("Department"));
                    userArrayList.add(user);
                }
                companiesAdapter=new CompaniesAdapter(userArrayList);
                companyListRecycle.setAdapter(companiesAdapter);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(CompaniesList.this,"problem...."+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}