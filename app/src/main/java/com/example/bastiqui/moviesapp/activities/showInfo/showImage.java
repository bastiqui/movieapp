package com.example.bastiqui.moviesapp.activities.showInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.bastiqui.moviesapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class showImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        Intent getIntent = getIntent();

        String uri = Objects.requireNonNull(getIntent.getExtras()).getString("image");

        com.jsibbold.zoomage.ZoomageView imageView = findViewById(R.id.showImage);

        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/original/" + uri)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(imageView);
    }
}