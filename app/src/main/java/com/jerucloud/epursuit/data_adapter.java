package com.jerucloud.epursuit;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class data_adapter extends RecyclerView.Adapter<data_adapter.MyViewHolder>{

    List<data_job_adds> mdata;
     private LayoutInflater inflater;
    Context mContext;
    int checker;




    public data_adapter(List<data_job_adds> mdata, Context mcontext,int checker) {
        this.mdata = mdata;
        this.mContext=mcontext;
        this.checker=checker;
    }




    @NonNull
    @Override
    public data_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mContext);
        View view=view = inflater.inflate(R.layout.item, parent, false);
        MyViewHolder holder = new MyViewHolder(view,mContext);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull data_adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
       holder.name.setText(mdata.get(position).getTitle());

        }




    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder   implements View.OnClickListener {
        TextView name;

        private Context context;
        addsitemclicklistner addsitemclicklistner;



        public MyViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.job_title);
            this.context = context;
            this.addsitemclicklistner= addsitemclicklistner;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int position = getLayoutPosition(); // gets item position
            Intent i = new Intent(mContext,details.class);
            if(checker==1){
                i.putExtra("rec",1);
            }else{

            }
            i.putExtra("obj",mdata.get(position));
            mContext.startActivity(i);


        }
    }





}
