package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminLogin extends AppCompatActivity {
    EditText et,password,Id;
    Button loginButton;
    TextView tv;
    FirebaseAuth fAuth;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        et=findViewById(R.id.et);
        password=findViewById(R.id.password);
        loginButton=findViewById(R.id.bt);
        tv=findViewById(R.id.tv);
        Id=findViewById(R.id.ID);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        fAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(et.getText().toString().isEmpty()||password.getText().toString().isEmpty()&&Id.getText().toString().isEmpty()){
                    Toast.makeText(AdminLogin.this,"please enter all fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(Id.getText().toString().equals("AdminJNNCE")){
                        progressDialog.setMessage("Logging In.. please wait");
                    progressDialog.show();
                        fAuth.signInWithEmailAndPassword(et.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent x=new Intent(AdminLogin.this,AdminHome.class);
                                startActivity(x);
                progressDialog.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AdminLogin.this,"Error!"+ e.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                    else{
                        Toast.makeText(AdminLogin.this,"Enter Valid Admin Code",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminLogin.this,RegisterScreen.class);
                startActivity(i);
            }
        });

    }


}