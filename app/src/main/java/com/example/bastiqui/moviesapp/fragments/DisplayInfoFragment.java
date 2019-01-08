package com.example.bastiqui.moviesapp.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import com.example.bastiqui.moviesapp.adapters.InfoAdapters.InfoMovieAdapter;
import com.example.bastiqui.moviesapp.adapters.InfoAdapters.InfoTVAdapter;

import java.util.Objects;

public class DisplayInfoFragment extends Fragment {

    private InfoMovieAdapter mInfoMovieAdapter;
    private InfoTVAdapter minfoTVAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        RecyclerView mRecyclerView = view.findViewById(R.id.list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Intent getIntent = Objects.requireNonNull(getActivity()).getIntent();

        String itemID = Objects.requireNonNull(getActivity().getIntent().getExtras()).getString("id");
        String type = Objects.requireNonNull(getIntent.getExtras()).getString("type");

        InfoViewModel mInfoViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);

        if (type.equals("movie")) {
            mInfoMovieAdapter = new InfoMovieAdapter();
            mRecyclerView.setAdapter(mInfoMovieAdapter);

            mInfoViewModel.getMovieDetails(itemID).observe(this, movieDetails -> {
                mInfoMovieAdapter.movieDetails = movieDetails;
                mInfoMovieAdapter.genres = movieDetails.getGenres();
                mInfoMovieAdapter.notifyDataSetChanged();
            });
        } else if (type.equals("tv")) {
            minfoTVAdapter = new InfoTVAdapter();
            mRecyclerView.setAdapter(minfoTVAdapter);

            mInfoViewModel.getTVDetails(itemID).observe(this, tvDetails -> {
                minfoTVAdapter.tvDetails = tvDetails;
                minfoTVAdapter.genres = tvDetails.getGenres();
                minfoTVAdapter.notifyDataSetChanged();
            });
        }

        return view;
    }
}