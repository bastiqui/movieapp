package com.example.bastiqui.moviesapp.model.GetDetails.movies.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("vote_average")
    @Expose
    private String voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("videos")
    @Expose
    private Videos videos;

    public MovieDetails() {
    }

    public MovieDetails(String id, String backdropPath, List<Genre> genres, String homepage, String overview, String posterPath, String releaseDate, String tagline, String title, String voteAverage, Integer voteCount, Videos videos) {
        this.id = id;
        this.backdropPath = backdropPath;
        this.genres = genres;
        this.homepage = homepage;
        this.overview = overview;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.tagline = tagline;
        this.title = title;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.videos = videos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }

    public static class Videos {
        @SerializedName("results")
        public Results[] results;
    }

    public static class Results {
        @SerializedName("key")
        public String key;
        @SerializedName("site")
        public String site;
    }

    public static class Genre {
        @SerializedName("name")
        public String name;
    }
}