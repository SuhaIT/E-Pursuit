package com.jerucloud.epursuit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class all extends AppCompatActivity {
    RecyclerView rv;
    List<data_job_adds> Listdata;
    FirebaseDatabase database;
    DatabaseReference ref,ref1;
    data_adapter adds_adapter;
    public  String major;
    private static String ID;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        rv=findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(all.this);
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        Listdata = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref1= database.getInstance().getReference("jobadds");
        final Query userQuery = ref1.orderByChild("rec_own");
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ID = user.getUid();
        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    if (userSnapshot.child("rec_own").getValue().equals(ID)) {
                        String gender = userSnapshot.child("gender").getValue().toString();
                        String graduationYear = userSnapshot.child("graduationYear").getValue().toString();
                        String yearsofExpeirience = userSnapshot.child("yearsofExpeirience").getValue().toString();
                        String qualiification = userSnapshot.child("qualiification").getValue().toString();
                        String fiieldfExperience = userSnapshot.child("fiieldfExperience").getValue().toString();
                        String jobDescription = userSnapshot.child("jobDescription").getValue().toString();
                        String jobCategory = userSnapshot.child("jobCategory").getValue().toString();
                        String contract = userSnapshot.child("contractPeriod").getValue().toString();

                        String jobCity = userSnapshot.child("jobCity").getValue().toString();
                        String jobSalary = userSnapshot.child("jobSalary").getValue().toString();
                        String maj_workingtime = userSnapshot.child("workingtime").getValue().toString();
                        String title = userSnapshot.child("title").getValue().toString();
                        String id=userSnapshot.getKey();



                        data_job_adds l=new data_job_adds( title, jobCategory,  jobSalary,  jobCity,  jobDescription,  contract,  maj_workingtime,
                                gender, major,  qualiification,  graduationYear,  fiieldfExperience,  yearsofExpeirience, id);
l.setContractPeriod(contract);
                        Listdata.add(l);

                    }
                }
                adds_adapter = new data_adapter(Listdata, all.this,1);
                rv.setAdapter(adds_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

/*LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.item_detail, null);
            TextView gender=view.findViewById(R.id.textView20);
            TextView graduationYear=view.findViewById(R.id.textView14);
            TextView yearsofExpeirience=view.findViewById(R.id.textView15);
            TextView majorr=view.findViewById(R.id.textView16);
            TextView qualiification=view.findViewById(R.id.textView17);
            TextView fiieldfExperience=view.findViewById(R.id.textView18);
            TextView jobDescription=view.findViewById(R.id.textView19);
            TextView jobCategory=view.findViewById(R.id.textView22);
            TextView jobCity=view.findViewById(R.id.textView23);
            TextView jobSalary=view.findViewById(R.id.textView24);
            TextView workingtime=view.findViewById(R.id.textView25);
            Button accept =view.findViewById(R.id.button2);
            Button reject =view.findViewById(R.id.button3);

            gender.setText(mdata.get(position).getGender());
            graduationYear.setText(mdata.get(position).getGraduationYear());
            yearsofExpeirience.setText(mdata.get(position).getYearsofExpeirience());
            majorr.setText(mdata.get(position).getMajorr());
            qualiification.setText(mdata.get(position).getQualiification());
            fiieldfExperience.setText(mdata.get(position).getFiieldfExperience());
            jobDescription.setText(mdata.get(position).getJobDescription());
            jobCategory.setText(mdata.get(position).getJobCategory());
            jobCity.setText(mdata.get(position).getJobCity());
            jobSalary.setText(mdata.get(position).getJobSalary());
            workingtime.setText(mdata.get(position).getWorkingtime());

            AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                    .setView(view)
                    .create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();*/



}