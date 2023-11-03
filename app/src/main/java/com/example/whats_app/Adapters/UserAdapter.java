package com.example.whats_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whats_app.Chats_Detail;
import com.example.whats_app.Modal.Message;
import com.example.whats_app.Modal.Users;
import com.example.whats_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    ArrayList<Users> list;
    Context context;

    public UserAdapter(ArrayList<Users> list,Context context){
            this.list = list;
            this.context =  context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_user,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Users users =list.get(position);
        Picasso.get().load(users.getProgilepic()).placeholder(R.drawable.account_avatar_profile_user_3_svgrepo_com).into(holder.img);
        holder.name.setText(users.getName());

        FirebaseDatabase.getInstance().getReference().child("Chat")
                .child(FirebaseAuth.getInstance().getUid() + users.getUserid())
                        .orderByChild("timestamp")
                                .limitToLast(1)
                                        .addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                                String lastMessage = snapshot.child("message").getValue().toString();
                                                holder.last_message.setText(lastMessage);
                                            }

                                            @Override
                                            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                            }

                                            @Override
                                            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                                            }

                                            @Override
                                            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Chats_Detail.class);
                intent.putExtra("user_id",users.getUserid());
                intent.putExtra("profile_pic",users.getProgilepic());
                intent.putExtra("user_name",users.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
    ImageView img;
    TextView name,last_message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.profile_image);
            name = itemView.findViewById(R.id.user_name);
            last_message = itemView.findViewById(R.id.lastmsg);
        }
    }
}
