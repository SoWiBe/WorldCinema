package com.example.batumicinema.Authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.batumicinema.MainMenu.MainActivity;
import com.example.batumicinema.MainMenu.MainMenu;
import com.example.batumicinema.R;
import com.example.batumicinema.network.ApiHandler;
import com.example.batumicinema.network.ErrorUtils;
import com.example.batumicinema.network.models.LoginBody;
import com.example.batumicinema.network.models.LoginResponse;
import com.example.batumicinema.network.service.IApiService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorizationActivity extends AppCompatActivity {

    private static final String TAG = "AuthorizationActivity";
    private TextInputEditText editEmail, editPassword;

    IApiService service = ApiHandler.getInstance().getService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        initializeViews();
    }


    private void initializeViews() {
        editEmail = findViewById(R.id.text_text_email);
        editPassword = findViewById(R.id.text_text_password);

    }

    public void goToSignUp(View view) {
        startActivity(new Intent(AuthorizationActivity.this, SignInActivity.class));
        finish();
    }

    public void goToMenu(View view) {
//        startActivity(new Intent(AuthorizationActivity.this, MainActivity.class));
//        finish();
        doLogin();

    }
    private void doLogin(){
        AsyncTask.execute(() -> {
            service.doLogin(getLoginData()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(AuthorizationActivity.this, "Авторизация прошла успешно!"+response.body().getToken(), Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 400) {
                        String serverErrorMessage = ErrorUtils.parseError(response).message();
                        Toast.makeText(AuthorizationActivity.this, serverErrorMessage.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AuthorizationActivity.this, "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(AuthorizationActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    public LoginBody getLoginData() {
        return new LoginBody(editEmail.getText().toString(), editPassword.getText().toString());
    }
}