package com.example.batumicinema.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.batumicinema.Authorization.AuthorizationActivity;
import com.example.batumicinema.R;
import com.example.batumicinema.adapters.CoversAdapter;
import com.example.batumicinema.network.ApiHandler;
import com.example.batumicinema.network.ErrorUtils;
import com.example.batumicinema.network.MovieCoverHandler;
import com.example.batumicinema.network.models.LoginResponse;
import com.example.batumicinema.network.models.MovieCoverResponse;
import com.example.batumicinema.network.service.IApiService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private TextInputEditText editEmail, editPassword;
    private ArrayList<MovieCoverResponse> movieCoverResponses;
    private RecyclerView recyclerView;
    private CoversAdapter coversAdapter;

    private boolean isSignIn = false;
    IApiService service = MovieCoverHandler.getInstance().getService();
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
        movieCoverResponses = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        coversAdapter = new CoversAdapter(movieCoverResponses, getContext());
        recyclerView.setAdapter(coversAdapter);
        getCovers();
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
                        coversAdapter.notifyDataSetChanged();
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
}