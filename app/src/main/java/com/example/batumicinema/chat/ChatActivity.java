package com.example.batumicinema.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.batumicinema.Discussions.DiscussionsActivity;
import com.example.batumicinema.R;
import com.example.batumicinema.adapters.ChatAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private FrameLayout frameSendImage;
    private ChatAdapter chatAdapter;
    private List<ChatItem> chatItems;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private TextInputEditText message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        recyclerView = findViewById(R.id.recyclerChat);
        linearLayoutManager = new LinearLayoutManager(this);
        message = findViewById(R.id.textEditMessage);
        recyclerView.setLayoutManager(linearLayoutManager);
        chatItems = new ArrayList<>();
        chatItems.add(new ChatItem(ChatItem.LayoutOne, "Завтра уже выйдет финальная серия!!!"));
        chatAdapter = new ChatAdapter(chatItems);
        recyclerView.setAdapter(chatAdapter);

        frameSendImage = findViewById(R.id.frameSendImage);
        frameSendImage.setOnClickListener(view -> {
            chatItems.add(new ChatItem(ChatItem.LayoutOne, message.getText().toString()));
            chatAdapter.notifyDataSetChanged();
        });
    }

    public void goToDiscussion(View view) {
        startActivity(new Intent(ChatActivity.this, DiscussionsActivity.class));
        finish();
    }
}