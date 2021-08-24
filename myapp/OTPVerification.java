package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OTPVerification extends AppCompatActivity {
    EditText number;
    Button enter;
    String  verificationCodeFromSystem;
    FirebaseAuth fAuth;
    FirebaseFirestore db;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        number = findViewById(R.id.number);
        enter = findViewById(R.id.enter);
        pb = findViewById(R.id.progressBar);
        pb= new ProgressBar(this);
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        pb.setVisibility(View.GONE);
        String num=getIntent().getStringExtra("phoneNo");
        sendVerificationCodeToUser(num);
enter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String code=number.getText().toString();
        if(number.getText().toString().isEmpty()){
            number.setError("PleaseEnter OTP");
            number.requestFocus();
            return;
        }
        if (number.getText().toString().length()<6){
            number.setError("Enter Valid OTP");
            number.requestFocus();
            return;
        }
        pb.setVisibility(View.VISIBLE);
        verifyCode(code);

    }
});


    }

    private void sendVerificationCodeToUser(String num) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(fAuth)
                        .setPhoneNumber("+91"+ num)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)// Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationCodeFromSystem=s;
        }

        @Override
        public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode();
         if(code!=null){
             pb.setVisibility(View.VISIBLE);
             verifyCode(code);
         }


        }

        @Override
        public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    };
    private void verifyCode(String codeByUser){

        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationCodeFromSystem,codeByUser);
        signInTheUserByCredential(credential);
    }



    private void signInTheUserByCredential(PhoneAuthCredential credential) {

        fAuth.signInWithCredential(credential).addOnCompleteListener(OTPVerification.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent n=new Intent(getApplicationContext(),RegisterScreen.class);
                    n.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(n);
                    Toast.makeText(OTPVerification.this,"Phone NumberVerified",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(OTPVerification.this, Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

}