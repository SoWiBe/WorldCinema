package com.example.batumicinema.network.service;

import com.example.batumicinema.network.models.ChatResponse;
import com.example.batumicinema.network.models.LoginBody;
import com.example.batumicinema.network.models.LoginResponse;
import com.example.batumicinema.network.models.MovieCoverResponse;
import com.example.batumicinema.network.models.MovieResponse;
import com.example.batumicinema.network.models.ProfileResponse;
import com.example.batumicinema.network.models.RegistrationBody;
import com.example.batumicinema.network.models.RegistrationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IApiService {
    @POST("login")
    Call<LoginResponse> doLogin(@Body LoginBody loginBody);
    @POST("register")
    Call<RegistrationResponse> doRegistration(@Body RegistrationBody registerBody);
    @GET("cover")
    Call<MovieCoverResponse> getCovers();
    @GET("movies?filter=inTrend")
    Call<List<MovieResponse>> getMovies();
    @GET("user")
    Call<List<ProfileResponse>> getData(@Header("Authorization") String token);
    @GET("chats/{movieId}")
    Call<List<ChatResponse>> getChats(@Path("movieId") String movieId);
}
