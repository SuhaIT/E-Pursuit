package com.jerucloud.epursuit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class messages extends AppCompatActivity {
    DatabaseReference ref,ref1,ref2;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://epursuit-eef8b-default-rtdb.firebaseio.com");
    String key_chat="";
    List<chatlist1>chatlist=new ArrayList<>();
    adap_message adap_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        RecyclerView rv=findViewById(R.id.rrr);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        String sender_id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        ImageView send = findViewById(R.id.imageView11);
        rv.setHasFixedSize(true);
      String reciver_id = "";
        TextView name= findViewById(R.id.nnnnn);
        EditText message = findViewById(R.id.editTextTextPersonName);

        final String[] name_reciver = {""};
        if(getIntent().hasExtra("id_reci")){
            reciver_id=getIntent().getStringExtra("id_reci");
            name_reciver[0] =getIntent().getStringExtra("name_reci");
            name.setText(name_reciver[0]+"");
        }else if (getIntent().hasExtra("rec_own")){
            reciver_id=getIntent().getStringExtra("rec_own");
            ref=FirebaseDatabase.getInstance().getReference("recreator");
            String finalReciver_id1 = reciver_id;
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot post : snapshot.getChildren()) {
                        if (post.getKey().equals(finalReciver_id1)) {
                            name_reciver[0] =post.child("RecName").getValue().toString();
                            name.setText(name_reciver[0]+"");

                        }}    }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        ref1=FirebaseDatabase.getInstance().getReference().child("chat");
        String finalReciver_id2 = reciver_id;
        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot post : snapshot.getChildren()) {
                    if (post.child("user_one").getValue().toString().equals(sender_id)&&post.child("user_two").getValue().toString().equals(finalReciver_id2)){
                        ref2=FirebaseDatabase.getInstance().getReference().child("chat").child(post.getKey()).child("messages");
                        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot post : snapshot.getChildren()) {
                                    chatlist1 cc=new chatlist1(post.child("id").getValue().toString(),post.child("msg").getValue().toString());
                                    chatlist.add(cc);

                                }
                                adap_message=new adap_message(chatlist,messages.this);
                                rv.setAdapter(adap_message);
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

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (key_chat.isEmpty()){
                    key_chat="1";
                    if (snapshot.hasChild("chat")){
                        key_chat=String.valueOf (snapshot.child("chat").getChildrenCount()+1);
                        if (snapshot.child("chat").child(key_chat).hasChild("messages")){
                            for (DataSnapshot messagesSnapshot:snapshot.child(key_chat).child("messages").getChildren()){
                                if (messagesSnapshot.hasChild("msg")&&messagesSnapshot.hasChild("id")){
                                    final String mesagetime =messagesSnapshot.getKey();
                                    final String getid =messagesSnapshot.child("id").getValue(String.class);
                                    final String getmsg =messagesSnapshot.child("msg").getValue(String.class);
                                    Timestamp timestamp= new Timestamp(Long.parseLong(mesagetime));
                                    Date date = new Date(timestamp.getTime());
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault());
                                    SimpleDateFormat  simpletimeFormat = new SimpleDateFormat("hh:mm aa",Locale.getDefault());

                                    chatlist1 chatlist1 = new chatlist1(getid,name_reciver[0],getmsg,simpleDateFormat.format(date),simpletimeFormat.format(date));


                                }
                            }
                        }}




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }}
        );
        String finalReciver_id = reciver_id;
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messg= message.getText().toString();
                final String currenttime=String.valueOf(System.currentTimeMillis()).substring(0,10);
                databaseReference.child("chat").child(key_chat).child("user_one").setValue(sender_id);
                databaseReference.child("chat").child(key_chat).child("user_two").setValue(finalReciver_id);
                databaseReference.child("chat").child(key_chat).child("messages").child(currenttime).child("msg").setValue(messg);
                databaseReference.child("chat").child(key_chat).child("messages").child(currenttime).child("id").setValue(sender_id);
                message.setText("");
                chatlist.clear();
            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot post : snapshot.getChildren()) {
                            if (post.child("user_one").getValue().toString().equals(sender_id)&&post.child("user_two").getValue().toString().equals(finalReciver_id2)){
                                ref2=FirebaseDatabase.getInstance().getReference().child("chat").child(post.getKey()).child("messages");
                                ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot post : snapshot.getChildren()) {
                                            chatlist1 cc=new chatlist1(post.child("id").getValue().toString(),post.child("msg").getValue().toString());
                                            chatlist.add(cc);

                                        }
                                        adap_message=new adap_message(chatlist,messages.this);
                                        rv.setAdapter(adap_message);
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

            }

        });




    }
}