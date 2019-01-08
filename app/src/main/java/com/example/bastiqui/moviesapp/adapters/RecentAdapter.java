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

import com.example.bastiqui.moviesapp.R;
import com.example.bastiqui.moviesapp.activities.showInfo.DisplayInfoActivity;
import com.example.bastiqui.moviesapp.database.DatabaseHelper;
import com.example.bastiqui.moviesapp.database.Recent_History;

import java.util.ArrayList;
import java.util.List;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.RecentViewHolder> {
    public List<Recent_History> recentHistoryList = new ArrayList<>();

    @NonNull
    @Override
    public RecentAdapter.RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_recent, parent, false);
        final RecentViewHolder recentViewHolder = new RecentViewHolder(view);

        recentViewHolder.imageView.setOnClickListener(v -> {
            Intent getInfo = new Intent(recentViewHolder.itemView.getContext(), DisplayInfoActivity.class);
            getInfo.putExtra("id", recentHistoryList.get(recentViewHolder.getAdapterPosition()).getId());
            getInfo.putExtra("type", recentHistoryList.get(recentViewHolder.getAdapterPosition()).getType());
            recentViewHolder.imageView.getContext().startActivity(getInfo);
        });

        return recentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentAdapter.RecentViewHolder holder, int position) {
        Recent_History recentHistory = recentHistoryList.get(position);

        holder.name.setText(recentHistory.getName());
        holder.type.setText(recentHistory.getType());
        holder.vote.setText(String.format("%s/10", recentHistory.getVote_average()));

        final DatabaseHelper dbHelper = new DatabaseHelper(holder.itemView.getContext());
        holder.imageView.setImageBitmap(dbHelper.getImage(recentHistory.getId()));
    }

    @Override
    public int getItemCount() {
        return recentHistoryList.size();
    }

    public class RecentViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView type;
        TextView vote;
        LinearLayout linearLayout;

        public RecentViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recent_image);
            name = itemView.findViewById(R.id.recent_name);
            type = itemView.findViewById(R.id.recent_type);
            vote = itemView.findViewById(R.id.recent_vote);
            linearLayout = itemView.findViewById(R.id.recent_item);
        }
    }
}