package com.example.imedb.movieapp.activties;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imedb.R;
import com.example.imedb.movieapp.entity.Movie;
import com.example.imedb.movieapp.front.MovieAdapter;
import com.example.imedb.movieapp.interfac.ApiInterface;
import com.example.imedb.movieapp.client.Apiclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText searchInput;
    private Button searchButton, resetButton;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando as views
        recyclerView = findViewById(R.id.recyclerViewMovies);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));  // Usando GridLayout
        searchInput = findViewById(R.id.searchInput);
        searchButton = findViewById(R.id.searchButton);
        resetButton = findViewById(R.id.resetButton);

        // Configurando Retrofit
        apiInterface = Apiclient.getRetrofitInstance().create(ApiInterface.class);

        // Carregar todos os filmes inicialmente
        loadAllMovies();

        // Configurar ação de busca
        searchButton.setOnClickListener(v -> searchMovies(searchInput.getText().toString()));

        // Configurar ação de reset
        resetButton.setOnClickListener(v -> {
            searchInput.setText("");
            loadAllMovies();
        });
    }

    private void loadAllMovies() {
        // Fazendo a requisição para obter todos os filmes
        Call<List<Movie>> call = apiInterface.getMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(@NonNull Call<List<Movie>> call, @NonNull Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body();
                    if (movies != null) {
                        // Passando os dados para o adapter do RecyclerView
                        recyclerView.setAdapter(new MovieAdapter(MainActivity.this, movies, new MovieAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Movie movie) {
                                // Ao clicar em um filme, abrir a tela de detalhes
                                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                                intent.putExtra("MOVIE_ID", movie.getId());  // Passando o ID do filme
                                startActivity(intent);
                            }
                        }));
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao carregar filmes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Movie>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Erro ao carregar filmes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchMovies(String query) {
        if (query.isEmpty()) {
            Toast.makeText(this, "Digite algo para buscar", Toast.LENGTH_SHORT).show();
            return;
        }

        // Fazendo a requisição para buscar filmes pela query
        Call<List<Movie>> call = apiInterface.searchMovies(query);
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(@NonNull Call<List<Movie>> call, @NonNull Response<List<Movie>> response) {
                if (response.isSuccessful()) {
                    List<Movie> movies = response.body();
                    if (movies != null) {
                        recyclerView.setAdapter(new MovieAdapter(MainActivity.this, movies, new MovieAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Movie movie) {
                                // Ao clicar em um filme, abrir a tela de detalhes
                                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                                intent.putExtra("MOVIE_ID", movie.getId());  // Passando o ID do filme
                                startActivity(intent);
                            }
                        }));
                    } else {
                        Toast.makeText(MainActivity.this, "Nenhum filme encontrado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao buscar filmes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Movie>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Erro ao buscar filmes", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
