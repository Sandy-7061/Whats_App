package com.example.whats_app.Adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whats_app.Chats_Detail;
import com.example.whats_app.Modal.Message;
import com.example.whats_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatAdapter extends  RecyclerView.Adapter{

    ArrayList<Message> messageArrayList;
    Context context;
    String recId;

    public ChatAdapter(ArrayList<Message> messageArrayList, Context context, String recId) {
        this.messageArrayList = messageArrayList;
        this.context = context;
        this.recId = recId;
    }

    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<Message> messageArrayList, Context context) {
        this.messageArrayList = messageArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sender_chat,parent,false);
            return new Senderviewholder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_receiver_ly,parent,false);
            return new Recieverviewholder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            Message messagemodal = messageArrayList.get(position);

            if(holder.getClass() == Senderviewholder.class) {
                ((Senderviewholder) holder).sendermsg.setText(messagemodal.getMessage());
            }else{
                ((Recieverviewholder)holder).receivermsg.setText(messagemodal.getMessage());
            }
    }

    @Override
    public int getItemViewType(int position) {
        if(messageArrayList.get(position).getUid().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else {
            return RECEIVER_VIEW_TYPE;
        }

    }

    @Override
    public int getItemCount() {
        return messageArrayList.size();
    }

    public class Recieverviewholder extends RecyclerView.ViewHolder{

        TextView receivermsg,receivertime;
        public Recieverviewholder(@NonNull View itemView) {
            super(itemView);
            receivermsg = itemView.findViewById(R.id.receiver_msg);
            receivertime = itemView.findViewById(R.id.receiver_time);

        }
    }

    public class Senderviewholder extends RecyclerView.ViewHolder{
            TextView sendermsg,sendertime;
        public Senderviewholder(@NonNull View itemView) {
            super(itemView);
            sendermsg = itemView.findViewById(R.id.sender_msg);
            sendertime = itemView.findViewById(R.id.sender_time);
        }
    }
}
