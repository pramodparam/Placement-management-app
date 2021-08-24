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
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class DisplayPlacementDetail extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView displayRecyclerView;
    ArrayList<DisplayStudent>  displayStudentArrayList;
    DisplayStudentAdapter displayStudentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_placement_detail);
        displayStudentArrayList=new ArrayList<>();
        setUpRecyclerView();
        setUpFirebase();
        loadDataFromFirebase();
    }

    private void setUpFirebase() {
        db=FirebaseFirestore.getInstance();
    }

    private void setUpRecyclerView() {
        displayRecyclerView= findViewById(R.id.recyclerViewPD);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        displayRecyclerView.setLayoutManager(layoutManager);
        displayRecyclerView.setHasFixedSize(true);
        displayRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void loadDataFromFirebase() {
        if(displayStudentArrayList.size()>0)
            displayStudentArrayList.clear();
        db.collection("Update Placement Detail").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot query: Objects.requireNonNull(task.getResult())){
                    DisplayStudent displayStudent=new DisplayStudent(query.getString("PD name"),query.getString("PD USN"),
                            query.getString("PD email"),query.getString("PD companyname")
                            ,query.getString("PD addrress"),query.getString("PD course"));
               displayStudentArrayList.add(displayStudent);
                }
                displayStudentAdapter=new DisplayStudentAdapter(displayStudentArrayList);
                displayRecyclerView.setAdapter(displayStudentAdapter);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(DisplayPlacementDetail.this,"problem...."+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });









    }
}