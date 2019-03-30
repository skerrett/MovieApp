package com.example.movieapp;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Activity7 extends AppCompatActivity implements  MovieAdapter.onItemClickListener{

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

        Intent intent = getIntent();
        int text = intent.getIntExtra(MainActivity.EXTRA_NUMBER,0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://moviesactorsapi20190322124235.azurewebsites.net/api/movies/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieActorsApi = retrofit.create(MovieActorsApi.class);


        getLength(text);
    }

    private void getLength(int text) {
        Call<List<Movie>> call = movieActorsApi.getRuntimeLess(text);

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Movie> movies = response.body();

                for (Movie movie : movies) {

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
                movieAdapter.setOnItemClickListnerer(Activity7.this);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }

        });

    }

    @Override
    public void onItemClick(int postion) {

        Movie clickeditem = MovieList.get(postion);



        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(clickeditem.getTrailerUrl())));
        Log.i("Video", "Video Playing....");
    }
}