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
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class UpdatePlacementDetail extends AppCompatActivity {
    EditText pd1,pd2,pd3,pd4,pd5,pd6;
    Button buttonPD;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_placement_detail);
        pd1=findViewById(R.id.StName);
        pd2=findViewById(R.id.StUSN);
        pd3=findViewById(R.id.emailPD);
        pd4=findViewById(R.id.compNamePD);
        pd5=findViewById(R.id.companyPD);
        pd6=findViewById(R.id.coursePD);
        db=FirebaseFirestore.getInstance();
        buttonPD=findViewById(R.id.UpdatePD);

        buttonPD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDetail();
            }
        });

    }

    private void UpdateDetail() {
        Map<String, Object> items = new HashMap<>();
        items.put("PD name", pd1.getText().toString().trim());
        items.put("PD USN", pd2.getText().toString().trim());
        items.put("PD email", pd3.getText().toString().trim());
        items.put("PD companyname", pd4.getText().toString().trim());
        items.put("PD addrress", pd5.getText().toString().trim());
        items.put("PD course", pd6.getText().toString().trim());

    db.collection("Update Placement Detail").add(items).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
        @Override
        public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {

            pd1.setText("");
            pd2.setText("");
            pd3.setText("");
            pd4.setText("");
            pd5.setText("");
            pd6.setText("");

            Intent x = new Intent(UpdatePlacementDetail.this, StudentHome.class);
            startActivity(x);
            Toast.makeText(UpdatePlacementDetail.this,"Updated Successfully",Toast.LENGTH_SHORT).show();
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull @NotNull Exception e) {
            Toast.makeText(UpdatePlacementDetail.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    });

    }

    }
