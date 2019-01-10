package com.example.bastiqui.moviesapp.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bastiqui.moviesapp.GridSpacingItemDecoration;
import com.example.bastiqui.moviesapp.R;
import com.example.bastiqui.moviesapp.ViewModels.MainViewModel;
import com.example.bastiqui.moviesapp.adapters.TVListAdapter;

public class TVListFragment extends Fragment {
    private TVListAdapter mTVListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView mRecyclerView = view.findViewById(R.id.list);

        int numberOfColumns = 3;

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));

        int spanCount = 3; // 3 columns
        int spacing = 0;
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, false));

        mTVListAdapter = new TVListAdapter();
        mRecyclerView.setAdapter(mTVListAdapter);

        MainViewModel mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mViewModel.getTVSeries().observe(this, series -> {
            mTVListAdapter.tvList = series;
            mTVListAdapter.notifyDataSetChanged();
        });
        return view;
    }
}