package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.databinding.ActivityMainBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class PhoneNumberVerification extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private FirebaseAuth fAuth;
    private Button phoneContinueBtn,submit;
    EditText pnumber;
    TextView resendCode;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
private static final String TAG="MAIN_TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pnumber=findViewById(R.id.pNumber);
        resendCode=findViewById(R.id.resendCodeTv);
        submit=findViewById(R.id.verify);
        phoneContinueBtn=findViewById(R.id.phoneContinueBtn);

        fAuth= FirebaseAuth.getInstance();
        mCallbacks =new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
            }
        };
     phoneContinueBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String phone=pnumber.getText().toString().trim();
             if(TextUtils.isEmpty(phone)){
                 Toast.makeText(PhoneNumberVerification.this,"Please Enter",Toast.LENGTH_SHORT).show();

             }
          else{
              resendVerificationCode(phone);
             }
         }
     });

     resendCode.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

         }
     });
     submit.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String code=submit.getText().toString().trim();
             if(TextUtils.isEmpty(code)){
                 Toast.makeText(PhoneNumberVerification.this,"Please Enter",Toast.LENGTH_SHORT).show();
             }
             else{
                // verifyPhoneNumberWithCode(mVerificationId,code);
             }
         }
     });
    }

    private void verifyPhoneNumberWithCode(String mVerificationId, String code) {
    }

    private void startPhoneNumber(String phone) {
    }

    private void resendVerificationCode(String phone) {
    }

}