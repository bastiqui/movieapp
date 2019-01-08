package com.example.bastiqui.moviesapp.dbRepository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.bastiqui.moviesapp.MoviedbAPI;
import com.example.bastiqui.moviesapp.MoviedbModule;
import com.example.bastiqui.moviesapp.model.Lists.MoviesList;
import com.example.bastiqui.moviesapp.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviedbRepository {
    MoviedbAPI moviedbAPI;

    public MoviedbRepository(){
        moviedbAPI = MoviedbModule.getAPI();
    }

    public LiveData<List<Movie>> getMovies(){
        final MutableLiveData<List<Movie>> lista = new MutableLiveData<>();

        moviedbAPI.getNewMovies().enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(@NonNull Call<MoviesList> call, @NonNull Response<MoviesList> response) {
                lista.setValue(response.body().results);
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
            }
        });
        return lista;
    }
}