package com.example.batumicinema.Authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.batumicinema.MainActivity;
import com.example.batumicinema.R;
import com.example.batumicinema.network.RegistrationHandler;
import com.example.batumicinema.network.models.LoginBody;
import com.example.batumicinema.network.models.RegistrationBody;
import com.example.batumicinema.network.models.RegistrationResponse;
import com.example.batumicinema.network.service.IApiService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private IApiService service = RegistrationHandler.getInstance().getService();
    private Button button;
    private TextInputEditText  editTextEmail, editTextFirstName, editTextLastName, editTextPassword, editTextRepeatPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        editTextEmail = findViewById(R.id.text_text_email);
        editTextFirstName = findViewById(R.id.text_text_name);
        editTextLastName = findViewById(R.id.text_text_second_name);
        editTextPassword = findViewById(R.id.text_text_password);
        editTextRepeatPassword = findViewById(R.id.text_text_repeat_password);
        button = findViewById(R.id.btnLogIn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRegister();
            }
        });
    }

    private void doRegister(){
        AsyncTask.execute(() -> {
            service.doRegistration(setRegistrationBody()).enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(SignInActivity.this, "succesful", Toast.LENGTH_SHORT).show();
                        startMainMenu();
                    }
                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {

                }
            });
        });
    }

    private RegistrationBody setRegistrationBody() {
        return new RegistrationBody(editTextEmail.getText().toString(), editTextPassword.getText().toString(),
                editTextFirstName.getText().toString(), editTextLastName.getText().toString());
    }

    private void startMainMenu(){
        startActivity(new Intent(SignInActivity.this, MainActivity.class));
        finish();
    }
}