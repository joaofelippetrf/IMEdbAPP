package com.example.imedb;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.imedb.movieapp.entity.Movie;
import com.example.imedb.movieapp.interfac.ApiInterface;
import com.example.imedb.movieapp.client.Apiclient;
import com.example.imedb.movieapp.front.MovieAdapter;
import com.example.imedb.movieapp.front.MovieAdapter;
import java.util.List;
import com.example.imedb.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando o RecyclerView
        recyclerView = findViewById(R.id.recyclerViewMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fazer a requisição à API
        ApiInterface apiInterface = Apiclient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<Movie>> call = apiInterface.getMovies();  // Chama o endpoint para obter filmes

        call.enqueue(new Callback<>() {
            @Override
            public void onFailure(@NonNull Call<List<Movie>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "API request failed", Toast.LENGTH_SHORT).show();
                Log.e("API Error", t.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call<List<Movie>> call, @NonNull Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body();
                    if (movies != null && !movies.isEmpty()) {
                        // Passando os filmes para o Adapter do RecyclerView
                        MovieAdapter adapter = new MovieAdapter(MainActivity.this, movies);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.d("API Response", "No movies found in response.");
                    }
                } else {
                    Log.e("API Error", "Error code: " + response.code() + " Response: " + response.message());
                }
            }
        });
    }
}
