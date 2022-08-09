package com.jerucloud.epursuit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class registerrecreater extends AppCompatActivity {
    EditText editTeuxtRecName,editTextRecGender,editTextCRN,editTextRecPhone,editTextRecEmail,editTextRecPassword
    ,editTextBio;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registerrecreater);
        Button SULoginBtn =findViewById(R.id.SULoginBtn);
        Button NextBptn= findViewById(R.id.NextBptn);
        editTeuxtRecName=findViewById(R.id.editTeuxtRecName);
        editTextRecGender=findViewById(R.id.editTextRecGender);
        editTextCRN=findViewById(R.id.editTextCRN);
        editTextRecPhone=findViewById(R.id.editTextRecPhone);
        editTextRecEmail=findViewById(R.id.editTextRecEmail);
        editTextRecPassword=findViewById(R.id.editTextRecPassword);
        editTextBio=findViewById(R.id.editTextBio);
        firebaseAuth= FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("recreator");
        NextBptn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.createUserWithEmailAndPassword(editTextRecEmail.getText().toString().trim(),editTextRecPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            firebaseUser=task.getResult().getUser();
                            DatabaseReference newUser=databaseReference.child(firebaseUser.getUid());
                            newUser.child("RecName").setValue(editTeuxtRecName.getText().toString().trim());
                            newUser.child("RecGender").setValue(editTextRecGender.getText().toString().trim());
                            newUser.child("CRN").setValue(editTextCRN.getText().toString().trim());
                            newUser.child("RecPhone").setValue(editTextRecPhone.getText().toString().trim());
                            newUser.child("RecEmail").setValue(editTextRecEmail.getText().toString().trim());
                            newUser.child("RecPassword").setValue(editTextRecPassword.getText().toString().trim());
                            newUser.child("editTextBio").setValue(editTextBio.getText().toString().trim());
                            Toast.makeText(registerrecreater.this,"done",Toast.LENGTH_LONG).show();
                            Intent toSigin = new Intent(registerrecreater.this,home_recreater.class);
                            startActivity(toSigin);
                            finish();
                        }else{
                            Toast.makeText(registerrecreater.this,"Error",Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });
        SULoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(registerrecreater.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}