package com.jerucloud.epursuit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class job_req extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String editTeuxtRecName,editTextRecGender,editTextCRN,editTextRecPhone,editTextRecEmail,editTextRecPassword
            ,editTextBio;
    String editTextGender2,editTextMajor2,editTextQualification2,editTextGraduationYear2,editTextFieldofExperience2,
            editTextYearsofExperience2,editTextFieldofExperience6;
    EditText editTextGendier2,editTexitMajor2,editTextQualiiification2,editTextGiraduationYear2,editTextFiieldofExperience2,editTextYearsofExpeirience2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_req);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
            } else {
                editTeuxtRecName= extras.getString("editTeuxtRecName");
                editTextRecGender= extras.getString("editTextRecGender");
                editTextCRN= extras.getString("editTextCRN");
                editTextRecPhone= extras.getString("editTextRecPhone");
                editTextRecEmail= extras.getString("editTextRecEmail");
                editTextRecPassword= extras.getString("editTextRecPassword");
                editTextBio= extras.getString("editTextBio");
                editTextGender2= extras.getString("editTextGender2");
                editTextMajor2= extras.getString("editTextMajor2");
                editTextQualification2= extras.getString("editTextQualification2");
                editTextGraduationYear2= extras.getString("editTextGraduationYear2");
                editTextFieldofExperience2= extras.getString("editTextFieldofExperience2");
                editTextYearsofExperience2= extras.getString("editTextYearsofExperience2");
                editTextFieldofExperience6= extras.getString("editTextFieldofExperience6");

            }
        } else {

        }
        firebaseAuth=firebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("jobadds");
        Button DoneBtn=findViewById(R.id.DoneBtn);
        editTextGendier2=findViewById(R.id.editTextGendier2);
        editTexitMajor2=findViewById(R.id.editTexitMajor2);
        editTextQualiiification2=findViewById(R.id.editTextQualiiification2);
        editTextGiraduationYear2=findViewById(R.id.editTextGiraduationYear2);
        editTextFiieldofExperience2=findViewById(R.id.editTextFiieldofExperience2);
        editTextYearsofExpeirience2=findViewById(R.id.editTextYearsofExpeirience2);


        DoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data_job_adds helperClass = new data_job_adds(editTextGender2,
                        editTextMajor2,editTextQualification2,editTextGraduationYear2,editTextFieldofExperience2
                        ,editTextYearsofExperience2,editTextFieldofExperience6,editTextGendier2.getText().toString().trim()
                ,editTexitMajor2.getText().toString().trim(),editTextQualiiification2.getText().toString().trim(),editTextGiraduationYear2.getText().toString().trim()
                       ,editTextFiieldofExperience2.getText().toString().trim() ,editTextYearsofExpeirience2.getText().toString().trim(),FirebaseAuth.getInstance().getCurrentUser().getUid());
                databaseReference.push(). setValue(helperClass);
                Toast.makeText(job_req.this, "ads added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}