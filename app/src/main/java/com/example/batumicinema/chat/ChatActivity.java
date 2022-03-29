package com.example.batumicinema.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.batumicinema.Discussions.DiscussionsActivity;
import com.example.batumicinema.R;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    public void goToDiscussion(View view) {
        startActivity(new Intent(ChatActivity.this, DiscussionsActivity.class));
        finish();
    }
}