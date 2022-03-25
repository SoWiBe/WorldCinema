package com.example.batumicinema.network.service;

import com.example.batumicinema.network.models.LoginBody;
import com.example.batumicinema.network.models.LoginResponse;
import com.example.batumicinema.network.models.MovieCoverResponse;
import com.example.batumicinema.network.models.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IApiService {
    @POST("login")
    Call<LoginResponse> doLogin(@Body LoginBody registerBody);
    @GET("cover")
    Call<MovieCoverResponse> getCovers();
    @GET("movies?filter=inTrend")
    Call<List<MovieResponse>> getMovies();
}
