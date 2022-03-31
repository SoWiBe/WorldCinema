package com.example.wearbatumi.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wearbatumi.Network.Modules.LoginBody;
import com.example.wearbatumi.Network.Modules.LoginResponse;
import com.example.wearbatumi.Network.Service.IApiService;
import com.example.wearbatumi.Network.SignInHandler;
import com.example.wearbatumi.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private IApiService service = SignInHandler.getInstance().getService();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextInputEditText textEditEmail, textEditPassword;
    private String token;
    private Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        sharedPreferences = getSharedPreferences("token", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        textEditEmail = findViewById(R.id.text_edit_email);
        textEditPassword = findViewById(R.id.text_edit_password);
        btnSignIn = findViewById(R.id.btnLogIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
        if(token != ""){
            startMenu();
        }
    }

    public void goToMenu(View view) {
    }
    private void startMenu() {
        startActivity(new Intent(SignInActivity.this, MainActivity.class));
        finish();
    }
    private void doLogin(){
        AsyncTask.execute(() -> {
            service.doLogin(new LoginBody(textEditEmail.getText().toString(),
                    textEditPassword.getText().toString())).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.isSuccessful()){
                        editor.putString("token", response.body().getToken()).apply();
                        startMenu();
                    } else if (response.code() == 400) {
                        Toast.makeText(SignInActivity.this, "Content", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignInActivity.this, "Произошла неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(SignInActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

}