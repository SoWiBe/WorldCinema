package com.example.batumicinema.network.models;

import com.google.gson.annotations.SerializedName;

public class MovieCoverResponse {
    @SerializedName("movieId")
    private String movieId;
    @SerializedName("backgroundImage")
    private String backgroundImage;
    @SerializedName("foregroundImage")
    private String foregroundImage;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getForegroundImage() {
        return foregroundImage;
    }

    public void setForegroundImage(String foregroundImage) {
        this.foregroundImage = foregroundImage;
    }
}
