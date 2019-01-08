package com.example.bastiqui.moviesapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.bastiqui.moviesapp.dbRepository.MoviedbRepository;
import com.example.bastiqui.moviesapp.dbRepository.TVdbRepository;
import com.example.bastiqui.moviesapp.dbRepository.TrendingdbRepository;
import com.example.bastiqui.moviesapp.model.Movie;
import com.example.bastiqui.moviesapp.model.TV;
import com.example.bastiqui.moviesapp.model.Trending;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private MoviedbRepository moviedbRepository;
    private TVdbRepository tVdbRepository;
    private TrendingdbRepository trendingdbRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        trendingdbRepository = new TrendingdbRepository();
        moviedbRepository = new MoviedbRepository();
        tVdbRepository = new TVdbRepository();
    }

    public LiveData<List<Movie>> getMovies(){
        return moviedbRepository.getMovies();
    }

    public LiveData<List<TV>> getTVSeries() {
        return tVdbRepository.getTVSeries();
    }

    public LiveData<List<Trending>> getTrending() {
        return trendingdbRepository.getTrending();
    }
}