package com.example.imedb.movieapp.interfac;

import com.example.imedb.movieapp.entity.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface ApiInterface {

    @GET("Movies/all") // Substitua pelo seu endpoint real
    Call<List<Movie>> getMovies();
}
