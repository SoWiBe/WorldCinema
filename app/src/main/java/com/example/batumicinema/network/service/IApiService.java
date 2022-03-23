package com.example.batumicinema.network.service;

import com.example.batumicinema.network.models.LoginBody;
import com.example.batumicinema.network.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IApiService {
    @POST("login")
    Call<LoginResponse> doLogin(@Body LoginBody registerBody);
}
