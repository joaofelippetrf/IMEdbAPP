package com.example.imedb.movieapp.entity;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    private Integer id;

    @SerializedName("overview")
    private String overview;

    @SerializedName("posterpath")
    private String posterpath;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("budget")
    private int budget;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("releaseDate") // Corrigido para camelCase
    private String releaseDate;

    @SerializedName("voteAverage") // Certifique-se de que o backend usa camelCase
    private double voteAverage;

    @SerializedName("voteCount")
    private int voteCount;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("genre")
    private String genre;

    @SerializedName("originalTitle")
    private String originalTitle;

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterpath() {
        return posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
}
