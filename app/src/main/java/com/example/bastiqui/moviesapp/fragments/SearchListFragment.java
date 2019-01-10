package com.example.bastiqui.moviesapp.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bastiqui.moviesapp.GridSpacingItemDecoration;
import com.example.bastiqui.moviesapp.R;
import com.example.bastiqui.moviesapp.ViewModels.SearchListViewModel;
import com.example.bastiqui.moviesapp.adapters.SearchResultAdapter;

import java.util.Objects;

public class SearchListFragment extends Fragment {

    private SearchResultAdapter mSearchResultAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView mRecyclerView = view.findViewById(R.id.list);

        int numberOfColumns = 3;

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));

        int spanCount = 3; // 3 columns
        int spacing = 0;
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, false));

        mSearchResultAdapter = new SearchResultAdapter();
        mRecyclerView.setAdapter(mSearchResultAdapter);

        SearchListViewModel mSearchViewModel = ViewModelProviders.of(this).get(SearchListViewModel.class);

        String query = Objects.requireNonNull(Objects.requireNonNull(getActivity()).getIntent().getExtras()).getString("query");

        mSearchViewModel.getSearch(query).observe(this, searches -> {
            mSearchResultAdapter.searchList = searches;
            mSearchResultAdapter.notifyDataSetChanged();
        });
        return view;
    }
}