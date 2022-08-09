package com.jerucloud.epursuit;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reminders#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reminders extends Fragment {
    RecyclerView rv;
    List<data_job_adds> Listdata;
    FirebaseDatabase database;
    DatabaseReference ref,ref1;
    adapter adds_adapter;
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

    public reminders() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reminders.
     */
    // TODO: Rename and change types and number of parameters
    public static reminders newInstance(String param1, String param2) {
        reminders fragment = new reminders();
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
        View view= inflater.inflate(R.layout.fragment_reminders, container, false);
        rv=view.findViewById(R.id.rv_reminder);
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
                                        String jobCity = userSnapshot.child("jobCity").getValue().toString();
                                        String jobSalary = userSnapshot.child("jobSalary").getValue().toString();
                                        String maj_workingtime = userSnapshot.child("workingtime").getValue().toString();
                                        String title = userSnapshot.child("title").getValue().toString();
                                        String contract_period = userSnapshot.child("contractPeriod").getValue().toString();





                                        data_job_adds l=userSnapshot.getValue(data_job_adds.class);
                                        l=new data_job_adds( title, jobCategory,  jobSalary,  jobCity,  jobDescription,  contract_period,  maj_workingtime,
                                                gender, major,  qualiification,  graduationYear,  fiieldfExperience,  yearsofExpeirience, "rec_own");

                                        Listdata.add(l);                                    }
                                }
                                adds_adapter = new adapter(Listdata, getActivity(),0);
                                adds_adapter.notifyDataSetChanged();
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


        return view;




    }
}