package com.example.bastiqui.moviesapp.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.bastiqui.moviesapp.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button version = findViewById(R.id.version);
        version.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Version: 1.0", Toast.LENGTH_SHORT).show());

        Button legal_information = findViewById(R.id.legal_information);
        legal_information.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "bla bla bla", Toast.LENGTH_SHORT).show());

        FloatingActionButton goHome = findViewById(R.id.return_home);
        goHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, FragmentsMainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}