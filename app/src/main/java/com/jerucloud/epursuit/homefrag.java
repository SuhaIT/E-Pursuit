package com.jerucloud.epursuit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homefrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homefrag extends Fragment  {
    RecyclerView rv;
    List<data_job_adds> Listdata;
    FirebaseDatabase database;
    DatabaseReference ref,ref1;
    data_adapter adds_adapter;
    public  String major;
    private static String emailID;
    private FirebaseAuth firebaseAuth;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homefrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homefrag.
     */
    // TODO: Rename and change types and number of parameters
    public static homefrag newInstance(String param1, String param2) {
        homefrag fragment = new homefrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_homefrag, container, false);
        rv=view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        Listdata = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref1= database.getInstance().getReference("jobseekers");
        final Query userQuery = ref1.orderByChild("email");
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        emailID = user.getEmail();
        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot post : dataSnapshot.getChildren()) {
                    if (post.child("email").getValue().equals(user.getEmail())) {
                        major=post.child("Maijor").getValue(String.class);
                        ref= FirebaseDatabase.getInstance().getReference("jobadds");
                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot userSnapshot: snapshot.getChildren()) {
                                    String maj_adds = userSnapshot.child("majorr").getValue().toString();
                                    if (maj_adds.equals(major)) {
                                        String gender = userSnapshot.child("gender").getValue().toString();
                                    String graduationYear = userSnapshot.child("graduationYear").getValue().toString();
                                    String yearsofExpeirience = userSnapshot.child("yearsofExpeirience").getValue().toString();
                                    String qualiification = userSnapshot.child("qualiification").getValue().toString();
                                    String fiieldfExperience = userSnapshot.child("fiieldfExperience").getValue().toString();
                                    String jobDescription = userSnapshot.child("jobDescription").getValue().toString();
                                    String jobCategory = userSnapshot.child("jobCategory").getValue().toString();
                                        String contract = userSnapshot.child("contractPeriod").getValue().toString();
                                        String jobCity = userSnapshot.child("jobCity").getValue().toString();

                                        String rec_own = userSnapshot.child("rec_own").getValue().toString();
                                    String jobSalary = userSnapshot.child("jobSalary").getValue().toString();
                                    String maj_workingtime = userSnapshot.child("workingtime").getValue().toString();
                                        String title = userSnapshot.child("title").getValue().toString();



                                        data_job_adds l=new data_job_adds( title, jobCategory,  jobSalary,  jobCity,  jobDescription,  contract,  maj_workingtime,
                                                 gender, major,  qualiification,  graduationYear,  fiieldfExperience,  yearsofExpeirience, rec_own);

                                        Listdata.add(l);                                    }
                                }
                                adds_adapter = new data_adapter(Listdata, getActivity(),0);
                               rv.setAdapter(adds_adapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    } else {
                        Log.d("Output", "Failure");
                    }

                    Log.d("Output", post.child("Email").toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


     /*  ref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot npsnapshot : snapshot.getChildren()){
                        String maj_adds = dataSnapshot.child("username").getValue().toString();
                        if (Objects.equals(npsnapshot.child("majorr").getValue(), major)){
                            data_job_adds l=npsnapshot.getValue(data_job_adds.class);
                            Listdata.add(l);
                        }else{

                        }

                    }
                    adds_adapter = new data_adapter(Listdata, getActivity(), new addsitemclicklistner() {
                        @Override
                        public void onproductclick(data_job_adds data) {
                            LayoutInflater inflater = LayoutInflater.from(getContext());
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




                            gender.setText(data.getGender());
                            graduationYear.setText(data.getGraduationYear());
                            yearsofExpeirience.setText(data.getYearsofExpeirience());
                            majorr.setText(data.getMajorr());
                            qualiification.setText(data.getQualiification());
                            fiieldfExperience.setText(data.getFiieldfExperience());
                            jobDescription.setText(data.getJobDescription());
                            jobCategory.setText(data.getJobCategory());
                            jobCity.setText(data.getJobCity());
                            jobSalary.setText(data.getJobSalary());
                            workingtime.setText(data.getWorkingtime());

                            AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                                    .setView(view)
                                    .create();
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            alertDialog.show();
                        }
                    });
                    rv.setAdapter(adds_adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        return view;
    }

/*
        LayoutInflater inflater = LayoutInflater.from(getActivity());
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




        gender.setText(Listdata.get(position).getGender());
        graduationYear.setText(Listdata.get(position).getGraduationYear());
        yearsofExpeirience.setText(Listdata.get(position).getYearsofExpeirience());
        majorr.setText(Listdata.get(position).getMajorr());
        qualiification.setText(Listdata.get(position).getQualiification());
        fiieldfExperience.setText(Listdata.get(position).getFiieldfExperience());
        jobDescription.setText(Listdata.get(position).getJobDescription());
        jobCategory.setText(Listdata.get(position).getJobCategory());
        jobCity.setText(Listdata.get(position).getJobCity());
        jobSalary.setText(Listdata.get(position).getJobSalary());
        workingtime.setText(Listdata.get(position).getWorkingtime());
        Toast.makeText(getActivity(), Listdata.get(position).getGender(), Toast.LENGTH_SHORT).show();

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
    */
}
