package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Screen2 extends AppCompatActivity {
    ImageView profilePic;
    public Uri fileUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        profilePic= findViewById(R.id.profilepic);
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });
    }

    private void choosePicture() {
        Intent intent=new Intent();
        intent.setType("documents/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 &&resultCode==RESULT_OK && data!=null && data.getData()!=null){
            fileUri=data.getData();
            profilePic.setImageURI(fileUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Uploading Document");
        pd.show();

        // Create a reference to "mountains.jpg"
        final String randomKey= UUID.randomUUID().toString();
        StorageReference mountainsRef = storageReference.child("documents/"+randomKey);
        mountainsRef.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                Snackbar.make(findViewById(android.R.id.content),"Document Uploaded.",Snackbar.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(),"Failed to Upload",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull @NotNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent=(100.00*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                pd.setMessage("Percentage:"+(int)progressPercent+"%");
            }
        });

// Create a reference to 'images/mountains.jpg'
       // StorageReference mountainImagesRef = storageReference.child("images/mountains.jpg");

// While the file names are the same, the references point to different files
      //  mountainsRef.getName().equals(mountainImagesRef.getName());    // true
      //  mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false


try {
    final File localfile=File.createTempFile("documents",".pdf");
    storageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

            Toast.makeText(getApplicationContext(),"Document retrieved",Toast.LENGTH_SHORT).show();
            Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
            ((ImageView)findViewById(R.id.profilepic)).setImageBitmap(bitmap);
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull @NotNull Exception e) {

        }
    });

} catch (IOException e) {
    e.printStackTrace();
}
    }
}