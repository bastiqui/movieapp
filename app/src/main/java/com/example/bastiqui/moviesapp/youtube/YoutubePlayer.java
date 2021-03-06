package com.example.bastiqui.moviesapp.youtube;

import android.content.Intent;
import android.os.Bundle;

import com.example.bastiqui.moviesapp.R;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Objects;

public class YoutubePlayer extends YouTubeFailureRecoveryActivity{

    private YouTubePlayerView playerView;
    private String youtube_url;

    String DEVELOPER_KEY = "";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_youtube);

        Intent getIntent = getIntent();

        // The unique video id of the youtube video (can be obtained from video url)
        youtube_url = Objects.requireNonNull(getIntent.getExtras()).getString("query");

        playerView = findViewById(R.id.player);
        playerView.initialize(DEVELOPER_KEY, this);
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return playerView;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {

        player.setFullscreen(true);
        player.setShowFullscreenButton(false);
        player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

        if (!wasRestored) {
            player.loadVideo(youtube_url);
        }
    }
}
