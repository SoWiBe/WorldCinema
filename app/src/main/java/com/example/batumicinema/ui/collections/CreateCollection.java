package com.example.batumicinema.ui.collections;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.batumicinema.R;

public class CreateCollection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collection);
    }

    public void goBack(View view) {
        finish();
    }
}