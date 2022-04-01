package com.example.batumicinema.network;

import com.example.batumicinema.network.service.IApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//связь retrofit с api для получения данных, весь connection здесь
public class RegistrationHandler {
    private static RegistrationHandler mInstance;

    private static String BASE_URL = "http://cinema.areas.su/auth/";

    private Retrofit retrofit;

    public RegistrationHandler() {

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

    public static RegistrationHandler getInstance() {
        if (mInstance == null) {
            mInstance = new RegistrationHandler();
        }
        return mInstance;
    }

    public IApiService getService() {
        return retrofit.create(IApiService.class);
    }
}
