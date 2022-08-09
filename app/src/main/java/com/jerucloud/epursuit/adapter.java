package com.jerucloud.epursuit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder>{

    List<data_job_adds> mdata;
    private LayoutInflater inflater;
    Context mContext;
    int checker;




    public adapter(List<data_job_adds> mdata, Context mcontext,int checker) {
        this.mdata = mdata;
        this.mContext=mcontext;
        this.checker=checker;
    }




    @NonNull
    @Override
    public adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mContext);
        View view=view = inflater.inflate(R.layout.item_rem, parent, false);
        adapter.MyViewHolder holder = new adapter.MyViewHolder(view,mContext);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(mdata.get(position).getTitle());

            holder.remain.setText(mdata.get(position).getContractPeriod());

           if (mdata.get(position).getContractPeriod().equals("3 days left")||mdata.get(position).getContractPeriod().equals("2 days left")||mdata.get(position).getContractPeriod().equals("1 days left")){
                holder.cons.setBackgroundColor(R.color.red);
               data_job_adds item = mdata.get(position);
               int itemPos = mdata.indexOf(item);
               mdata.remove(itemPos);
               mdata.add(0, item );



           }


    }





    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder   implements View.OnClickListener {
        TextView name,remain;
        ConstraintLayout cons;

        private Context context;
        addsitemclicklistner addsitemclicklistner;



        @SuppressLint("ResourceAsColor")
        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.job_title);
            remain=itemView.findViewById(R.id.textView13);
            cons=itemView.findViewById(R.id.conds);

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
