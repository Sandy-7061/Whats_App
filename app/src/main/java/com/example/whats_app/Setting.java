package com.example.whats_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whats_app.Modal.Users;
import com.example.whats_app.databinding.ActivitySettingBinding; // Import the generated binding class.
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class Setting extends AppCompatActivity {
  ActivitySettingBinding binding;
  FirebaseAuth auth;
  FirebaseDatabase database;
  FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater()); // Inflate the binding.
        setContentView(binding.getRoot()); // Set the root view of the binding as the content view.

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        binding.baclArrowSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Setting.this, MainActivity.class));
            }
        });

        binding.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });

        // to Get Profile pic form firabase
        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                Picasso.get().load(user.getProgilepic())
                        .placeholder(R.drawable.account_avatar_profile_user_3_svgrepo_com)
                        .into(binding.profilePicImg);

                binding.edtAboutYou.setText(user.getStatus());
                binding.edtUsernameSetting.setText(user.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updated_name = binding.edtUsernameSetting.getText().toString();
                String about = binding.edtAboutYou.getText().toString();

                HashMap<String,Object> obj = new HashMap<>();
                obj.put("name",updated_name);
                obj.put("status",about);

                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).updateChildren(obj);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData() != null){
            Uri sfile = data.getData();
            binding.profilePicImg.setImageURI(sfile);

            final StorageReference refrance = storage.getReference().child("Profile_Pictures").child(FirebaseAuth.getInstance().getUid());

            refrance.putFile(sfile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    refrance.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("progilepic").setValue(uri.toString());
                            Toast.makeText(Setting.this, "Profile Changed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}
