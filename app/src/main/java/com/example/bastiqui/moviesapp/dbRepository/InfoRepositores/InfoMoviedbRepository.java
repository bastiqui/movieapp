package com.example.bastiqui.moviesapp.dbRepository.InfoRepositores;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.bastiqui.moviesapp.MoviedbAPI;
import com.example.bastiqui.moviesapp.MoviedbModule;
import com.example.bastiqui.moviesapp.model.GetDetails.movies.movies.MovieDetails;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoMoviedbRepository {
    MoviedbAPI moviedbAPI;

    public InfoMoviedbRepository(){
        moviedbAPI = MoviedbModule.getAPI();
    }

    public LiveData<MovieDetails> getMovieInfo(String id){
        final MutableLiveData<MovieDetails> lista = new MutableLiveData<>();

        moviedbAPI.getMovieInfo(id).enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response) {
                MovieDetails movieDetails = response.body();
                lista.setValue(movieDetails);
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
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