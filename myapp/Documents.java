package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Documents extends AppCompatActivity {
    EditText choose;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        choose=findViewById(R.id.chooseDocumnet);
        upload=findViewById(R.id.uploadButton);
    }
}