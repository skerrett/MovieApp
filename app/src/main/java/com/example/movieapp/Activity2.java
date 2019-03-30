package com.example.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity2 extends AppCompatActivity {
    private TextView textViewResult;
    private MovieActorsApi movieActorsApi;
    private RecyclerView RecyclerView;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> MovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        RecyclerView = findViewById(R.id.recycler_view);
        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MovieList = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://moviesactorsapi20190322124235.azurewebsites.net/api/movies/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieActorsApi = retrofit.create(MovieActorsApi.class);

        getMovies();
    }

    private void getMovies(){
        Call<List<Movie>> call = movieActorsApi.getAll();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Movie> movies = response.body();

                for (Movie movie : movies){
                    String name =  movie.getName();
                    int releaseYear = movie.getReleaseYear();
                    String genre = movie.getGenre();
                    int stars = movie.getStars();
                    int runtime = movie.getRuntime();
                    String coverUrl = movie.getCoverUrl();
                    String trailerUrl =  movie.getTrailerUrl();

                    MovieList.add(new Movie(name,releaseYear,genre,stars,coverUrl,runtime,trailerUrl));
                }
                movieAdapter = new MovieAdapter(getApplicationContext(),MovieList);
                RecyclerView.setAdapter(movieAdapter);

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }

        });
        movieAdapter = new MovieAdapter(Activity2.this,MovieList);
        RecyclerView.setAdapter(movieAdapter);
    }
}
