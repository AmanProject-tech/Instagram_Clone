package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.firebase.Fragments.HomeFragment;
import com.example.firebase.Fragments.NotificationFragment;
import com.example.firebase.Fragments.ProfileFragment;
import com.example.firebase.Fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    selectorFragment = new HomeFragment();
                    break;

                case R.id.nav_search:
                    selectorFragment = new SearchFragment();
                    break;

                case R.id.nav_add:
                    selectorFragment = null;
                    startActivity(new Intent(MainActivity.this, PostActivity.class));
                    break;

                case R.id.nav_heart:
                    selectorFragment = new NotificationFragment();
                    break;

                case R.id.nav_profile:
                    selectorFragment = new ProfileFragment();
                    break;
            }

            if (selectorFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectorFragment).commit();
            }

            return true;
        });
        Bundle intent = getIntent().getExtras();
        if(intent != null){
            String profileId = intent.getString("publisherId");
            getSharedPreferences("PROFILE", MODE_PRIVATE).edit().putString("profiledId", profileId).apply();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.nav_profile);
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
        }


    }
}
