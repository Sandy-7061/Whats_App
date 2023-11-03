package com.example.whats_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.whats_app.Modal.Users;
import com.example.whats_app.databinding.ActivitySignInBinding;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_in extends AppCompatActivity {
    ActivitySignInBinding binding;
    ProgressDialog progressDialog;
    private FirebaseAuth auth;
    GoogleSignInClient mgooglesigninopn;
    FirebaseDatabase database;
    private static final int RC_SIGN_IN = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // auth me get instance
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(Sign_in.this);
        progressDialog.setTitle("Loging Account");
        progressDialog.setMessage("We're Loging Your Account");
        progressDialog.setIcon(R.drawable.loading);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // Use your web client ID
                .requestEmail()
                .build();
        mgooglesigninopn = GoogleSignIn.getClient(this, gso);




        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.edtPersonEmail.getText().toString().isEmpty()){
                    binding.edtPersonEmail.setError("Enter Your Email");
                    return;
                }
                if(binding.edtPersonPassword.getText().toString().isEmpty()){
                    binding.edtPersonPassword.setError("Enter Your Password");
                    return;
                }
                progressDialog.show();

                auth.signInWithEmailAndPassword(binding.edtPersonEmail.getText().toString(),binding.edtPersonPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    progressDialog.dismiss();
                                    Toast.makeText(Sign_in.this, "You Logged in Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Sign_in.this, MainActivity.class));
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(Sign_in.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                binding.edtPersonEmail.setText("");
                                binding.edtPersonPassword.setText("");
                            }
                        });
            }
        });
//        if(auth.getCurrentUser() != null){
//            startActivity(new Intent(Sign_in.this, MainActivity.class));
//        }

        binding.alradyhaveaccnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sign_in.this,Sign_up.class));
            }
        });

        // sign in with google button
        binding.googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }


        private void signIn(){
        Intent signinIntent =mgooglesigninopn.getSignInIntent();
        startActivityForResult(signinIntent, RC_SIGN_IN);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == RC_SIGN_IN){
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try{
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                        Log.d("Success","firebaseAuthwithGoogle: "+account.getId());
                        firebasebaseAuthwithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    Log.w("Failed","Google sign in failed",e);
                }
            }

        }

    private void firebasebaseAuthwithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("Google sign in success","Signed in with google");
                            FirebaseUser user = auth.getCurrentUser();
                            Users users = new Users();
                            users.setUserid(user.getUid());
                            users.setName(user.getDisplayName());
                            users.setProgilepic(user.getPhotoUrl().toString());
                            database.getReference().child("Users").child(user.getUid()).setValue(users);

                            // Now you have the authenticated user, and you can perform further actions.
                            startActivity(new Intent(Sign_in.this, MainActivity.class));
                        } else {
                            // Handle authentication failure
                            Snackbar.make(binding.getRoot(), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}