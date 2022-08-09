package com.jerucloud.epursuit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class profile extends AppCompatActivity {
    EditText name,email,phon;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private static String emailID;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout = findViewById(R.id.logout);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phon = findViewById(R.id.phon);
        firebaseAuth = FirebaseAuth.getInstance().getInstance();
        database = FirebaseDatabase.getInstance();

        if (firebaseAuth.getInstance().getCurrentUser() == null) {
            finish();
            //Starting the User Login Activity if the user is not Logged in
            startActivity(new Intent(this, MainActivity.class));
        }
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        email.setText(user.getEmail());
        emailID = user.getEmail();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                //Switches to login Activity
                startActivity(new Intent(profile.this, MainActivity.class));

            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                DatabaseReference users = database.getReference("jobseekers");
                final Query userQuery = users.orderByChild("email");

                userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot post : dataSnapshot.getChildren()) {
                            if (post.child("email").getValue().equals(user.getEmail())) {
                                name.setText(post.child("firstnamee").getValue().toString());
                                phon.setText(post.child("Phone").getValue().toString());
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

            } else {
                DatabaseReference users = database.getReference("recreator");
                final Query userQuery = users.orderByChild("RecEmail");

                userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot post : dataSnapshot.getChildren()) {
                            if (post.child("RecEmail").getValue().equals(user.getEmail())) {
                                name.setText(post.child("RecName").getValue().toString());
                                phon.setText(post.child("RecPhone").getValue().toString());
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


            }
        }
    }}