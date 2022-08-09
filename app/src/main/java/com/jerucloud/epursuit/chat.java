package com.jerucloud.epursuit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link chat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class chat extends Fragment {
RecyclerView rv;
List<chatlist> chat = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference ref,ref1;
    private FirebaseAuth firebaseAuth;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public chat() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment chat.
     */
    // TODO: Rename and change types and number of parameters
    public static chat newInstance(String param1, String param2) {
        chat fragment = new chat();
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
        View view= inflater.inflate(R.layout.fragment_chat, container, false);
        rv=view.findViewById(R.id.rv_chat);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        ref= database.getInstance().getReference("chat");
        ref1= database.getInstance().getReference("recreator");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot post : snapshot.getChildren()) {
                    if (post.child("user_one").getValue().toString().equals(id)){//jobseekers
                        String id_reciever=post.child("user_two").getValue().toString();
                        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot post : snapshot.getChildren()) {
                                    if (post.getKey().equals(id_reciever)){
                                        String reciver_name=post.child("RecName").getValue().toString();
                                        chat.add(new chatlist(id_reciever,id,reciver_name,"n"));
                                    }

                                }
                                chat_adapter adds_adapter = new chat_adapter(chat, getActivity());
                                rv.setAdapter(adds_adapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });




}else if(post.child("user_two").getValue().toString().equals(id)){
                        String id_reciever=post.child("user_one").getValue().toString();
                        ref1= database.getInstance().getReference("jobseekers");
                        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot post : snapshot.getChildren()) {
                                    if (post.getKey().equals(id_reciever)){
                                        String reciver_name=post.child("firstnamee").getValue().toString()+post.child("lastname").getValue().toString();
                                        chat.add(new chatlist(id_reciever,id,reciver_name,"n"));
                                    }

                                }
                                chat_adapter adds_adapter = new chat_adapter(chat, getActivity());
                                rv.setAdapter(adds_adapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }



                        }}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        return  view;
}

    }