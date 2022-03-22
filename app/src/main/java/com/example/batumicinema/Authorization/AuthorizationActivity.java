package com.example.batumicinema.Authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.batumicinema.MainMenu.MainActivity;
import com.example.batumicinema.MainMenu.MainMenu;
import com.example.batumicinema.R;

public class AuthorizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
    }

    public void goToSignUp(View view) {
        startActivity(new Intent(AuthorizationActivity.this, SignInActivity.class));
        finish();
    }

    public void goToMenu(View view) {
        startActivity(new Intent(AuthorizationActivity.this, MainActivity.class));
        finish();
    }
}