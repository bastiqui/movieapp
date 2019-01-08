package com.example.bastiqui.moviesapp.dbRepository.InfoRepositores;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.bastiqui.moviesapp.MoviedbAPI;
import com.example.bastiqui.moviesapp.MoviedbModule;
import com.example.bastiqui.moviesapp.model.GetDetails.movies.tv.TVDetails;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoTVdbRepository {
    MoviedbAPI moviedbAPI;

    public InfoTVdbRepository(){
        moviedbAPI = MoviedbModule.getAPI();
    }

    public LiveData<TVDetails> getTVInfo(String id){
        final MutableLiveData<TVDetails> lista = new MutableLiveData<>();

        moviedbAPI.getTVInfo(id).enqueue(new Callback<TVDetails>() {
            @Override
            public void onResponse(@NonNull Call<TVDetails> call, @NonNull Response<TVDetails> response) {
                TVDetails tvDetails = response.body();
                lista.setValue(tvDetails);
            }

            @Override
            public void onFailure(Call<TVDetails> call, Throwable t) {
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
