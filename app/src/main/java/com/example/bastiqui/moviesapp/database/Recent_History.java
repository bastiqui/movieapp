package com.example.bastiqui.moviesapp.database;

public class Recent_History {
    private String id;
    private String name;
    private String type;
    private String vote_average;
    private String date;

    public Recent_History() {}

    public Recent_History(String id, String name, String type, String vote_average, String date) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Recent_History{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", vote_average='" + vote_average + '\'' +
                ", date=" + date +
                '}';
    }
}
