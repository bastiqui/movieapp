package com.example.bastiqui.moviesapp.dbRepository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.bastiqui.moviesapp.MoviedbAPI;
import com.example.bastiqui.moviesapp.MoviedbModule;
import com.example.bastiqui.moviesapp.model.TV;
import com.example.bastiqui.moviesapp.model.Lists.TVList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVdbRepository {
    MoviedbAPI moviedbAPI;

    public TVdbRepository() {
        moviedbAPI = MoviedbModule.getAPI();
    }

    public LiveData<List<TV>> getTVSeries() {
        final MutableLiveData<List<TV>> lista = new MutableLiveData<>();

        moviedbAPI.getTVSeries().enqueue(new Callback<TVList>() {
            @Override
            public void onResponse(Call<TVList> call, Response<TVList> response) {
                lista.setValue(response.body().results);
            }

            @Override
            public void onFailure(Call<TVList> call, Throwable t) {

            }
        });
        return lista;
    }
}