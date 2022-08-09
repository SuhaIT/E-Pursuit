package com.jerucloud.epursuit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class createjabad extends AppCompatActivity {
    String editTeuxtRecName,editTextRecGender,editTextCRN,editTextRecPhone,editTextRecEmail,editTextRecPassword
            ,editTextBio;
    EditText editTextGender2,editTextMajor2,editTextQualification2,editTextGraduationYear2,editTextFieldofExperience2,
            editTextYearsofExperience2,editTextFieldofExperience6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createjabad);
        Button DoneBtn= findViewById(R.id.DoneBtn);
        editTextGender2=findViewById(R.id.editTextGender2);
        editTextMajor2=findViewById(R.id.editTextMajor2);
        editTextQualification2=findViewById(R.id.editTextQualification2);
        editTextGraduationYear2=findViewById(R.id.editTextGraduationYear2);
        editTextFieldofExperience2=findViewById(R.id.editTextFieldofExperience2);
                editTextYearsofExperience2=findViewById(R.id.editTextYearsofExperience2);
                editTextFieldofExperience6=findViewById(R.id.editTextFieldofExperience6);



        DoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(createjabad.this,job_req.class);
                i.putExtra("editTextGender2", editTextGender2.getText().toString().trim());
                i.putExtra("editTextMajor2", editTextMajor2.getText().toString().trim());
                i.putExtra("editTextQualification2", editTextQualification2.getText().toString().trim());
                i.putExtra("editTextGraduationYear2", editTextGraduationYear2.getText().toString().trim());
                i.putExtra("editTextFieldofExperience2", editTextFieldofExperience2.getText().toString().trim());
                i.putExtra("editTextYearsofExperience2", editTextYearsofExperience2.getText().toString().trim());
                i.putExtra("editTextFieldofExperience6", editTextFieldofExperience6.getText().toString().trim() );
                startActivity(i);
                finish();
            }
        });
    }
}