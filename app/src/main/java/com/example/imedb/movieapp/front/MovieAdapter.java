package com.example.imedb.movieapp.front;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.imedb.R;
import com.example.imedb.movieapp.entity.Movie;
import java.util.List;
import com.example.imedb.R;
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private List<Movie> movies;  // Variável que armazena os filmes
    private OnItemClickListener listener;

    // Construtor do adapter
    public MovieAdapter(Context context, List<Movie> movies, OnItemClickListener listener) {
        this.context = context;
        this.movies = movies;  // Atribui a lista de filmes
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false); // Use movie_item.xml
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);  // Agora você pode acessar os filmes

        // Defina o título e o poster
        holder.movieTitleTextView.setText(movie.getOriginalTitle());
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + movie.getPosterpath())
                .into(holder.moviePosterImageView);

        // Defina o clique do item
        holder.itemView.setOnClickListener(v -> listener.onItemClick(movie));
    }

    @Override
    public int getItemCount() {
        return movies.size();  // Retorna o tamanho da lista de filmes
    }

    // ViewHolder para as views dentro do item
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView moviePosterImageView;
        public TextView movieTitleTextView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            moviePosterImageView = itemView.findViewById(R.id.moviePoster);
            movieTitleTextView = itemView.findViewById(R.id.movieTitle);
        }
    }

    // Interface para lidar com o clique do item
    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }
}
