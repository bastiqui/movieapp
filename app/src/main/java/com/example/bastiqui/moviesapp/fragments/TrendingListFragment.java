package com.example.bastiqui.moviesapp.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bastiqui.moviesapp.GridSpacingItemDecoration;
import com.example.bastiqui.moviesapp.ViewModels.MainViewModel;
import com.example.bastiqui.moviesapp.R;
import com.example.bastiqui.moviesapp.adapters.TrendingListAdapter;
import com.example.bastiqui.moviesapp.model.Trending;

import java.util.List;

public class TrendingListFragment extends Fragment {
    private MainViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private TrendingListAdapter mTrendingListAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mRecyclerView = view.findViewById(R.id.list);

        int numberOfColumns = 3;

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));

        int spanCount = 3; // 3 columns
        int spacing = 0;
        boolean includeEdge = false;
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        mTrendingListAdapter = new TrendingListAdapter();
        mRecyclerView.setAdapter(mTrendingListAdapter);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mViewModel.getTrending().observe(this, new Observer<List<Trending>>() {
            @Override
            public void onChanged(@Nullable List<Trending> trendings) {
                mTrendingListAdapter.trendingList = trendings;
                mTrendingListAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }
}
