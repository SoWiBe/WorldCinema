package com.example.batumicinema.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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
import com.example.batumicinema.network.models.MessageBody;
import com.example.batumicinema.network.models.MovieResponse;
import com.example.batumicinema.network.service.IApiService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    //отвечает за нажатие кнопки отправки
    private FrameLayout frameSendImage;
    //адаптер для чата
    private ChatAdapter chatAdapter;
    //менеджер для recyclerView
    private LinearLayoutManager linearLayoutManager;
    //отвечает за заполнение чата
    private RecyclerView recyclerView;
    //поле ввода сообщения
    private TextInputEditText message;
    //данные для отправки в запросы
    private String movieId, chatId;
    //получение данных через intent
    private Bundle bundle;
    //название фильма
    private TextView txtNameMovie;
    //токен, нужен здесь для получения данных чата
    private String token;
    //сервис с чат запросами
    private IApiService service = ChatHandler.getInstance().getService();
    //получение данных юзера
    private SharedPreferences sharedPreferences;
    //данные имени и фамилии
    private String myFirstName, mySecondName;

    private ArrayList<ChatMessageResponse> chatMessageResponses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //инициализируем поля
        txtNameMovie = findViewById(R.id.txtChatName);
        message = findViewById(R.id.textEditMessage);
        recyclerView = findViewById(R.id.recyclerChat);
        frameSendImage = findViewById(R.id.frameSendImage);

        //получение токена
        sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        //получение имени и фамилии
        myFirstName = sharedPreferences.getString("firstName", "");
        mySecondName = sharedPreferences.getString("lastName", "");

        //получаем название фильма
        bundle = getIntent().getExtras();
        if(bundle!=null){
            movieId = bundle.getString("movieId");
            getChats(movieId);
        }

        //создаем manager и подклчаем его к recyclerView
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getChatMessages(token, chatId);
            }
        }, 0,3000);

        frameSendImage.setOnClickListener(view -> {
            sendMessage(token, chatId, new MessageBody(message.getText().toString()));
            getChatMessages(token, chatId);
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
//                    Toast.makeText(getApplicationContext(), "Произ22ошла неизвестная ошибка2222", Toast.LENGTH_SHORT).show();
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
                        for (int i = 0; i < response.body().size(); i++) {
                            chatMessageResponses.get(i).setViewType(1);
                             if(chatMessageResponses.get(i).getFirstName().equals(myFirstName) &&
                                     chatMessageResponses.get(i).getLastName().equals(mySecondName) ) {
                                 chatMessageResponses.get(i).setViewType(0);
                             }
                        }
                        chatAdapter = new ChatAdapter(chatMessageResponses);
                        recyclerView.setAdapter(chatAdapter);
                        recyclerView.scrollToPosition(chatMessageResponses.size() - 1);
                    } else if (response.code() == 400) {
                        Toast.makeText(getApplicationContext(), "AAAA", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "22 неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<List<ChatMessageResponse>> call, Throwable t) {
//                    Toast.makeText(getApplicationContext(), "Произо222шла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void sendMessage(String token, String chatId, MessageBody text) {
        AsyncTask.execute(() -> {
            service.doMessage(token, chatId, text).enqueue(new Callback<List<ChatMessageResponse>>() {
                @Override
                public void onResponse(Call<List<ChatMessageResponse>> call, Response<List<ChatMessageResponse>> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "sdfdsfsd", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 400) {
                        Toast.makeText(getApplicationContext(), "AAAA", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<ChatMessageResponse>> call, Throwable t) {

                }
            });
        });
    }
}