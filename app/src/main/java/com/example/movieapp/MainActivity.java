package com.example.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Dialog.DialogListener,Dialog2.DialogListener{
    private TextView textViewResult;
    private MovieActorsApi movieActorsApi;
    private Button button;
    private Button button2;
    private Button button3;


    public static final String EXTRA_TEXT = "com.example.movieapp.EXTRA_TEXT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();

            }
        });
        button2 = findViewById(R.id.button_id2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        button3 = findViewById(R.id.button_id3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog2();
            }
        });

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://moviesactorsapi20190322124235.azurewebsites.net/api/movies/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieActorsApi = retrofit.create(MovieActorsApi.class);

        //getMovies();
        //getGenre();
    }

    private void getMovies() {
        Call<List<Movie>> call = movieActorsApi.getAll();

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
                    content += "RUNTIME: " + movie.getRuntime() + "\n\n";
                    content += "CoverUrl: " + movie.getCoverUrl() + "\n\n";
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

    private void getGenre() {
        Call<List<Movie>> call = movieActorsApi.getGenre("Action");

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
                    content += "TrailerUrl: " + movie.getTrailerUrl() + "\n";

                    textViewResult.append(content);
                }


            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }

    @Override
    public void sendGenre(String Genre) {
        String text = Genre.toString();

        Intent intent = new Intent(this, Activity3.class);
        intent.putExtra(EXTRA_TEXT,text);
        startActivity(intent);
    }

    @Override
    public void sendName(String Name) {
        String text = Name.toString();

        Intent intent = new Intent(this, Activity4.class);
        intent.putExtra(EXTRA_TEXT,text);
        startActivity(intent);
    }

    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public void openDialog() {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    public void openDialog2() {
        Dialog2 dialog = new Dialog2();
        dialog.show(getSupportFragmentManager(), "dialog2");
    }


}
