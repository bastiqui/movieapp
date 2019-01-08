package com.example.bastiqui.moviesapp.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bastiqui.moviesapp.R;
import com.example.bastiqui.moviesapp.ViewModels.InfoViewModel;
import com.example.bastiqui.moviesapp.adapters.WatchlistAdapter;

public class WatchListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent, container, false);

        RecyclerView mRecyclerView = view.findViewById(R.id.list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        WatchlistAdapter watchlistAdapter = new WatchlistAdapter();
        mRecyclerView.setAdapter(watchlistAdapter);

        InfoViewModel mInfoViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);

        mInfoViewModel.getAllWatchlist().observe(this, watchlistModelList -> {
            watchlistAdapter.watchLists = watchlistModelList;
            watchlistAdapter.notifyDataSetChanged();
        });
        return view;
    }
}
