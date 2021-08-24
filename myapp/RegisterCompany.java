package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class RegisterCompany extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
Button button;
FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company);

        ed1=findViewById(R.id.pName);
        ed2=findViewById(R.id.Email);
        ed3=findViewById(R.id.comp);
        ed4=findViewById(R.id.course);
        db=FirebaseFirestore.getInstance();
        button=findViewById(R.id.studentReg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateRegistration();
            }
        });



    }

    private void UpdateRegistration() {

        if(ed1.getText().toString().isEmpty()){
            ed1.setError("Please Enter Name");
        }
        if(ed2.getText().toString().isEmpty()){
            ed2.setError("Please Enter Email");
        }
        if(!ed2.getText().toString().endsWith("@gmail.com")){
            ed2.setError("Please Enter Valid Email");
        }
        if(ed3.getText().toString().isEmpty()){
            ed3.setError("Please Enter Company Name");

        }
        if(ed4.getText().toString().isEmpty()){
            ed4.setError("Please Enter Department");
        }






            Map<String, Object> items = new HashMap<>();
            items.put("Person Name", ed1.getText().toString().trim());
            items.put("Person Email", ed2.getText().toString().trim());
            items.put("Comp Name", ed3.getText().toString().trim());
            items.put("Course", ed4.getText().toString().trim());

            db.collection("Register Company").add(items).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                    ed4.setText("");
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                    Intent x = new Intent(RegisterCompany.this, StudentHome.class);
                    startActivity(x);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }