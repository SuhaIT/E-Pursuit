package com.jerucloud.epursuit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class rec_adapter extends RecyclerView.Adapter<rec_adapter.MyViewHolder> {

    List<data_job_seek> mdata;
    Context mContext;


    public rec_adapter(List<data_job_seek> mdata, Context context) {
        this.mdata = mdata;
        this.mContext=context;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item, parent, false);
        rec_adapter.MyViewHolder holder = new rec_adapter.MyViewHolder(view,mContext);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(mdata.get(position).getFirstnamee());
    }



    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,url;
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
            final LayoutInflater inflater = LayoutInflater.from(mContext);
            View viewm = inflater.inflate(R.layout.activity_detail_seek, null);
            final AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                    .setView(viewm)
                    .create();
            TextView name = viewm.findViewById(R.id.textView20);
            TextView email = viewm.findViewById(R.id.textView14);
            TextView yearsexp = viewm.findViewById(R.id.textView15);
            TextView qualific = viewm.findViewById(R.id.textView16);
            TextView prefffetime = viewm.findViewById(R.id.textView17);
            TextView salar = viewm.findViewById(R.id.textView18);
            TextView nationaliy = viewm.findViewById(R.id.textView19);
            TextView major = viewm.findViewById(R.id.textView21);
            TextView graduationyea = viewm.findViewById(R.id.textView22);
            Button button2=viewm.findViewById(R.id.button2);
            int position = getLayoutPosition(); // gets item position
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref1;
             final String[] expyears = new String[1];
            final String[] qualif = new String[1];
            final String[] prefer = new String[1];
            final String[] salary = new String[1];
            final String[] nationali = new String[1];
            final String[] graduationyear = new String[1];
            final String[] majo = new String[1];
            ref1= database.getInstance().getReference("jobseekers");
            final Query userQuery = ref1.orderByChild("email");
            userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        if (userSnapshot.child("email").getValue().equals(mdata.get(position).getEmail())) {
                            majo[0] = userSnapshot.child("Maijor").getValue().toString();
                             expyears[0] = userSnapshot.child("Experience").getValue().toString();
                             qualif[0] = userSnapshot.child("Qualification").getValue().toString();
                             prefer[0] = userSnapshot.child("PrrieferredTime").getValue().toString();
                             salary[0] = userSnapshot.child("PrreferredSalary").getValue().toString();
                             nationali[0] = userSnapshot.child("Natrioinality").getValue().toString();
                             graduationyear[0] = userSnapshot.child("GraiduatrionYear").getValue().toString();
                            name.setText(name.getText().toString()+" "+mdata.get(position).getFirstnamee()+mdata.get(position).getLastname());
                            email.setText(email.getText().toString()+" "+mdata.get(position).getEmail());
                            yearsexp.setText(yearsexp.getText().toString()+" "+expyears[0]);
                            qualific.setText(qualific.getText().toString()+" "+qualif[0]);
                            prefffetime.setText(prefffetime.getText().toString()+" "+prefer[0]);
                            salar.setText(salar.getText().toString()+" "+salary[0]);
                            nationaliy.setText(nationaliy.getText().toString()+" "+nationali[0]);
                            major.setText(major.getText().toString()+" "+majo[0]);
                            graduationyea.setText(graduationyea.getText().toString()+" "+graduationyear[0]);
                        }}}

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    
                }
            });









            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO CHAT WITH this JOBSEEKERS
                    Intent i = new Intent(mContext,messages.class);
                    i.putExtra("jobseek",mdata.get(position));
                   mContext.startActivity(i);
                }
            });
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();



        }







    }



}
