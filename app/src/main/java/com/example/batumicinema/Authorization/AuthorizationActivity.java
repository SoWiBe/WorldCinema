package com.example.batumicinema.Authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.batumicinema.MainMenu.MainActivity;
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

    //TAG необходим для корректной работы TOAST
    private static final String TAG = "AuthorizationActivity";
    //поля ввода email and пароля
    private TextInputEditText editEmail, editPassword;

    //необходимо для записи данных в память
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    //нужный токен для входа
    private String token;

    //переменная сервиса, для работы с api
    private IApiService service = ApiHandler.getInstance().getService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        //задаем имена editor и preferences для записи и получения данных
        editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        preferences = getSharedPreferences("token", MODE_PRIVATE);
        //инициализируем поля ввода пароля и email
        initializeViews();
        //пытаемся сразу получить токен, второй параметр отвечает за значение, которое мы получим, когда в token пусто
        token = preferences.getString("token", "");
        //если токен записан, то заходим в меню
        if(token != ""){
            //метод запуска меню
            startMenu();
        }
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
        doLogin();
    }

    //получаем токен
    private void doLogin(){
        //асинхронно запускаем метод doLogin, которому передаем объект с email и password
        AsyncTask.execute(() -> {
            service.doLogin(getLoginData()).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    //если запрос на получение токена успешен
                    if(response.isSuccessful()){
                        //записываем токен в наш editor
                        //теперь при повторном входе в onCreate мы попадем в if и сразу откроется основное меню
                        editor.putString("token", response.body().getToken()).apply();
                        startMenu();
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


    public void startMenu() {
        startActivity(new Intent(AuthorizationActivity.this, MainActivity.class));
        finish();
    }

    public LoginBody getLoginData() {
        return new LoginBody(editEmail.getText().toString(), editPassword.getText().toString());
    }
}