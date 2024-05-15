package com.example.watttracker;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.watttracker.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Replace the initial fragment with the calcFragment
        replaceFragment(new calcFragment());

        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        navView.setItemIconTintList(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            // Handle bottom navigation item selection
            int itemId = item.getItemId();
            if (itemId == R.id.info) {
                replaceFragment(new infoFragment());
            } else if (itemId == R.id.calc) {
                replaceFragment(new calcFragment());
            } else if (itemId == R.id.profile) {
                replaceFragment(new profileFragment());
            }
            return true;
        });

        // Set the selected item in the bottom navigation to calc
        binding.bottomNavigationView.setSelectedItemId(R.id.calc);
    }

    private void replaceFragment(Fragment fragment) {
        // Replace the current fragment with the specified fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}