package com.example.bastiqui.moviesapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bastiqui.moviesapp.R;

public class HelpActivity extends AppCompatActivity {

    @SuppressLint({"IntentReset", "RestrictedApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton goHome = findViewById(R.id.return_home);
        goHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, FragmentsMainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        final String[] TO = {"asquicenoh97@elpuig.xeill.net"};

        Button form = findViewById(R.id.form_sastifaction);
        form.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/forms/aSGWRPFTh23ItqR73"));
            Toast.makeText(getApplicationContext(), "Thanks for voting!", Toast.LENGTH_SHORT).show();
            startActivity(Intent.createChooser(browserIntent, "Open the form in:"));
        });

        final Button feedback = findViewById(R.id.feedback);
        final EditText feedbackText = findViewById(R.id.feedbackText);
        final FloatingActionButton feedbackButton = findViewById(R.id.feedbackButton);
        feedback.setOnClickListener(v -> {
            if (feedbackText.getVisibility() == View.VISIBLE) {
                feedbackText.setVisibility(View.INVISIBLE);
                feedbackButton.setVisibility(View.INVISIBLE);
            } else {
                feedbackText.setVisibility(View.VISIBLE);
                feedbackButton.setVisibility(View.VISIBLE);
            }
        });

        feedbackButton.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Sending feedback... Thanks for it!", Toast.LENGTH_SHORT).show();
            feedbackText.setVisibility(View.INVISIBLE);
            feedbackButton.setVisibility(View.INVISIBLE);
        });

        Button email_feedback = findViewById(R.id.email_feedback);
        email_feedback.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email feedback");

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            startActivity(Intent.createChooser(emailIntent, "Send your email in:"));
        });
    }
}