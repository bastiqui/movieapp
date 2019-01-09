package com.example.bastiqui.moviesapp.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.bastiqui.moviesapp.database.DatabaseHelper;
import com.example.bastiqui.moviesapp.database.RecentHistory;
import com.example.bastiqui.moviesapp.database.WatchlistModel;
import com.example.bastiqui.moviesapp.dbRepository.InfoRepositores.InfoMoviedbRepository;
import com.example.bastiqui.moviesapp.dbRepository.InfoRepositores.InfoTVdbRepository;
import com.example.bastiqui.moviesapp.model.GetDetails.movies.movies.MovieDetails;
import com.example.bastiqui.moviesapp.model.GetDetails.movies.tv.TVDetails;

import java.util.List;

public class InfoViewModel extends AndroidViewModel {
    private InfoMoviedbRepository infoMoviedbRepository;
    private InfoTVdbRepository infoTVdbRepository;
    private final DatabaseHelper dbHelper = new DatabaseHelper(getApplication());

    public InfoViewModel(@NonNull Application application) {
        super(application);
        infoMoviedbRepository = new InfoMoviedbRepository();
        infoTVdbRepository = new InfoTVdbRepository();
    }

    public LiveData<MovieDetails> getMovieDetails(String id) {
        return infoMoviedbRepository.getMovieInfo(id);
    }

    public LiveData<TVDetails> getTVDetails(String id) {
        return infoTVdbRepository.getTVInfo(id);
    }

    public LiveData<List<RecentHistory>> getAllRecent () {
        return dbHelper.getAllRecent();
    }

    public LiveData<List<WatchlistModel>> getAllWatchlist() {
        return dbHelper.getAllWatchlist();
    }
}