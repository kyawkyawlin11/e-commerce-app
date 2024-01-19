package com.visibleone.ecommerce.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.visibleone.ecommerce.R;
import com.visibleone.ecommerce.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
                if(item.getItemId() == R.id.home_page) {
                    replaceFragment(new HomeFragment());
                    return true;
                }
                if(item.getItemId() == R.id.cart_page) {
                    replaceFragment(new HomeFragment());
                    return true;
                }
                if(item.getItemId() == R.id.favourite_page) {
                    replaceFragment(new HomeFragment());
                    return true;
                }
                if(item.getItemId() == R.id.search_page) {
                    replaceFragment(new HomeFragment());
                    return true;
                }
            return false;
        });
    }
    private  void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mContainer, fragment);
        transaction.commit();
    }
}