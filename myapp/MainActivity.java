package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText et,password,Id;
    Button loginButton;
    TextView tv;
    FirebaseAuth fAuth;
    FirebaseFirestore db;
    String type;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


                if (et.getText().toString().isEmpty() || password.getText().toString().isEmpty() || Id.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (Id.getText().toString().equals("StudentJNNCE")) {
                        progressDialog.setMessage("Logging In.. please wait");
                        progressDialog.show();
                        fAuth.signInWithEmailAndPassword(et.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                progressDialog.show();
                                Intent x = new Intent(MainActivity.this, StudentHome.class);
                                startActivity(x);
                                progressDialog.dismiss();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Error!" + e.getMessage(), Toast.LENGTH_SHORT).show();


                            }
                        });
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Enter Valid Student Code", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

tv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(MainActivity.this,RegisterScreen.class);
        startActivity(i);
    }
});

    }


}