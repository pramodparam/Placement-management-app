package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AdminRegister extends AppCompatActivity {
        TextInputLayout NameAdmin,email, mbNumber, password, cPassword;
        Button regBt,otp;
        FirebaseAuth fAuth;
        FirebaseFirestore db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            NameAdmin=findViewById(R.id.UsernameAdmin);
            setContentView(R.layout.activity_admin_register);
            email = findViewById(R.id.EmailAdmin);
            mbNumber = findViewById(R.id.AdminMnumber);
            password = findViewById(R.id.passwordA);
            otp=findViewById(R.id.otpAdmin);
            fAuth = FirebaseAuth.getInstance();
            db = FirebaseFirestore.getInstance();
            cPassword = findViewById(R.id.confirmPasswordA);
            regBt = findViewById(R.id.registerButtonAdmin);
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);

            otp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),OTPVerification.class));

                }
            });

            regBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Nameadmin= Objects.requireNonNull(NameAdmin.getEditText()).getText().toString().trim();
                    String emailId = Objects.requireNonNull(Objects.requireNonNull(email.getEditText()).getText().toString().trim());
                    String pass = Objects.requireNonNull(Objects.requireNonNull(password.getEditText()).getText().toString().trim());
                    String pass1 = Objects.requireNonNull(password.getEditText().getText().toString().trim());
                    String phoneNo = Objects.requireNonNull(Objects.requireNonNull(mbNumber.getEditText()).getText().toString().trim());

                    if(Nameadmin.isEmpty()){
                        NameAdmin.setErrorEnabled(true);
                        NameAdmin.setError("Please Enter Name");
                    }

                    if (emailId.isEmpty()) {
                        email.setError(" Email ID Required");
                        return;
                    } else if (!emailId.endsWith("@gmail.com")) {
                        email.setError(" Enter Valid Email ID");
                        return;
                    }

                    if (pass.isEmpty()) {
                        password.setError(" Password Required");
                        return;
                    }


                    if (pass1.isEmpty()) {
                        cPassword.setError(" Confirm Password Required");
                        return;
                    }

                    if (phoneNo.isEmpty()) {
                        mbNumber.setError("Valid Phone Number Required");
                        return;
                    }
                    if(phoneNo.length()>10){
                        mbNumber.setError("Enter Valid Number");
                        mbNumber.setErrorEnabled(true);
                    }
                    if(phoneNo.length()!=10){
                        mbNumber.setError("Enter Valid Number");
                        mbNumber.setErrorEnabled(true);
                    }
                    if (!pass1.equals(pass)) {
                        cPassword.setError("Password Do not Match");
                        return;
                    }
                    fAuth.createUserWithEmailAndPassword(emailId, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Intent x=new Intent(AdminRegister.this,AdminLogin.class);
                            startActivity(x);

                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(AdminRegister.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }

            });



        }
}