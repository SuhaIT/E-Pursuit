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


public class registerjobseek2 extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    String firstnamee,
            lastname,
            Gender,
            Date,
            Phone,email,
            Password,editTextMaijorr,ediitTextQualificationr,editTextFiieldofExperiencer,editTextGraiduatrionYear,editiTextYearrsofExperience,
            editTextPreiferrredJobTitle,editTextNatrioinality,editTextPrrieferredTime,editTexitPrreferredSalary;
    EditText editTextMaijor,ediitTextQualification,editTextFiieldofExperience,editTextGraiduationYear,editiTextYearsofExperience,
            editTextPreiferredJobTitle,editTextNatioinality,editTextPrieferredTime,editTexitPreferredSalary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registerjobseek2);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
            } else {

                firstnamee= extras.getString("firstname");
                lastname= extras.getString("lastname");
                Gender= extras.getString("Gender");
                Date= extras.getString("Date");
                Phone= extras.getString("Phone");
                Password= extras.getString("Password");
                email= extras.getString("email");
            }
        } else {

        }
        Button NextpBtn =findViewById(R.id.NextpBtn);
        editTextMaijor=findViewById(R.id.editTextMaijor);
        ediitTextQualification=findViewById(R.id.ediitTextQualification);
        editTextFiieldofExperience=findViewById(R.id.editTextFiieldofExperience);
        editTextGraiduationYear=findViewById(R.id.editTextGraiduationYear);
        editiTextYearsofExperience=findViewById(R.id.editiTextYearsofExperience);
        editTextPreiferredJobTitle=findViewById(R.id.editTextPreiferredJobTitle);
        editTextNatioinality=findViewById(R.id.editTextNatioinality);
        editTextPrieferredTime=findViewById(R.id.editTextPrieferredTime);
        editTexitPreferredSalary=findViewById(R.id.editTexitPreferredSalary);


        firebaseAuth= FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("jobseekers");



        NextpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            firebaseUser=task.getResult().getUser();
                            DatabaseReference newUser=databaseReference.child(firebaseUser.getUid());
                            newUser.child("firstnamee").setValue(firstnamee);
                            newUser.child("lastname").setValue(lastname);
                            newUser.child("Gender").setValue(Gender);
                            newUser.child("Date").setValue(Date);
                            newUser.child("Phone").setValue(Phone);
                            newUser.child("email").setValue(email);
                            newUser.child("Password").setValue(Password);
                            newUser.child("Maijor").setValue(editTextMaijor.getText().toString().trim());
                            newUser.child("Qualification").setValue(ediitTextQualification.getText().toString().trim());
                            newUser.child("Experience").setValue(editTextFiieldofExperience.getText().toString().trim());
                            newUser.child("GraiduatrionYear").setValue(editTextGraiduationYear.getText().toString().trim());
                            newUser.child("YearrsofExperience").setValue(editiTextYearsofExperience.getText().toString().trim());
                            newUser.child("PreiferrredJobTitle").setValue(editTextPreiferredJobTitle.getText().toString().trim());
                            newUser.child("Natrioinality").setValue(editTextNatioinality.getText().toString().trim());
                            newUser.child("PrrieferredTime").setValue(editTextPrieferredTime.getText().toString().trim());
                            newUser.child("PrreferredSalary").setValue(editTexitPreferredSalary.getText().toString().trim());


                            Toast.makeText(registerjobseek2.this,"done",Toast.LENGTH_LONG).show();
                            Intent toSigin = new Intent(registerjobseek2.this,home.class);
                            startActivity(toSigin);
                            finish();
                        }else{
                            Toast.makeText(registerjobseek2.this,"Error",Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });
    }


}