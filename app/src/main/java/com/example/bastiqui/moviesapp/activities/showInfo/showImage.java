package com.example.bastiqui.moviesapp.activities.showInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bastiqui.moviesapp.GlideApp;
import com.example.bastiqui.moviesapp.R;

import java.util.Objects;

public class showImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        Intent getIntent = getIntent();

        String uri = Objects.requireNonNull(getIntent.getExtras()).getString("image");

        com.jsibbold.zoomage.ZoomageView imageView = findViewById(R.id.showImage);

        GlideApp.with(this)
                .load("https://image.tmdb.org/t/p/w500/" + uri)
                .into(imageView);
    }
}