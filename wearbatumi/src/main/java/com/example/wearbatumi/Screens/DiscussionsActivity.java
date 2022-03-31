package com.example.wearbatumi.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.wearbatumi.Adapters.DiscussionsAdapter;
import com.example.wearbatumi.Adapters.MovieAdapter;
import com.example.wearbatumi.Network.Modules.MovieResponse;
import com.example.wearbatumi.Network.MovieHandler;
import com.example.wearbatumi.Network.Service.IApiService;
import com.example.wearbatumi.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscussionsActivity extends AppCompatActivity {

    private ArrayList<MovieResponse> movieResponses;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DiscussionsAdapter movieAdapter;

    private IApiService serviceMovie = MovieHandler.getInstance().getService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussions);

        recyclerView = findViewById(R.id.recyclerDiscussions);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        getMovies();
    }

    private void getMovies() {
        AsyncTask.execute(() -> {
            serviceMovie.getMovies().enqueue(new Callback<List<MovieResponse>>() {
                @Override
                public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {
                    if(response.isSuccessful()){
                        movieResponses = new ArrayList<>(response.body());
                        movieAdapter = new DiscussionsAdapter(movieResponses, DiscussionsActivity.this);
                        recyclerView.setAdapter(movieAdapter);
                        movieAdapter.notifyDataSetChanged();
                    } else if (response.code() == 400) {
                        Toast.makeText(DiscussionsActivity.this, "ПРоизошла ошибка", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DiscussionsActivity.this, "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
                    Toast.makeText(DiscussionsActivity.this,t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("SomeFragment", "onFailure: " + t.getLocalizedMessage());
                }
            });
        });
    }
}