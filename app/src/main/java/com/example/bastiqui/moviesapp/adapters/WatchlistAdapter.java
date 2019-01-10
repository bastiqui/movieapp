package com.example.bastiqui.moviesapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bastiqui.moviesapp.R;
import com.example.bastiqui.moviesapp.activities.WatchList;
import com.example.bastiqui.moviesapp.activities.showInfo.DisplayInfoActivity;
import com.example.bastiqui.moviesapp.database.DatabaseHelper;
import com.example.bastiqui.moviesapp.database.WatchlistModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.WatchlistViewHolder> {
    public List<WatchlistModel> watchLists = new ArrayList<>();

    @NonNull
    @Override
    public WatchlistAdapter.WatchlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_recent, parent, false);
        final WatchlistViewHolder watchlistViewHolder = new WatchlistViewHolder(view);

        watchlistViewHolder.imageView.setOnClickListener(v -> {
            Intent getInfo = new Intent(watchlistViewHolder.itemView.getContext(), DisplayInfoActivity.class);
            getInfo.putExtra("id", watchLists.get(watchlistViewHolder.getAdapterPosition()).getId());
            getInfo.putExtra("type", watchLists.get(watchlistViewHolder.getAdapterPosition()).getType());
            watchlistViewHolder.imageView.getContext().startActivity(getInfo);
        });

        watchlistViewHolder.linearLayout.setOnLongClickListener(v -> {
            String[] options = {"Delete from watchlist"};

            AlertDialog.Builder builder = new AlertDialog.Builder(watchlistViewHolder.itemView.getContext());
            builder.setTitle("Choose an action: ");
            builder.setItems(options, (dialog, which) -> {
                switch (which) {
                    case 0:
                        final DatabaseHelper db = new DatabaseHelper(watchlistViewHolder.itemView.getContext());
                        db.removeWatchlist(watchLists.get(watchlistViewHolder.getAdapterPosition()).getId());

                        Intent refresh = new Intent(watchlistViewHolder.itemView.getContext(), WatchList.class);
                        watchlistViewHolder.itemView.getContext().startActivity(refresh);

                        ((Activity)watchlistViewHolder.itemView.getContext()).finish();
                        break;
                    default:
                        break;
                }
            });
            builder.show();

            return true;
        });

        return watchlistViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WatchlistAdapter.WatchlistViewHolder holder, int position) {
        WatchlistModel watchList = watchLists.get(position);

        Picasso.with(holder.itemView.getContext()).load(watchList.getImage())
                .fit()
                .into(holder.imageView);

        holder.name.setText(watchList.getName());
        holder.type.setText(watchList.getType());
        holder.vote.setText(String.format("%s/10", watchList.getVote_average()));
    }

    @Override
    public int getItemCount() {
        return watchLists.size();
    }

    public class WatchlistViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView type;
        TextView vote;
        LinearLayout linearLayout;

        public WatchlistViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recent_image);
            name = itemView.findViewById(R.id.recent_name);
            type = itemView.findViewById(R.id.recent_type);
            vote = itemView.findViewById(R.id.recent_vote);
            linearLayout = itemView.findViewById(R.id.recent_item);
        }
    }
}
