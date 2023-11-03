package com.example.whats_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.whats_app.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import com.example.whats_app.Adapters.FragmentAdapter;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();

        // Set the toolbar as the ActionBar
        setSupportActionBar(binding.myToolbarOrignal);

        // Hide the default ActionBar
        binding.viewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        binding.tablayout.setupWithViewPager(binding.viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus,menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itm = item.getItemId();
        if(itm == R.id.Setting){
            startActivity(new Intent(MainActivity.this, Setting.class));
        } else if (itm == R.id.log_out) {
            auth.signOut();
            startActivity(new Intent(MainActivity.this, Sign_in.class));
        }
        return super.onOptionsItemSelected(item);
    }
}




