package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class addCompanies extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    Button b1;
    FirebaseFirestore db;
    FirebaseAuth fAuth;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        ed1=findViewById(R.id.companyName);
        db=FirebaseFirestore.getInstance();
        ed2=findViewById(R.id.DateButton);
        ed3=findViewById(R.id.LocationButton);
        ed4=findViewById(R.id.departmentButton);
        b1=findViewById(R.id.companyAddButton);
        Spinner sp =  findViewById(R.id.dep);

        List<String> list = new ArrayList<>();
        list.add("Select Department");
        list.add("CSE");
        list.add("ECE");
        list.add("EEE");
        list.add("TCE");
        list.add("ISE");
        list.add("ME");
        list.add("Civil");

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

UpdateCompany();
    }
        });


    }

    public void UpdateCompany() {

        if (ed1.getText().toString().isEmpty() && ed2.getText().toString().isEmpty()
                &&ed3.getText().toString().isEmpty()&&ed4.getText().toString().isEmpty()) {

            Toast.makeText(addCompanies.this,"Please Enter the Details",Toast.LENGTH_SHORT).show();

        } else {
            Map<String, Object> items = new HashMap<>();
            items.put("Company Name", ed1.getText().toString().trim());
            items.put("Req date", ed2.getText().toString().trim());
            items.put("Location", ed3.getText().toString().trim());
            items.put("Department", ed4.getText().toString().trim());
            items.put("spinner", Arrays.toString(sp.getTouchables().toArray()).trim());

            db.collection("Company Details").add(items).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                    ed4.setText("");
                    Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();

                    Intent x = new Intent(addCompanies.this, AdminHome.class);
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

}
