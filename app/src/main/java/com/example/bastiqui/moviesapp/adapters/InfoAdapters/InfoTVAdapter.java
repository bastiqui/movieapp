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
import com.example.bastiqui.moviesapp.activities.showInfo.showSeasons;
import com.example.bastiqui.moviesapp.database.DatabaseHelper;
import com.example.bastiqui.moviesapp.database.WatchlistModel;
import com.example.bastiqui.moviesapp.model.GetDetails.movies.tv.Season;
import com.example.bastiqui.moviesapp.model.GetDetails.movies.tv.TVDetails;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import babushkatext.BabushkaText;

public class InfoTVAdapter extends RecyclerView.Adapter<InfoTVAdapter.InfoTVViewHolder> {
    public TVDetails tvDetails = new TVDetails();
    public List<TVDetails.Genre> genres = new ArrayList<>();
    public List<Season> seasons = new ArrayList<>();

    @NonNull
    @Override
    public InfoTVAdapter.InfoTVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_info_tv, parent, false);
        final InfoTVViewHolder infoTVViewHolder = new InfoTVViewHolder(view);

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

        infoTVViewHolder.readMore.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(infoTVViewHolder.itemView.getContext());
            dialog.setContentView(R.layout.activity_show_description);
            TextView textView = dialog.findViewById(R.id.showDescription);
            textView.setText(tvDetails.getOverview());

            Button closeDialog = dialog.findViewById(R.id.closeDialog);
            closeDialog.setOnClickListener(v1 -> dialog.dismiss());
            dialog.show();
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
                StringBuilder allGenresBuilder = new StringBuilder();
                for (int i = 0; i < (genres.size()-1); i++) {
                    allGenresBuilder.append(genres.get(i).name).append("/");
                }
                allGenres = allGenresBuilder.toString();
                allGenres +=  genres.get(genres.size()-1).name;
            }
        }
        holder.genres.setText(allGenres);

        holder.description.setText(tvDetails.getOverview());

        if (genres.size() > 0) {
            double value = Double.parseDouble(tvDetails.getVoteAverage());
            String vote = " " + tvDetails.getVoteAverage() + " ";

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

        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/original/" + tvDetails.getBackdropPath())
                .fit()
                .memoryPolicy(MemoryPolicy.NO_STORE)
                .into(holder.back_poster);

        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/original/" + tvDetails.getPosterPath())
                .fit()
                .centerInside()
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
            dbHelper.addWatchlist(new WatchlistModel(tvDetails.getId(), tvDetails.getName(), "https://image.tmdb.org/t/p/original/" + tvDetails.getPosterPath(),"tv", tvDetails.getVoteAverage(), Information.getDate()));
            Toast.makeText(holder.itemView.getContext(), "TV added to watchlist", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class InfoTVViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster_path;
        ImageView back_poster;
        FloatingActionButton addWatchlist;
        BabushkaText rating;
        TextView date;
        TextView type;
        TextView genres;
        TextView description;
        TextView readMore;
        TextView seasons;

        InfoTVViewHolder(View itemView) {
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
            readMore = itemView.findViewById(R.id.readMore);
            seasons = itemView.findViewById(R.id.display_seasons);
        }
    }
}