package com.example.batumicinema.network.models;

import com.google.gson.annotations.SerializedName;


//класс, необходимый для отправки в post-запрос и получения токена пользователя
public class LoginBody {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public LoginBody(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
