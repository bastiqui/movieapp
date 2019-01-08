package com.example.bastiqui.moviesapp.database;

import java.sql.Blob;

public class StoreImage {
    private String id;
    private Blob image;

    public StoreImage() {}

    public StoreImage (String id) {
        this.id = id;
    }

    public StoreImage(String id, Blob image) {
        this.id = id;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "StoreImage{" +
                "id='" + id + '\'' +
                ", image=" + image +
                '}';
    }
}
