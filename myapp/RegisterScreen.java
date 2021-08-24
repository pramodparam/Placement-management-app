package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.ErrorManager;

public class RegisterScreen extends AppCompatActivity {

    TextInputLayout email, mbNumber, password, cPassword,name;
    Button regBt,otp;
    TextView verify;
    FirebaseAuth fAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        name=findViewById(R.id.StudentName);
        email = findViewById(R.id.EmailStudent);
        mbNumber = findViewById(R.id.studentPNo);
        password = findViewById(R.id.passwordS);
        otp=findViewById(R.id.otpStudent);
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        cPassword = findViewById(R.id.confirmPasswordS);
        regBt = findViewById(R.id.registerButtonS);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNo = Objects.requireNonNull(Objects.requireNonNull(mbNumber.getEditText()).getText().toString().trim());
                if(phoneNo.length()!=10){
                    mbNumber.setError("Enter Valid Number");
                    mbNumber.setErrorEnabled(true);
                }
                else{
                Intent c=new Intent(getApplicationContext(),OTPVerification.class);
                c.putExtra("phoneNo",phoneNo);
                startActivity(c);
                }

            }
        });

        regBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name= Objects.requireNonNull(name.getEditText()).getText().toString().trim();
                String emailId = Objects.requireNonNull(Objects.requireNonNull(email.getEditText()).getText().toString().trim());
                String pass = Objects.requireNonNull(Objects.requireNonNull(password.getEditText()).getText().toString().trim());
                String pass1 = Objects.requireNonNull(Objects.requireNonNull(cPassword.getEditText()).getText().toString().trim());
                String phoneNo = Objects.requireNonNull(Objects.requireNonNull(mbNumber.getEditText()).getText().toString().trim());
                if(Name.isEmpty()){
                    name.setErrorEnabled(true);
                    name.setError("Enter Name please");
                }
                if (emailId.isEmpty()) {
                    email.setErrorEnabled(true);
                    email.setError(" Email ID Required");
                    return;
                } else if (!emailId.endsWith("@gmail.com")) {
                    email.setError(" Enter Valid Email ID");
                    email.setErrorEnabled(true);
                    return;
                }

                if (pass.isEmpty()) {
                    password.setError(" Password Required");
                    password.setErrorEnabled(true);
                    return;
                }


                if (pass1.isEmpty()) {
                    cPassword.setErrorEnabled(true);
                    cPassword.setError(" Confirm Password Required");
                    return;
                }

                if (phoneNo.isEmpty()) {
                    mbNumber.setError("Valid Phone Number Required");
                    mbNumber.setErrorEnabled(true);
                    return;
                }
                if(phoneNo.length()>10){
                    mbNumber.setError("Enter Valid Number");
                    mbNumber.setErrorEnabled(true);
                    mbNumber.setErrorEnabled(true);
                }
                if(phoneNo.length()!=10){
                    mbNumber.setError("Enter Valid Number");
                    mbNumber.setErrorEnabled(true);
                    mbNumber.setErrorEnabled(true);
                }
                if (!pass1.equals(pass)) {
                    cPassword.setError("Password Do not Match");
                    cPassword.setErrorEnabled(true);
                    return;
                }

                fAuth.createUserWithEmailAndPassword(emailId, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                       Intent x=new Intent(RegisterScreen.this,MainActivity.class);
                       startActivity(x);

                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(RegisterScreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }

        });


    }
}
