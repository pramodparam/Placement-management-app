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

public class StudentList extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView studentrec;
    ArrayList<Student> studentArrayList;
   StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        studentArrayList=new ArrayList<>();
        setUpRecyclerView();
        setUpFirebase();
        loadDataFromFirebase();
    }
    private void setUpFirebase() {
        db=FirebaseFirestore.getInstance();


    }

    private void setUpRecyclerView() {
        studentrec= findViewById(R.id.StudentRecycle);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        studentrec.setLayoutManager(layoutManager);
        studentrec.setHasFixedSize(true);
        studentrec.setItemAnimator(new DefaultItemAnimator());

    }

    private void loadDataFromFirebase() {

        if(studentArrayList.size()>0)
            studentArrayList.clear();
        db.collection("Register Company").orderBy("Person Name").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot query: Objects.requireNonNull(task.getResult())){
                    Student user= new Student(query.getString("Person Name"),query.getString("Person Email"),
                            query.getString("Comp Name"),query.getString("Course"));
                    studentArrayList.add(user);
                }
                studentAdapter=new StudentAdapter(studentArrayList);
                studentrec.setAdapter(studentAdapter);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(StudentList.this,"problem...."+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });





    }


}