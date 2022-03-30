package com.example.batumicinema.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.batumicinema.Discussions.DiscussionsActivity;
import com.example.batumicinema.R;
import com.example.batumicinema.adapters.ChatAdapter;
import com.example.batumicinema.adapters.MovieAdapter;
import com.example.batumicinema.network.ChatHandler;
import com.example.batumicinema.network.ErrorUtils;
import com.example.batumicinema.network.models.ChatMessageResponse;
import com.example.batumicinema.network.models.ChatResponse;
import com.example.batumicinema.network.models.MovieResponse;
import com.example.batumicinema.network.service.IApiService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    private FrameLayout frameSendImage;
    private ChatAdapter chatAdapter;
    private List<ChatItem> chatItems;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private TextInputEditText message;
    private String movieId, chatId;
    private Bundle bundle;
    private TextView txtNameMovie;
    private String token;
    private IApiService service = ChatHandler.getInstance().getService();
    private SharedPreferences sharedPreferences;
    private String myFirstName, mySecondName;

    private ArrayList<ChatMessageResponse> chatMessageResponses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        myFirstName = sharedPreferences.getString("firstName", "");
        mySecondName
        Toast.makeText(this, sharedPreferences.getString("firstName", ""), Toast.LENGTH_SHORT).show();
        txtNameMovie = findViewById(R.id.txtChatName);
        bundle = getIntent().getExtras();
        if(bundle!=null){
            movieId = bundle.getString("movieId");
            getChats(movieId);
            Toast.makeText(this, "" + movieId, Toast.LENGTH_SHORT).show();
        }

        recyclerView = findViewById(R.id.recyclerChat);
        linearLayoutManager = new LinearLayoutManager(this);
        message = findViewById(R.id.textEditMessage);
        recyclerView.setLayoutManager(linearLayoutManager);
        chatItems = new ArrayList<>();
        chatItems.add(new ChatItem(ChatItem.LayoutTwo, "Завтра уже выйдет финальная серия!!!"));
        chatAdapter = new ChatAdapter(chatItems);
        recyclerView.setAdapter(chatAdapter);

        frameSendImage = findViewById(R.id.frameSendImage);
        frameSendImage.setOnClickListener(view -> {
            chatItems.add(new ChatItem(ChatItem.LayoutTwo, message.getText().toString()));
            chatAdapter.notifyDataSetChanged();
        });
    }

    public void goToDiscussion(View view) {
        startActivity(new Intent(ChatActivity.this, DiscussionsActivity.class));
        finish();
    }

    private void getChats(String movieId) {
        AsyncTask.execute(() -> {
            service.getChats(movieId).enqueue(new Callback<List<ChatResponse>>() {
                @Override
                public void onResponse(Call<List<ChatResponse>> call, Response<List<ChatResponse>> response) {
                    if(response.isSuccessful()){
                        if(response.body().size() == 0){
                            Toast.makeText(ChatActivity.this, "Такого чата нет!", Toast.LENGTH_SHORT).show();
                        } else {
                            txtNameMovie.setText(response.body().get(0).getName());
                            chatId = response.body().get(0).getChatId();
                            getChatMessages(token, chatId);
                        }
                    } else if (response.code() == 400) {
                        Toast.makeText(getApplicationContext(), "AAAA", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<ChatResponse>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Произ22ошла неизвестная ошибка2222", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void getChatMessages(String token, String chatId) {
        AsyncTask.execute(() -> {
            service.getChatMessages(token, chatId).enqueue(new Callback<List<ChatMessageResponse>>() {
                @Override
                public void onResponse(Call<List<ChatMessageResponse>> call, Response<List<ChatMessageResponse>> response) {
                    if(response.isSuccessful()){
                        chatMessageResponses = new ArrayList<>(response.body());
                    } else if (response.code() == 400) {
                        Toast.makeText(getApplicationContext(), "AAAA", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "22 неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<ChatMessageResponse>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Произо222шла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}