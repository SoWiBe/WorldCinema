package com.example.batumicinema.ui.collections;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.batumicinema.R;
import com.google.android.material.button.MaterialButton;

public class CreateCollection extends AppCompatActivity {

    private MaterialButton btnChooseIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collection);

        btnChooseIcon = findViewById(R.id.btnChooseIcon);
        btnChooseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateCollection.this, ChooseIconActivity.class));
            }
        });
    }

    public void goBack(View view) {
        finish();
    }
}