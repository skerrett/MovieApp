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

    @GET("year_released/{release_year}")
    Call<List<Movie>> getYear(@Path("release_year")int release);

    @GET("stars/{stars}")
    Call<List<Movie>> getStar(@Path("stars")int stars);

    @GET("runtime/less/{runtime}")
    Call<List<Movie>> getRuntimeLess(@Path("runtime")int runtime);

    @GET("runtime/more/{runtime}")
    Call<List<Movie>> getRuntimeMore(@Path("runtime")int runtime);


}
