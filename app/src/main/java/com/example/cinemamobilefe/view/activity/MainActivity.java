package com.example.cinemamobilefe.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cinemamobilefe.Data_local.DataLocalManager;
import com.example.cinemamobilefe.R;
import com.example.cinemamobilefe.databinding.ActivityMainBinding;
import com.example.cinemamobilefe.model.UserModel;
import com.example.cinemamobilefe.view.fragment.FragmentHome;
import com.example.cinemamobilefe.view.fragment.FragmentProfile;
import com.example.cinemamobilefe.view.fragment.FragmentTickets;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFrg(new FragmentHome());
        userModel = DataLocalManager.getUser();
        binding.navBottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.it_home) {
                    replaceFrg(new FragmentHome());
                } else if (menuItem.getItemId() == R.id.it_tickets) {
                    if (userModel != null) {
                        replaceFrg(new FragmentTickets());
                    } else {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                } else if (menuItem.getItemId() == R.id.it_profile) {
                    if (userModel != null) {
                        replaceFrg(new FragmentProfile());
                    } else {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
                return true;
            }
        });
//        binding.navBottom.setBackgroundColor(Color.TRANSPARENT);
    }
    public void replaceFrg(Fragment frg) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frg_main, frg).commit();
    }
}