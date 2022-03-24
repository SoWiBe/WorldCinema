package com.example.batumicinema.network.models;

import com.google.gson.annotations.SerializedName;

public class MovieResponse {
    @SerializedName("movies")
    private String movieId;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String age;
    @SerializedName("images")
    private String[] images;
    @SerializedName("poster")
    private String poster;
    @SerializedName("tags")
    private Tag[] tags;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }
}
