package com.example.batumicinema.network;

import android.os.AsyncTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SafeCaller {

//    public void safeCall(Call<T> apiCall, Runnable onSuccess, Runnable onServerError, Runnable onUnexpectedError) {
//        AsyncTask.execute(() -> {
//            apiCall.enqueue(new Callback<T>() {
//                @Override
//                public void onResponse(Call<T> call, Response<T> response) {
//                    if(response.isSuccessful()){
//                        onSuccess.run();
//                    } else if (response.code() == 400) {
//                        onServerError.run();
//                    } else {
//                        onUnexpectedError.run();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<T> call, Throwable t) {
//                    onUnexpectedError.run();
//                }
//            });
//        });
//    }
}
