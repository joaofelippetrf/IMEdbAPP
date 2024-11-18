package com.example.imedb.movieapp.interfac;

import com.example.imedb.movieapp.entity.Movie;
import retrofit2.http.Path;  // Add this import for Retrofit's @Path
import retrofit2.http.Query;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface ApiInterface {

    @GET("Movies/all") // Substitua pelo seu endpoint real
    Call<List<Movie>> getMovies();

    @GET("Movies/busca")
    Call<List<Movie>> searchMovies(@Query("query") String query);

    @GET("Movies/{id}")  // Retrofit path variable
    Call<Movie> getMovieById(@Path("id") int id);  // Use Retrofit's @Path annotation
}
