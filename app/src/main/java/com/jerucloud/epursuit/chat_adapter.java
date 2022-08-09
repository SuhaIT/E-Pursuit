package com.jerucloud.epursuit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class chat_adapter extends RecyclerView.Adapter<chat_adapter.MyViewHolder>{

    List<chatlist> mdata;
    private LayoutInflater inflater;
    Context mContext;




    public chat_adapter(List<chatlist> mdata, Context mcontext) {
        this.mdata = mdata;
        this.mContext=mcontext;
    }




    @NonNull
    @Override
    public chat_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mContext);
        View view=view = inflater.inflate(R.layout.itemmess, parent, false);
        chat_adapter.MyViewHolder holder = new chat_adapter.MyViewHolder(view,mContext);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull chat_adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(mdata.get(position).getNamereciver());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext,messages.class);
                i.putExtra("id_reci",mdata.get(position).getIdreciever());
                i.putExtra("name_reci",mdata.get(position).getNamereciver());
                mContext.startActivity(i);


            }
        });

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



        }
    }





}
