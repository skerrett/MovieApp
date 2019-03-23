package com.example.movieapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieActorsApi {

    @GET("all")
    Call<List<Movie>> getAll();

    @GET("")
    Call<List<Movie>> getGenre();
}
