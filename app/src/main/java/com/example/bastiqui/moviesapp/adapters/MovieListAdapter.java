package com.example.bastiqui.moviesapp.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bastiqui.moviesapp.Information;
import com.example.bastiqui.moviesapp.R;
import com.example.bastiqui.moviesapp.activities.showInfo.DisplayInfoActivity;
import com.example.bastiqui.moviesapp.database.DatabaseHelper;
import com.example.bastiqui.moviesapp.database.RecentHistory;
import com.example.bastiqui.moviesapp.model.Movie;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>{
    public List<Movie> movieList = new ArrayList<>();

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        final MovieViewHolder movieViewHolder = new MovieViewHolder(view);

        movieViewHolder.linearLayout.setOnClickListener(v -> {
            final DatabaseHelper dbHelper = new DatabaseHelper(movieViewHolder.itemView.getContext());
            Intent intent = new Intent(movieViewHolder.itemView.getContext(), DisplayInfoActivity.class);

            intent.putExtra("id", movieList.get(movieViewHolder.getAdapterPosition()).id);
            intent.putExtra("type", "movie");

            dbHelper.addRecent(new RecentHistory(movieList.get(movieViewHolder.getAdapterPosition()).id,
                    movieList.get(movieViewHolder.getAdapterPosition()).title,
                    "https://image.tmdb.org/t/p/original/" + movieList.get(movieViewHolder.getAdapterPosition()).poster_path,
                    "movie",
                    movieList.get(movieViewHolder.getAdapterPosition()).getVote_average(),
                    Information.getDate()));

            movieViewHolder.itemView.getContext().startActivity(intent);
        });

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        final Movie movie = movieList.get(position);

        holder.title.setText(MessageFormat.format("#{0} {1}", position + 1, movie.title));
        holder.title.setSelected(true);

        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/original/" + movie.poster_path)
                .fit()
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return (movieList != null ? movieList.size() : 0);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster;
        LinearLayout linearLayout;

        MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitle);
            poster = itemView.findViewById(R.id.itemImage);
            linearLayout = itemView.findViewById(R.id.item_id);
        }
    }
}