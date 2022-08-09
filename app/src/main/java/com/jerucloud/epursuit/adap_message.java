package com.jerucloud.epursuit;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class adap_message extends RecyclerView.Adapter<adap_message.MyViewholder>{
    List<chatlist1> message_listList;
   Context context;

    public adap_message(List<chatlist1> message_listList, Context context) {
        this.message_listList = message_listList;
        this.context = context;
    }

    public adap_message(List<chatlist1> message_listList) {
        this.message_listList = message_listList;
    }

    @NonNull
    @Override
    public adap_message.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_adapter,null));

    }

    @Override
    public void onBindViewHolder(@NonNull adap_message.MyViewholder holder, int position) {

      ;
        if (message_listList.get(position).getId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){

        holder.my.setVisibility(View.VISIBLE);
        holder.opp.setVisibility(View.GONE);
        holder.my_message.setText(message_listList.get(position).getMessage());

        }else{
          //  holder.cons.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            holder.opp.setVisibility(View.VISIBLE);
            holder.my.setVisibility(View.GONE);
            holder.oppo_message.setText(message_listList.get(position).getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return message_listList.size();
    }


    public class MyViewholder extends RecyclerView.ViewHolder {
        LinearLayout opp,my;
        TextView oppo_message,my_message;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            opp=itemView.findViewById(R.id.oppo_layout);
            my=itemView.findViewById(R.id.my_layout);
            oppo_message=itemView.findViewById(R.id.oppo_message);
            my_message=itemView.findViewById(R.id.my_message);
        }
    }
}
