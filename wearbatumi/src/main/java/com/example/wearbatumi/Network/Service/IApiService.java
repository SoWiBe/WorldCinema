package com.example.wearbatumi.Network.Service;

import com.example.wearbatumi.Network.Modules.LoginBody;
import com.example.wearbatumi.Network.Modules.LoginResponse;
import com.example.wearbatumi.Network.Modules.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IApiService {
    @POST("login")
    Call<LoginResponse> doLogin(@Body LoginBody loginBody);
    @GET("movies?filter=new")
    Call<List<MovieResponse>> getMovies();
}
