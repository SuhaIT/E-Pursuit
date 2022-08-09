package com.jerucloud.epursuit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class editdetail extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdetail);

        data_job_adds myObject = (data_job_adds) getIntent().getSerializableExtra("objj");

        TextView gender=findViewById(R.id.textView28);
        TextView graduationYear=findViewById(R.id.textView29);
        TextView yearsofExpeirience=findViewById(R.id.textView30);
        TextView majorr=findViewById(R.id.textView31);
        TextView qualiification=findViewById(R.id.textView32);
        TextView fiieldfExperience=findViewById(R.id.textView33);
        TextView jobDescription=findViewById(R.id.textView34);
        TextView jobCategory=findViewById(R.id.textView35);
        TextView jobCity=findViewById(R.id.textView36);
        TextView jobSalary=findViewById(R.id.textView37);
        TextView workingtime=findViewById(R.id.textView39);
        TextView contract=findViewById(R.id.textView40);
        Button accept =findViewById(R.id.button);

        gender.setText("gender :"+myObject.getGender());
        contract.setText("Contract period  :"+myObject.getContractPeriod());
        graduationYear.setText("Graduation year :"+myObject.getGraduationYear());
        yearsofExpeirience.setText("Years of Experience :"+myObject.getYearsofExpeirience());
        majorr.setText("majorr :"+myObject.getMajorr());
        qualiification.setText("qualiification :"+myObject.getQualiification());
        fiieldfExperience.setText("fiieldfExperience :"+myObject.getFiieldfExperience());
        jobDescription.setText("jobDescription :"+myObject.getJobDescription());
        jobCategory.setText("jobCategory :"+myObject.getJobCategory());
        jobCity.setText("jobCity :"+myObject.getJobCity());
        jobSalary.setText("jobSalary :"+myObject.getJobSalary());
        workingtime.setText("workingtime :"+ myObject.getWorkingtime());


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.child("jobadds").child(myObject.getRec_own()).child("gender").setValue(str(gender.getText().toString()));
                ref.child("jobadds").child(myObject.getRec_own()).child("contractPeriod").setValue(str(contract.getText().toString()));
                ref.child("jobadds").child(myObject.getRec_own()).child("fiieldfExperience").setValue(str(fiieldfExperience.getText().toString()));
                ref.child("jobadds").child(myObject.getRec_own()).child("graduationYear").setValue(str(graduationYear.getText().toString()));
                ref.child("jobadds").child(myObject.getRec_own()).child("jobCategory").setValue(str(jobCategory.getText().toString()));
                ref.child("jobadds").child(myObject.getRec_own()).child("jobCity").setValue(str(jobCity.getText().toString()));
                ref.child("jobadds").child(myObject.getRec_own()).child("jobDescription").setValue(str(jobDescription.getText().toString()));
                ref.child("jobadds").child(myObject.getRec_own()).child("jobSalary").setValue(str(jobSalary.getText().toString()));
                ref.child("jobadds").child(myObject.getRec_own()).child("majorr").setValue(str(majorr.getText().toString()));
                ref.child("jobadds").child(myObject.getRec_own()).child("qualiification").setValue(str(qualiification.getText().toString()));
                ref.child("jobadds").child(myObject.getRec_own()).child("workingtime").setValue(str(workingtime.getText().toString()));
                ref.child("jobadds").child(myObject.getRec_own()).child("yearsofExpeirience").setValue(str(yearsofExpeirience.getText().toString()));
                Toast.makeText(editdetail.this, "Updated", Toast.LENGTH_SHORT).show();
                onBackPressed();


            }
        });



    }

    private String str(String toString) {
        String[] arrOfStr = toString.split(":", -1);
        return arrOfStr[1];

    }

}