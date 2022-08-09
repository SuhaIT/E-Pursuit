package com.jerucloud.epursuit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class details extends AppCompatActivity {
    FirebaseDatabase database;

    DatabaseReference ref,ref1,ref2;
    private FirebaseAuth firebaseAuth;
    private static String emailID;
    public  String major;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        data_job_adds myObject = (data_job_adds) getIntent().getSerializableExtra("obj");
        TextView gender=findViewById(R.id.textView20);
        TextView graduationYear=findViewById(R.id.textView14);
        TextView yearsofExpeirience=findViewById(R.id.textView15);
        TextView majorr=findViewById(R.id.textView16);
        TextView qualiification=findViewById(R.id.textView17);
        TextView fiieldfExperience=findViewById(R.id.textView18);
        TextView jobDescription=findViewById(R.id.textView19);
        TextView jobCategory=findViewById(R.id.textView22);
        TextView jobCity=findViewById(R.id.textView23);
        TextView jobSalary=findViewById(R.id.textView24);
        TextView titl=findViewById(R.id.titl);
        TextView contract=findViewById(R.id.textView21);
        titl.setText(myObject.getTitle());
        TextView workingtime=findViewById(R.id.textView25);
        Button accept =findViewById(R.id.button2);
        Button reject =findViewById(R.id.button3);
        int i =getIntent().getIntExtra("rec",0);
        if (i==1){
            accept.setText("edit");
            reject.setText("delete");
        }else{
            accept.setText("Accept");
            reject.setText("Reject");
        }
        contract.setText("contract period :"+myObject.getContractPeriod());
        gender.setText("gender :"+myObject.getGender());
        graduationYear.setText("GraduationYear :"+myObject.getGraduationYear());
        yearsofExpeirience.setText("gender :"+myObject.getYearsofExpeirience());
        majorr.setText("majorr :"+myObject.getMajorr());
        qualiification.setText("qualiification :"+myObject.getQualiification());
        fiieldfExperience.setText("fiieldfExperience :"+myObject.getFiieldfExperience());
        jobDescription.setText("jobDescription :"+myObject.getJobDescription());
        jobCategory.setText("jobCategory :"+myObject.getJobCategory());
        jobCity.setText("jobCity :"+myObject.getJobCity());
        jobSalary.setText("jobSalary :"+myObject.getJobSalary());
        workingtime.setText("workingtime :"+myObject.getWorkingtime());
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accept.getText().equals("edit")){

                    Intent i = new Intent(details.this,editdetail.class);
                    i.putExtra("objj",myObject);
                    startActivity(i);
                }else{
                  //TODO CHAT BETWEEN JOBSEEKERS AND RECRUITER
                    Intent i = new Intent(details.this,messages.class);
                    i.putExtra("rec_own",myObject.getRec_own());
                    startActivity(i);

                }
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reject.getText().equals("delete")){
                    Toast.makeText(details.this, "delete job adds", Toast.LENGTH_SHORT).show();
                    firebaseAuth = FirebaseAuth.getInstance();
                    database = FirebaseDatabase.getInstance();
                    ref1= database.getInstance().getReference("jobadds");
                    final Query userQuery = ref1.orderByChild("rec_own");
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    emailID = user.getUid();
                    userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot post : dataSnapshot.getChildren()) {
                                if (post.child("rec_own").getValue().equals(user.getUid())) {
                                    if(post.child("majorr").getValue().equals(myObject.getMajorr())){
                                        if(post.child("jobDescription").getValue().equals(myObject.getJobDescription())){
                                            post.getRef().removeValue();
                                            Intent i=new Intent(details.this,all.class);
                                            startActivity(i);
                                            finish();
                                        }

                                    }

                                }
                             else{

                }
            }
        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    }else{
                    Intent i=new Intent(details.this,home.class);
                    startActivity(i);
                    finish();
                }

            }});


    }
}