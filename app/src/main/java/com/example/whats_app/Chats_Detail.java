package com.example.whats_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.whats_app.Adapters.ChatAdapter;
import com.example.whats_app.Modal.Message;
import com.example.whats_app.databinding.ActivityChatsDetailBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class Chats_Detail extends AppCompatActivity {
ActivityChatsDetailBinding binding;
FirebaseDatabase database;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        final String sender_user_id = auth.getUid();
        String user_name = getIntent().getStringExtra("user_name");
        String user_profile = getIntent().getStringExtra("profile_pic");
        String receive_id = getIntent().getStringExtra("user_id");


        binding.userName.setText(user_name);
        Picasso.get().load(user_profile).placeholder(R.drawable.account_avatar_profile_user_3_svgrepo_com).into(binding.profileImage);

        binding.chatBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Chats_Detail.this,MainActivity.class));
            }
        });
        final ArrayList<Message> messagemodal= new ArrayList<>();

        final ChatAdapter adachat = new ChatAdapter(messagemodal,this);
        binding.chatRecyclerview.setAdapter(adachat);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatRecyclerview.setLayoutManager(layoutManager);

        final String senderRoom = sender_user_id + receive_id;
        final String receiverRoom = receive_id + sender_user_id;

        database.getReference().child("Chat")
                        .child(senderRoom)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        messagemodal.clear();
                                        for (DataSnapshot snapshot1 : snapshot.getChildren()){

                                            Message message1 = snapshot1.getValue(Message.class);
                                            messagemodal.add(message1);
                                        }
                                        adachat.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });






        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messages = binding.enterMessage.getText().toString();
                final com.example.whats_app.Modal.Message message = new com.example.whats_app.Modal.Message(sender_user_id,messages);
                message.setTimestamp(new Date().getTime());
                binding.enterMessage.setText("");

                database.getReference().child("Chat")
                        .child(senderRoom)
                        .push()
                        .setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                database.getReference().child("Chat")
                                        .child(receiverRoom)
                                        .push()
                                        .setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                            }
                                        });
                            }
                        });


            }
        });
    }
}