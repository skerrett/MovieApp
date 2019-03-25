package com.example.movieapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieActorsApi {

    @GET("all")
    Call<List<Movie>> getAll();

    @GET("genre/{genre}")
    Call<List<Movie>> getGenre(@Path("genre")String genre);

    @GET("name/{name}")
    Call<List<Movie>> getName(@Path("name") String name);


}
