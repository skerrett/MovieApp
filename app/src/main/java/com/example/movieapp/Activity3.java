package com.example.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity3 extends AppCompatActivity {
    private TextView textViewResult;
    private MovieActorsApi movieActorsApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://moviesactorsapi20190322124235.azurewebsites.net/api/movies/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieActorsApi = retrofit.create(MovieActorsApi.class);

        getGenre(text);
    }

    private void getGenre(String text) {
        Call<List<Movie>> call = movieActorsApi.getGenre(text);

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Movie> movies = response.body();

                for (Movie movie : movies) {
                    String content = "";
                    content += "NAME: " + movie.getName() + "\n";
                    content += "ReleaseYear: " + movie.getReleaseYear() + "\n";
                    content += "GENRE: " + movie.getGenre() + "\n";
                    content += "STARS: " + movie.getStars() + "\n";
                    content += "RUNTIME: " + movie.getRuntime() + "\n";
                    content += "CoverUrl: " + movie.getCoverUrl() + "\n";
                    content += "TrailerUrl: " + movie.getTrailerUrl() + "\n\n";

                    textViewResult.append(content);
                }


            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
