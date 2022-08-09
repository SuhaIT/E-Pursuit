package com.jerucloud.epursuit;

import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homerecfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homerecfrag extends Fragment {
    RecyclerView rv;

    java.util.List<data_job_seek> Listads;
    java.util.List<data_job_adds> Lisads;
    FirebaseDatabase database;
    rec_adapter adds_adapter;
    DatabaseReference ref,ref1,ref2;
    private FirebaseAuth firebaseAuth;
    private static String emailID;
    public  String major;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homerecfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homerecfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static homerecfrag newInstance(String param1, String param2) {
        homerecfrag fragment = new homerecfrag();
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
        View view= inflater.inflate(R.layout.fragment_homerecfrag, container, false);
        rv=view.findViewById(R.id.rv);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        Listads = new ArrayList<>();
        Lisads=new ArrayList<>();


        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref1= database.getInstance().getReference("recreator");
        final Query userQuery = ref1.orderByChild("RecEmail");
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        emailID = user.getEmail();
        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot post : dataSnapshot.getChildren()) {
                    if (post.child("RecEmail").getValue().equals(user.getEmail())) {

                        ref2= FirebaseDatabase.getInstance().getReference("jobadds");
                        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot userSnapshot: snapshot.getChildren()) {
                                    String rec_own = userSnapshot.child("rec_own").getValue().toString();
                                    if(rec_own.equals(user.getUid())){
                                        String maj_add = userSnapshot.child("majorr").getValue().toString();

                                        ref= FirebaseDatabase.getInstance().getReference("jobseekers");
                                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for (DataSnapshot userSnapshot: snapshot.getChildren()) {
                                                    String maj_adds = userSnapshot.child("Maijor").getValue().toString();
                                                    if (maj_adds.equals(maj_add)) {
                                                        data_job_seek l=userSnapshot.getValue(data_job_seek.class);
                                                        Listads.add(l);                                    }
                                                }
                                                adds_adapter = new rec_adapter(Listads, getActivity());
                                                rv.setAdapter(adds_adapter);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }

                                }
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