package com.example.batumicinema.Discussions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.batumicinema.R;
import com.example.batumicinema.adapters.DiscussionsAdapter;
import com.example.batumicinema.network.ChatHandler;
import com.example.batumicinema.network.service.IApiService;

import java.util.ArrayList;

public class DiscussionsActivity extends AppCompatActivity {

    private RecyclerView recyclerDiscussions;
    private LinearLayoutManager layoutManager;
    private DiscussionsAdapter discussionsAdapter;
    private ArrayList<Discussion> discussionsList = new ArrayList<>();;
    private IApiService service = ChatHandler.getInstance().getService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussions);

        recyclerDiscussions = findViewById(R.id.recyclerDiscussions);

        discussionsList.add(new Discussion(R.drawable.disc_arraw, "Стрела", "Иван: Смотрели уже последнюю серию? Я просто поверить не могу в..."));
        discussionsList.add(new Discussion(R.drawable.disc_arraw, "Стрела", "Иван: Смотрели уже последнюю серию? Я просто поверить не могу в..."));
        discussionsList.add(new Discussion(R.drawable.disc_arraw, "Стрела", "Иван: Смотрели уже последнюю серию? Я просто поверить не могу в..."));

        layoutManager = new LinearLayoutManager(DiscussionsActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerDiscussions.setLayoutManager(layoutManager);

        discussionsAdapter = new DiscussionsAdapter(discussionsList, DiscussionsActivity.this);
        recyclerDiscussions.setAdapter(discussionsAdapter);
    }

    public void goToProfile(View view) {
        finish();
    }

}