package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentHome extends AppCompatActivity {

    Button bs1,bs2,bs3,logStudent;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        bs1=findViewById(R.id.coButton);
        bs2=findViewById(R.id.viewDocument);
        bs3=findViewById(R.id.updatepd);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        logStudent=findViewById(R.id.logoutStudent);

        logStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });


        bs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doc=new Intent(StudentHome.this,CompaniesList.class);
                startActivity(doc);

            }
        });

        bs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doct=new Intent(StudentHome.this,UpdatePlacementDetail.class);
                startActivity(doct);
                }
        });


    }
}