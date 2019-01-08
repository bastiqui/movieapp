package com.example.bastiqui.moviesapp.activities.showInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bastiqui.moviesapp.R;

import java.util.Objects;

public class showDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_description);

        Intent getIntent = getIntent();

        String description = Objects.requireNonNull(getIntent.getExtras()).getString("description");
        TextView showDescription = findViewById(R.id.showDescription);

        showDescription.setText(description);
    }
}
