package com.example.batumicinema.network.models;

import com.google.gson.annotations.SerializedName;

public class Tag {
    @SerializedName("idTags")
    private String idTags;
    @SerializedName("tagName")
    private String tagName;

    public String getIdTags() {
        return idTags;
    }

    public void setIdTags(String idTags) {
        this.idTags = idTags;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
