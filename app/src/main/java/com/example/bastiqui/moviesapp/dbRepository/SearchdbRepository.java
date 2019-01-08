package com.example.bastiqui.moviesapp.dbRepository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.bastiqui.moviesapp.MoviedbAPI;
import com.example.bastiqui.moviesapp.MoviedbModule;
import com.example.bastiqui.moviesapp.model.Search;
import com.example.bastiqui.moviesapp.model.Lists.SearchList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchdbRepository {
    MoviedbAPI moviedbAPI;

    public SearchdbRepository(){
        moviedbAPI = MoviedbModule.getAPI();
    }

    public LiveData<List<Search>> getSearch(String query){
        final MutableLiveData<List<Search>> lista = new MutableLiveData<>();

        moviedbAPI.getSearch(query).enqueue(new Callback<SearchList>() {
            @Override
            public void onResponse(@NonNull Call<SearchList> call, @NonNull Response<SearchList> response) {
                lista.setValue(response.body().results);
            }

            @Override
            public void onFailure(Call<SearchList> call, Throwable t) {
            }
        });
        return lista;
    }
}