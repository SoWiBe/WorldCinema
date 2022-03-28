package com.example.batumicinema.network.models;

import com.google.gson.annotations.SerializedName;

public class ChatResponse {
    @SerializedName("chatId")
    private String chatId;
    @SerializedName("name")
    private String name;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
