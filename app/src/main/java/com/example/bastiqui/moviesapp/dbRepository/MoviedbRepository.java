package com.example.bastiqui.moviesapp.dbRepository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.bastiqui.moviesapp.MoviedbAPI;
import com.example.bastiqui.moviesapp.MoviedbModule;
import com.example.bastiqui.moviesapp.model.Lists.MoviesList;
import com.example.bastiqui.moviesapp.model.Movie;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviedbRepository {
    private MoviedbAPI moviedbAPI;

    public MoviedbRepository(){
        moviedbAPI = MoviedbModule.getAPI();
    }

    public LiveData<List<Movie>> getMovies(){
        final MutableLiveData<List<Movie>> lista = new MutableLiveData<>();

        moviedbAPI.getNewMovies().enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(@NonNull Call<MoviesList> call, @NonNull Response<MoviesList> response) {
                assert response.body() != null;
                lista.setValue(response.body().results);
            }

            @Override
            public void onFailure(@NonNull Call<MoviesList> call, @NonNull Throwable t) {
                if (t instanceof IOException) {
                    System.out.println("ABCD -> Timeout -> " + String.valueOf(t.getCause()));
                }
                else if (t instanceof IllegalStateException) {
                    System.out.println("ABCD -> ConversionError -> " + String.valueOf(t.getCause()));
                } else {
                    System.out.println("ABCD -> ERROR -> " + String.valueOf(t.getLocalizedMessage()));
                }
            }
        });
        return lista;
    }
}