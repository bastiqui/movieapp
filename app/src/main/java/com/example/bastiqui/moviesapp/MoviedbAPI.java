package com.example.bastiqui.moviesapp;

import com.example.bastiqui.moviesapp.model.GetDetails.movies.movies.MovieDetails;
import com.example.bastiqui.moviesapp.model.GetDetails.movies.tv.TVDetails;
import com.example.bastiqui.moviesapp.model.Lists.MoviesList;
import com.example.bastiqui.moviesapp.model.Lists.SearchList;
import com.example.bastiqui.moviesapp.model.Lists.TVList;
import com.example.bastiqui.moviesapp.model.Lists.TrendingList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviedbAPI {
    @GET("/3/movie/popular")
    Call<MoviesList> getNewMovies();

    @GET("3/tv/popular")
    Call<TVList> getTVSeries();

    @GET("/3/trending/all/week")
    Call<TrendingList> getTrending();

    @GET("/3/search/multi")
    Call<SearchList> getSearch(@Query("query") String query);

    @GET("/3/movie/{id}")
    Call<MovieDetails> getMovieInfo(@Path("id") String id);

    @GET("/3/tv/{id}")
    Call<TVDetails> getTVInfo(@Path("id") String id);
}