package edu.eci.co.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import edu.eci.co.movieapp.databinding.ActivityMainBinding;
import edu.eci.co.movieapp.network.MovieDto;
import edu.eci.co.movieapp.network.MovieResponse;
import edu.eci.co.movieapp.network.OmdbApi;
import edu.eci.co.movieapp.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.searchButton.setOnClickListener(v -> {
            String searchQuery = binding.searchQueryText.getText().toString();
            fetchMovies(searchQuery);
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new MoviesAdapter(new ArrayList<>()));
    }

    private void fetchMovies(String searchQuery) {
        OmdbApi service = RetrofitClient.getRetrofitInstance().create(OmdbApi.class);
        Call<MovieResponse> call = service.getMovies(searchQuery, "a2935151");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MovieResponse movieDto = response.body();
                    List<MovieDto> movies = response.body().getSearch();
                    if (movies != null) {
                        MoviesAdapter adapter = new MoviesAdapter(movies);
                        binding.recyclerView.setAdapter(adapter);
                        Log.d("MainActivity", "Movie: " + movieDto);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage(), t);
            }
        });

    }
}
