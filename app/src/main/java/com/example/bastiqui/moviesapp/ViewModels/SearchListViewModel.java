package com.example.bastiqui.moviesapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.bastiqui.moviesapp.dbRepository.SearchdbRepository;
import com.example.bastiqui.moviesapp.model.Search;

import java.util.List;

public class SearchListViewModel extends AndroidViewModel {
    private SearchdbRepository searchdbRepository;

    public SearchListViewModel(@NonNull Application application) {
        super(application);
        searchdbRepository = new SearchdbRepository();
    }

    public LiveData<List<Search>> getSearch(String query) {
        return searchdbRepository.getSearch(query);
    }
}