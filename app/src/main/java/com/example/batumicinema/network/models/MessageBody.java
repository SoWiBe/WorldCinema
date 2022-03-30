package com.example.batumicinema.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageBody {
    @SerializedName("text")
    @Expose
    private String text;

    public MessageBody(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
