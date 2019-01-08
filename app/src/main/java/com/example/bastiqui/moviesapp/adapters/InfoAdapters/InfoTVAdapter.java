package com.example.bastiqui.moviesapp.adapters.InfoAdapters;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bastiqui.moviesapp.GlideApp;
import com.example.bastiqui.moviesapp.Information;
import com.example.bastiqui.moviesapp.R;
import com.example.bastiqui.moviesapp.activities.showInfo.showDescription;
import com.example.bastiqui.moviesapp.activities.showInfo.showImage;
import com.example.bastiqui.moviesapp.activities.showInfo.showSeasons;
import com.example.bastiqui.moviesapp.database.DatabaseHelper;
import com.example.bastiqui.moviesapp.database.WatchlistModel;
import com.example.bastiqui.moviesapp.model.GetDetails.movies.tv.Season;
import com.example.bastiqui.moviesapp.model.GetDetails.movies.tv.TVDetails;

import java.util.ArrayList;
import java.util.List;

public class InfoTVAdapter extends RecyclerView.Adapter<InfoTVAdapter.InfoTVViewHolder> {
    public TVDetails tvDetails = new TVDetails();
    public List<TVDetails.Genre> genres = new ArrayList<>();
    public List<Season> seasons = new ArrayList<>();

    @NonNull
    @Override
    public InfoTVAdapter.InfoTVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_info_tv, parent, false);
        final InfoTVViewHolder infoTVViewHolder = new InfoTVViewHolder(view);

        infoTVViewHolder.description.setOnClickListener(v -> {
            Intent sendDescription = new Intent(infoTVViewHolder.itemView.getContext(), showDescription.class);
            sendDescription.putExtra("description", tvDetails.getOverview());
            infoTVViewHolder.itemView.getContext().startActivity(sendDescription);
        });

        infoTVViewHolder.title.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tvDetails.getHomepage()));
            infoTVViewHolder.itemView.getContext().startActivity(Intent.createChooser(browserIntent, "Open the page in:"));
        });

        infoTVViewHolder.seasons.setOnClickListener(v -> {
            Intent sendSeasons = new Intent(infoTVViewHolder.itemView.getContext(), showSeasons.class);

            ArrayList<String> seasonName = new ArrayList<>();
            ArrayList<String> numEpisodes = new ArrayList<>();

            for (int i = 0; i < seasons.size(); i++) {
                seasonName.add(seasons.get(i).getName());
            }
            for (int i = 0; i < seasons.size(); i++) {
                numEpisodes.add(seasons.get(i).getEpisodeCount());
            }

            sendSeasons.putStringArrayListExtra("seasonName", seasonName);
            sendSeasons.putStringArrayListExtra("numEpisodes", numEpisodes);

            infoTVViewHolder.itemView.getContext().startActivity(sendSeasons);
        });

        return infoTVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoTVAdapter.InfoTVViewHolder holder, int position) {
        Intent viewImage = new Intent(holder.itemView.getContext(), showImage.class);

        holder.title.setText(tvDetails.getName());

        holder.type.setText(R.string.tv_serie);

        String allGenres = "";
        if (genres.size() > 0) {
            if (genres.size() == 1) {
                allGenres += genres.get(0).name;
            } else {
                for (int i = 0; i < (genres.size()-1); i++) {
                    allGenres += genres.get(i).name + "/";
                }
                allGenres +=  genres.get(genres.size()-1).name;
            }
        }
        holder.genres.setText(allGenres);

        holder.description.setText(tvDetails.getOverview());

        holder.rating.setText(tvDetails.getVoteAverage());

        GlideApp.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + tvDetails.getBackdropPath())
                .into(holder.back_poster);

        GlideApp.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + tvDetails.getPosterPath())
                .into(holder.poster_path);

        holder.back_poster.setOnClickListener(v -> {
            viewImage.putExtra("image", tvDetails.getBackdropPath());
            holder.itemView.getContext().startActivity(viewImage);
        });

        holder.poster_path.setOnClickListener(v -> {
            viewImage.putExtra("image", tvDetails.getPosterPath());
            holder.itemView.getContext().startActivity(viewImage);
        });

        final DatabaseHelper dbHelper = new DatabaseHelper(holder.itemView.getContext());
        holder.addWatchlist.setOnClickListener(v -> {
            dbHelper.addWatchlist(new WatchlistModel(tvDetails.getId(), tvDetails.getName(), "tv", tvDetails.getVoteAverage(), Information.getDate()));
            Toast.makeText(holder.itemView.getContext(), "TV added to watchlist", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class InfoTVViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster_path;
        ImageView back_poster;
        FloatingActionButton addWatchlist;
        TextView rating;
        TextView date;
        TextView type;
        TextView genres;
        TextView description;
        TextView seasons;

        public InfoTVViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.display_title);
            poster_path = itemView.findViewById(R.id.infoPoster);
            back_poster = itemView.findViewById(R.id.infoBackPoster);
            addWatchlist = itemView.findViewById(R.id.addWatchlist);
            rating = itemView.findViewById(R.id.averageRating);
            date = itemView.findViewById(R.id.releaseDate);
            type = itemView.findViewById(R.id.display_type);
            genres = itemView.findViewById(R.id.display_genres);
            description = itemView.findViewById(R.id.display_description);
            seasons = itemView.findViewById(R.id.display_seasons);
        }
    }
}