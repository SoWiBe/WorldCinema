package com.example.batumicinema.network;

import com.example.batumicinema.network.models.ProfileResponse;
import com.example.batumicinema.network.service.IApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//связь retrofit с api для получения данных, весь connection здесь
public class ProfileHandler {
    private static ProfileHandler mInstance;

    private static String BASE_URL = "http://cinema.areas.su/";

    private Retrofit retrofit;

    public ProfileHandler() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ProfileHandler getInstance() {
        if (mInstance == null) {
            mInstance = new ProfileHandler();
        }
        return mInstance;
    }

    public IApiService getService() {
        return retrofit.create(IApiService.class);
    }
}
