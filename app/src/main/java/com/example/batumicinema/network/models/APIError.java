package com.example.batumicinema.network.models;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class APIError {
    private String error;

    public APIError(String error) {
        this.error = error;
    }

    public String message(){
        return error;
    }
}
