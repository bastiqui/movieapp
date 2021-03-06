package com.example.bastiqui.moviesapp.adapters.InfoAdapters;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bastiqui.moviesapp.Information;
import com.example.bastiqui.moviesapp.R;
import com.example.bastiqui.moviesapp.activities.showInfo.showImage;
import com.example.bastiqui.moviesapp.database.DatabaseHelper;
import com.example.bastiqui.moviesapp.database.WatchlistModel;
import com.example.bastiqui.moviesapp.model.GetDetails.movies.movies.MovieDetails;
import com.example.bastiqui.moviesapp.youtube.YoutubePlayer;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import babushkatext.BabushkaText;

public class InfoMovieAdapter extends RecyclerView.Adapter<InfoMovieAdapter.InfoMovieViewHolder> {
    public MovieDetails movieDetails = new MovieDetails();
    public List<MovieDetails.Genre> genres = new ArrayList<>();

    @NonNull
    @Override
    public InfoMovieAdapter.InfoMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_info, parent, false);
        final InfoMovieViewHolder infoMovieViewHolder = new InfoMovieViewHolder(view);

        infoMovieViewHolder.youTubeThumbnailView.setOnClickListener(v -> {
            if (movieDetails.getVideos().results.length > 0) {
                if (movieDetails.getVideos().results[0].site.equals("YouTube")) {
                    Intent youtube = new Intent(infoMovieViewHolder.itemView.getContext(), YoutubePlayer.class);
                    youtube.putExtra("query", movieDetails.getVideos().results[0].key);
                    infoMovieViewHolder.itemView.getContext().startActivity(youtube);
                } else {
                    Toast.makeText(infoMovieViewHolder.itemView.getContext(), "This movie doesn't contain a compatible video to be shown.", Toast.LENGTH_SHORT).show();
                }
            } else {
                infoMovieViewHolder.youTubeThumbnailView.setVisibility(View.GONE);
                Toast.makeText(infoMovieViewHolder.itemView.getContext(), "This movie doesn't contain a video to be shown.", Toast.LENGTH_SHORT).show();
            }
        });

        infoMovieViewHolder.title.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(movieDetails.getHomepage()));
            infoMovieViewHolder.itemView.getContext().startActivity(Intent.createChooser(browserIntent, "Open the page in:"));
        });

        infoMovieViewHolder.readMore.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(infoMovieViewHolder.itemView.getContext());
            dialog.setContentView(R.layout.activity_show_description);
            TextView textView = dialog.findViewById(R.id.showDescription);
            textView.setText(movieDetails.getOverview());

            Button closeDialog = dialog.findViewById(R.id.closeDialog);
            closeDialog.setOnClickListener(v1 -> dialog.dismiss());
            dialog.show();
        });

        //return new InfoMovieViewHolder;
        return infoMovieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoMovieAdapter.InfoMovieViewHolder holder, int position) {
        Intent viewImage = new Intent(holder.itemView.getContext(), showImage.class);

        holder.title.setText(movieDetails.getTitle());

        holder.type.setText(R.string.movie);

        String allGenres = "";
        if (genres.size() > 0) {
            if (genres.size() == 1) {
                allGenres += genres.get(0).name;
            } else {
                StringBuilder allGenresBuilder = new StringBuilder();
                for (int i = 0; i < (genres.size()-1); i++) {
                    allGenresBuilder.append(genres.get(i).name).append("/");
                }
                allGenres = allGenresBuilder.toString();
                allGenres +=  genres.get(genres.size()-1).name;
            }
        }
        holder.genres.setText(allGenres);

        holder.description.setText(movieDetails.getOverview());

        if (genres.size() > 0) {
            double value = Double.parseDouble(movieDetails.getVoteAverage());
            String vote = " " + movieDetails.getVoteAverage() + " ";

            if (value > 7.0) {
                holder.rating.addPiece(new BabushkaText.Piece.Builder(vote)
                        .textColor(Color.parseColor("#ffffff")).backgroundColor(Color.parseColor("#53c653"))
                        .build());
            } else if (value < 5.0) {
                holder.rating.addPiece(new BabushkaText.Piece.Builder(vote)
                        .textColor(Color.parseColor("#ffffff")).backgroundColor(Color.parseColor("#ff4d4d"))
                        .build());
            } else {
                holder.rating.addPiece(new BabushkaText.Piece.Builder(vote)
                        .textColor(Color.parseColor("#ffffff")).backgroundColor(Color.parseColor("#ffcc66"))
                        .build());
            }
        }

        holder.rating.display();

        holder.date.setText(movieDetails.getReleaseDate());

        Picasso.with(holder.itemView.getContext())
                .load(R.drawable.youtube_logo)
                .fit()
                .centerInside()
                .into(holder.youTubeThumbnailView);

        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/original/" + movieDetails.getBackdropPath())
                .fit()
                .memoryPolicy(MemoryPolicy.NO_STORE)
                .into(holder.back_poster);

        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/original/" + movieDetails.getPosterPath())
                .fit()
                .centerInside()
                .memoryPolicy(MemoryPolicy.NO_STORE)
                .into(holder.poster_path);

        holder.back_poster.setOnClickListener(v -> {
            viewImage.putExtra("image", movieDetails.getBackdropPath());
            holder.itemView.getContext().startActivity(viewImage);
        });

        holder.poster_path.setOnClickListener(v -> {
            viewImage.putExtra("image", movieDetails.getPosterPath());
            holder.itemView.getContext().startActivity(viewImage);
        });

        final DatabaseHelper dbHelper = new DatabaseHelper(holder.itemView.getContext());
        holder.addWatchlist.setOnClickListener(v -> {
            dbHelper.addWatchlist(new WatchlistModel(movieDetails.getId(), movieDetails.getTitle(), "https://image.tmdb.org/t/p/original/" + movieDetails.getPosterPath(),"movie", movieDetails.getVoteAverage(), Information.getDate()));
            Toast.makeText(holder.itemView.getContext(), "Movie added to watchlist", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class InfoMovieViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        YouTubeThumbnailView youTubeThumbnailView;
        FloatingActionButton addWatchlist;
        ImageView poster_path;
        ImageView back_poster;
        BabushkaText rating;
        TextView date;
        TextView type;
        TextView genres;
        TextView description;
        TextView readMore;

        InfoMovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.display_title);
            youTubeThumbnailView = itemView.findViewById(R.id.youtubePreview);
            addWatchlist = itemView.findViewById(R.id.addWatchlist);
            poster_path = itemView.findViewById(R.id.infoPoster);
            back_poster = itemView.findViewById(R.id.infoBackPoster);
            rating = itemView.findViewById(R.id.averageRating);
            date = itemView.findViewById(R.id.releaseDate);
            type = itemView.findViewById(R.id.display_type);
            genres = itemView.findViewById(R.id.display_genres);
            description = itemView.findViewById(R.id.display_description);
            readMore = itemView.findViewById(R.id.readMore);
        }
    }
}