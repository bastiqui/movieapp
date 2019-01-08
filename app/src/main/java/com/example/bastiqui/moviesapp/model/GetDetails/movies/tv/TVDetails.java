package com.example.bastiqui.moviesapp.model.GetDetails.movies.tv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVDetails {

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("in_production")
    @Expose
    private Boolean inProduction;
    @SerializedName("last_air_date")
    @Expose
    private String lastAirDate;
    @SerializedName("last_episode_to_air")
    @Expose
    private LastEpisodeToAir lastEpisodeToAir;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("next_episode_to_air")
    @Expose
    private NextEpisodeToAir nextEpisodeToAir;
    @SerializedName("original_name")
    @Expose
    private String originalName;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("seasons")
    @Expose
    private List<Season> seasons;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("vote_average")
    @Expose
    private String voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;

    public TVDetails() {
    }

    public TVDetails(String backdropPath, String firstAirDate, List<Genre> genres, String homepage, String id, Boolean inProduction, String lastAirDate, LastEpisodeToAir lastEpisodeToAir, String name, NextEpisodeToAir nextEpisodeToAir, String originalName, String overview, Double popularity, String posterPath, List<Season> seasons, String status, String voteAverage, Integer voteCount) {
        this.backdropPath = backdropPath;
        this.firstAirDate = firstAirDate;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.inProduction = inProduction;
        this.lastAirDate = lastAirDate;
        this.lastEpisodeToAir = lastEpisodeToAir;
        this.name = name;
        this.nextEpisodeToAir = nextEpisodeToAir;
        this.originalName = originalName;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.seasons = seasons;
        this.status = status;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getInProduction() {
        return inProduction;
    }

    public void setInProduction(Boolean inProduction) {
        this.inProduction = inProduction;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public LastEpisodeToAir getLastEpisodeToAir() {
        return lastEpisodeToAir;
    }

    public void setLastEpisodeToAir(LastEpisodeToAir lastEpisodeToAir) {
        this.lastEpisodeToAir = lastEpisodeToAir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NextEpisodeToAir getNextEpisodeToAir() {
        return nextEpisodeToAir;
    }

    public void setNextEpisodeToAir(NextEpisodeToAir nextEpisodeToAir) {
        this.nextEpisodeToAir = nextEpisodeToAir;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public static class Genre {
        @SerializedName("name")
        public String name;
    }
}