package com.example.batumicinema.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.text.method.MovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.batumicinema.Authorization.AuthorizationActivity;
import com.example.batumicinema.R;
import com.example.batumicinema.adapters.CoversAdapter;
import com.example.batumicinema.adapters.MovieAdapter;
import com.example.batumicinema.network.ApiHandler;
import com.example.batumicinema.network.ErrorUtils;
import com.example.batumicinema.network.MovieCoverHandler;
import com.example.batumicinema.network.MovieHandler;
import com.example.batumicinema.network.models.LoginResponse;
import com.example.batumicinema.network.models.MovieCoverResponse;
import com.example.batumicinema.network.models.MovieResponse;
import com.example.batumicinema.network.service.IApiService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private TextInputEditText editEmail, editPassword;
    private ArrayList<MovieCoverResponse> movieCoverResponses;
    private ArrayList<MovieResponse> movieResponses;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CoversAdapter coversAdapter;
    private MovieAdapter movieAdapter;

    private boolean isSignIn = false;
    IApiService service = MovieCoverHandler.getInstance().getService();
    IApiService serviceMovie = MovieHandler.getInstance().getService();
    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        getMovies();
        return view;
    }

    private void getCovers(){
        AsyncTask.execute(() -> {
            service.getCovers().enqueue(new Callback<MovieCoverResponse>() {
                @Override
                public void onResponse(Call<MovieCoverResponse> call, Response<MovieCoverResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getContext(), "EHFF!"+response.body().getBackgroundImage(), Toast.LENGTH_SHORT).show();
                        movieCoverResponses.add(response.body());
                        movieAdapter.notifyDataSetChanged();
                    } else if (response.code() == 400) {
                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Log.d(TAG, serverErrorMessage.toString() + " || " + response.code());
                        Toast.makeText(getContext(), serverErrorMessage.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MovieCoverResponse> call, Throwable t) {
                    Toast.makeText(getContext(),t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void getMovies() {
        AsyncTask.execute(() -> {
            serviceMovie.getMovies().enqueue(new Callback<List<MovieResponse>>() {
                @Override
                public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {
                    if(response.isSuccessful()){
                        movieResponses = new ArrayList<>(response.body());
                        movieAdapter = new MovieAdapter(movieResponses, getContext());
                        recyclerView.setAdapter(movieAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        movieAdapter.notifyDataSetChanged();
                    } else if (response.code() == 400) {
                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Log.d(TAG, serverErrorMessage.toString() + " || " + response.code());
                        Toast.makeText(getContext(), serverErrorMessage.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
                    Toast.makeText(getContext(),t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });
        });
    }
}