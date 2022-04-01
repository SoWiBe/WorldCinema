package com.example.batumicinema.network;

import com.example.batumicinema.network.service.IApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//связь retrofit с api для получения данных, весь connection здесь
public class MovieHandler {
    private static MovieHandler mInstance;

    private static final String BASE_URL = "http://cinema.areas.su/";

    private Retrofit retrofit;

    public MovieHandler(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ErrorUtils.retrofit = retrofit;

    }

    public static MovieHandler getInstance() {
        if(mInstance == null){
            mInstance = new MovieHandler();
        }
        return mInstance;
    }

    public IApiService getService() {
        return retrofit.create(IApiService.class);
    }
}
