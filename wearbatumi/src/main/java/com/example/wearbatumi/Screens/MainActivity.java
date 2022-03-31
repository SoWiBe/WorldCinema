package com.example.wearbatumi.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wearbatumi.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearFilms;
    private LinearLayout linearDiscussions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearFilms = findViewById(R.id.linear_discussions);
        linearFilms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MoviesScreen.class));
            }
        });
        linearDiscussions = findViewById(R.id.linear_chats);
        linearDiscussions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DiscussionsActivity.class));
            }
        });
    }

}