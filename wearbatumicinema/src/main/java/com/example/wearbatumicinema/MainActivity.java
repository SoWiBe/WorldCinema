package com.example.wearbatumicinema;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.wearbatumicinema.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Fragment signInFragment = new SignInBlank();
    private Fragment active;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}