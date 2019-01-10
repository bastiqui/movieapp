package com.example.bastiqui.moviesapp.database;

import android.support.annotation.NonNull;

public class WatchlistModel {
    private String id;
    private String name;
    private String image;
    private String type;
    private String vote_average;
    private String date;

    public WatchlistModel(String id, String name, String image, String type, String vote_average, String date) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
        this.vote_average = vote_average;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        return "WatchlistModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", type='" + type + '\'' +
                ", vote_average='" + vote_average + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
