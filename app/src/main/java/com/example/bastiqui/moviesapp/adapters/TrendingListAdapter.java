package com.example.bastiqui.moviesapp.adapters;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.bastiqui.moviesapp.GlideApp;
import com.example.bastiqui.moviesapp.Information;
import com.example.bastiqui.moviesapp.R;
import com.example.bastiqui.moviesapp.activities.showInfo.DisplayInfoActivity;
import com.example.bastiqui.moviesapp.database.DatabaseHelper;
import com.example.bastiqui.moviesapp.database.RecentHistory;
import com.example.bastiqui.moviesapp.model.Trending;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class TrendingListAdapter extends RecyclerView.Adapter<TrendingListAdapter.TrendingViewHolder> {
    public List<Trending> trendingList = new ArrayList<>();

    @NonNull
    @Override
    public TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        final TrendingViewHolder trendingViewHolder = new TrendingViewHolder(view);

        trendingViewHolder.linearLayout.setOnClickListener(v -> {
            final DatabaseHelper dbHelper = new DatabaseHelper(trendingViewHolder.itemView.getContext());
            Intent intent = new Intent(trendingViewHolder.itemView.getContext(), DisplayInfoActivity.class);

            if (trendingList.get(trendingViewHolder.getAdapterPosition()).title == null) {
                intent.putExtra("id", trendingList.get(trendingViewHolder.getAdapterPosition()).id);
                intent.putExtra("type", "tv");

                dbHelper.addRecent(new RecentHistory(trendingList.get(trendingViewHolder.getAdapterPosition()).id,
                        trendingList.get(trendingViewHolder.getAdapterPosition()).name,
                        "https://image.tmdb.org/t/p/w500/" + trendingList.get(trendingViewHolder.getAdapterPosition()).poster_path,
                        "tv",
                        trendingList.get(trendingViewHolder.getAdapterPosition()).vote_average,
                        Information.getDate()));
            } else {
                intent.putExtra("id", trendingList.get(trendingViewHolder.getAdapterPosition()).id);
                intent.putExtra("type", "movie");

                dbHelper.addRecent(new RecentHistory(trendingList.get(trendingViewHolder.getAdapterPosition()).id,
                        trendingList.get(trendingViewHolder.getAdapterPosition()).title,
                        "https://image.tmdb.org/t/p/w500/" + trendingList.get(trendingViewHolder.getAdapterPosition()).poster_path,
                        "movie",
                        trendingList.get(trendingViewHolder.getAdapterPosition()).vote_average,
                        Information.getDate()));
            }

            trendingViewHolder.itemView.getContext().startActivity(intent);
        });

        return trendingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingViewHolder holder, int position) {
        Trending trending = trendingList.get(position);

        if (trending.title == null) {
            holder.title.setText(MessageFormat.format("#{0} {1}", position + 1, trending.name));
        } else {
            holder.title.setText(MessageFormat.format("#{0} {1}", position + 1, trending.title));
        }



        GlideApp.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + trending.poster_path).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return trendingList.size();
    }

    class TrendingViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster;
        LinearLayout linearLayout;

        public TrendingViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitle);
            poster = itemView.findViewById(R.id.itemImage);
            linearLayout = itemView.findViewById(R.id.item_id);
        }
    }
}
