package com.example.batumicinema.MainMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.batumicinema.R;
import com.example.batumicinema.ui.collections.CollectionsFragment;
import com.example.batumicinema.ui.compilation.CompilationFragment;
import com.example.batumicinema.ui.home.HomeFragment;
import com.example.batumicinema.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    private final Fragment homeFragment = new HomeFragment();
    private final Fragment compilFragment = new CompilationFragment();
    private final Fragment collectionsFragment = new CollectionsFragment();
    private final Fragment profileFragment = new ProfileFragment();
    private Fragment active = homeFragment;
    private FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.main_container, homeFragment, "home").commit();
        fragmentManager.beginTransaction().add(R.id.main_container, compilFragment, "compilation").hide(compilFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, collectionsFragment, "collections").hide(collectionsFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, profileFragment, "profile").hide(profileFragment).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(onNavigationItemSelectedListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.page_1:
                    fragmentManager.beginTransaction().hide(active).show(homeFragment).commit();
                    active = homeFragment;
                    return true;
                case R.id.page_2:
                    fragmentManager.beginTransaction().hide(active).show(compilFragment).commit();
                    active = compilFragment;
                    return true;
                case R.id.page_3:
                    fragmentManager.beginTransaction().hide(active).show(collectionsFragment).commit();
                    active = collectionsFragment;
                    return true;
                case R.id.page_4:
                    fragmentManager.beginTransaction().hide(active).show(profileFragment).commit();
                    active = profileFragment;
                    return true;
            }
            return false;
        }
    };


}