package com.example.imedb;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.imedb.movieapp.ApiInterface;
import com.example.imedb.movieapp.Movie;
import com.example.imedb.movieapp.Apiclient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciando a TextView
        textView = findViewById(R.id.textView);

        // Fazer a requisição à API
        ApiInterface apiInterface = Apiclient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<Movie>> call = apiInterface.getMovies();  // Chama o endpoint para obter filmes

        call.enqueue(new Callback<>() {
            @SuppressLint("SetTextI18n")



            @Override
            public void onFailure(@NonNull Call<List<Movie>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "API request failed", Toast.LENGTH_SHORT).show();
                Log.e("API Error", Objects.requireNonNull(t.getMessage()));
            }
            @SuppressLint("SetTextI18n")

            @Override
            public void onResponse(@NonNull Call<List<Movie>> call, @NonNull Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body();
                    if (movies != null && !movies.isEmpty()) {
                        Log.d("API Response", "Movies fetched: " + movies.size());
                        textView.setText("Movies: \n" + movies.get(0).getOriginalTitle());
                    } else {
                        Log.d("API Response", "No movies found in response.");
                        textView.setText("No movies found.");
                    }
                } else {
                    Log.e("API Error", "Error code: " + response.code() + " Response: " + response.message());
                    textView.setText("Error: " + response.code());
                }
            }


        });
    }
}
