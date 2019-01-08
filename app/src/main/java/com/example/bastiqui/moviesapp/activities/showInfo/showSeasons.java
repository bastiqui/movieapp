package com.example.bastiqui.moviesapp.activities.showInfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bastiqui.moviesapp.R;

import java.util.ArrayList;

public class showSeasons extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_seasons);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        Intent getIntent = getIntent();

        ArrayList<String> name;
        ArrayList<String> numEpisodes;

        name = getIntent.getStringArrayListExtra("seasonName");
        numEpisodes = getIntent.getStringArrayListExtra("numEpisodes");

        setContentView(linearLayout);

        for (int i = 0; i < name.size(); i++) {
            TextView season = new TextView(this);
            season.setText(name.get(i));
            season.setTextSize(25);

            TextView episodes = new TextView(this);
            episodes.setText(numEpisodes.get(i));
            episodes.setTextSize(20);

            season.setPadding(16,16, 16, 4);
            episodes.setPadding(32,4, 16, 16);

            linearLayout.addView(season);
            linearLayout.addView(episodes);
        }
    }
}
