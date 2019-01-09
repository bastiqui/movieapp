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
import com.example.bastiqui.moviesapp.model.TV;

import java.util.ArrayList;
import java.util.List;

public class TVListAdapter extends RecyclerView.Adapter<TVListAdapter.TVViewHolder> {
    public List<TV> tvList = new ArrayList<>();

    @NonNull
    @Override
    public TVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        final TVViewHolder tvViewHolder = new TVViewHolder(view);

        tvViewHolder.linearLayout.setOnClickListener(v -> {
            final DatabaseHelper dbHelper = new DatabaseHelper(tvViewHolder.itemView.getContext());
            Intent intent = new Intent(tvViewHolder.itemView.getContext(), DisplayInfoActivity.class);

            intent.putExtra("id", tvList.get(tvViewHolder.getAdapterPosition()).id);
            intent.putExtra("type", "tv");

            dbHelper.addRecent(new RecentHistory(tvList.get(tvViewHolder.getAdapterPosition()).id,
                    tvList.get(tvViewHolder.getAdapterPosition()).name,
                    "https://image.tmdb.org/t/p/w500/" + tvList.get(tvViewHolder.getAdapterPosition()).getPoster_path(),
                    "tv",
                    tvList.get(tvViewHolder.getAdapterPosition()).vote_average,
                    Information.getDate()));

            tvViewHolder.itemView.getContext().startActivity(intent);
        });

        return tvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TVViewHolder holder, int position) {
        TV tv = tvList.get(position);

        holder.title.setText(new StringBuilder().append("#").append(position + 1).append(" ").append(tv.name).toString());

        GlideApp.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + tv.poster_path).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    class TVViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster;
        LinearLayout linearLayout;

        public TVViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitle);
            poster = itemView.findViewById(R.id.itemImage);
            linearLayout = itemView.findViewById(R.id.item_id);
        }
    }
}
