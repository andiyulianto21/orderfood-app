package com.example.foodorderonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.foodorderonline.databinding.ActivityMainBinding;
import com.example.foodorderonline.fragment.CartFragment;
import com.example.foodorderonline.fragment.HomeFragment;
import com.example.foodorderonline.fragment.ProfileFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(LayoutInflater.from(MainActivity.this));
        getSupportActionBar().hide();
        setContentView(mainBinding.getRoot());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(mainBinding.frameLayout.getId(), HomeFragment.class, null)
                .commit();

        mainBinding.bottomNavMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.menu_home:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(mainBinding.frameLayout.getId(), HomeFragment.class, null)
                                .commit();
                        break;
                    case R.id.menu_cart:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(mainBinding.frameLayout.getId(), CartFragment.class, null)
                                .commit();
                        break;
                    case R.id.menu_profile:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(mainBinding.frameLayout.getId(), ProfileFragment.class, null)
                                .commit();
                        break;
                }

                return true;
            }
        });
    }
}