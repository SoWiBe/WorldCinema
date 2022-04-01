package com.example.batumicinema.network.service;

import com.example.batumicinema.network.models.ChatMessageResponse;
import com.example.batumicinema.network.models.ChatResponse;
import com.example.batumicinema.network.models.LoginBody;
import com.example.batumicinema.network.models.LoginResponse;
import com.example.batumicinema.network.models.MessageBody;
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
    //запрос на получение token
    @POST("login")
    Call<LoginResponse> doLogin(@Body LoginBody loginBody);

    //запрос на регистрацию
    @POST("register")
    Call<RegistrationResponse> doRegistration(@Body RegistrationBody registerBody);

    //получение постов фильма (только один пост)
    @GET("cover")
    Call<MovieCoverResponse> getCovers();

    //получение фильмов
    @GET("movies?filter=forMe")
    Call<List<MovieResponse>> getMovies();

    //получение данных user
    @GET("user")
    Call<List<ProfileResponse>> getData(@Header("Authorization") String token);

    //получение список чатов юзера
    @GET("chats/{movieId}")
    Call<List<ChatResponse>> getChats(@Path("movieId") String movieId);

    //получение списка сообщений чата
    @GET("chats/{chatId}/messages")
    Call<List<ChatMessageResponse>> getChatMessages(@Header("Authorization") String token, @Path("chatId") String chatId);

    //отправка написанного сообщения
    @POST("chats/{chatId}/messages")
    Call<List<ChatMessageResponse>> doMessage(@Header("Authorization") String token, @Path("chatId") String chatId, @Body MessageBody message);
}
