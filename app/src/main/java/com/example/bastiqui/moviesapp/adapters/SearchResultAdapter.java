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
import com.example.bastiqui.moviesapp.model.Search;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchViewHolder> {
    public List<Search> searchList = new ArrayList<>();

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        final SearchViewHolder searchViewHolder = new SearchViewHolder(view);

        searchViewHolder.linearLayout.setOnClickListener(v -> {
            final DatabaseHelper dbHelper = new DatabaseHelper(searchViewHolder.itemView.getContext());
            Intent intent = new Intent(searchViewHolder.itemView.getContext(), DisplayInfoActivity.class);

            intent.putExtra("id", searchList.get(searchViewHolder.getAdapterPosition()).id);
            intent.putExtra("type", searchList.get(searchViewHolder.getAdapterPosition()).media_type);

            if (searchList.get(searchViewHolder.getAdapterPosition()).media_type.equals("movie")) {
                dbHelper.addRecent(new RecentHistory(searchList.get(searchViewHolder.getAdapterPosition()).id,
                        searchList.get(searchViewHolder.getAdapterPosition()).title,
                        "https://image.tmdb.org/t/p/original/" + searchList.get(searchViewHolder.getAdapterPosition()).poster_path,
                        searchList.get(searchViewHolder.getAdapterPosition()).media_type,
                        searchList.get(searchViewHolder.getAdapterPosition()).getVote_average(),
                        Information.getDate()));
            } else {
                dbHelper.addRecent(new RecentHistory(searchList.get(searchViewHolder.getAdapterPosition()).id,
                        searchList.get(searchViewHolder.getAdapterPosition()).name,
                        "https://image.tmdb.org/t/p/original/" + searchList.get(searchViewHolder.getAdapterPosition()).poster_path,
                        searchList.get(searchViewHolder.getAdapterPosition()).media_type,
                        searchList.get(searchViewHolder.getAdapterPosition()).getVote_average(),
                        Information.getDate()));
            }

            searchViewHolder.itemView.getContext().startActivity(intent);
        });

        return searchViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Search search = searchList.get(position);

        if (search.media_type.equals("movie")) holder.title.setText(search.title);
        else holder.title.setText(search.name);

        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/original/" + search.poster_path)
                .fit()
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster;
        LinearLayout linearLayout;

        SearchViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.itemTitle);
            poster = itemView.findViewById(R.id.itemImage);
            linearLayout = itemView.findViewById(R.id.item_id);
        }
    }
}
