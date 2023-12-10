package edu.eci.co.movieapp.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApi {
    @GET(".")
    Call<MovieResponse> getMovies(@Query("s") String search, @Query("apikey") String apiKey);
}
