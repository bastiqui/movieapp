package com.example.bastiqui.moviesapp.dbRepository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.bastiqui.moviesapp.MoviedbAPI;
import com.example.bastiqui.moviesapp.MoviedbModule;
import com.example.bastiqui.moviesapp.model.Trending;
import com.example.bastiqui.moviesapp.model.Lists.TrendingList;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingdbRepository {
    private MoviedbAPI moviedbAPI;

    public TrendingdbRepository(){
        moviedbAPI = MoviedbModule.getAPI();
    }

    public LiveData<List<Trending>> getTrending(){
        final MutableLiveData<List<Trending>> lista = new MutableLiveData<>();

        moviedbAPI.getTrending().enqueue(new Callback<TrendingList>() {
            @Override
            public void onResponse(@NonNull Call<TrendingList> call, @NonNull Response<TrendingList> response) {
                assert response.body() != null;
                lista.setValue(response.body().results);
            }

            @Override
            public void onFailure(@NonNull Call<TrendingList> call, @NonNull Throwable t) {
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
