package com.example.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://moviesactorsapi20190322124235.azurewebsites.net/api/movies/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieActorsApi movieActorsApi = retrofit.create(MovieActorsApi.class);

        Call<List<Post>> call = movieActorsApi.getAll();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()){
            textViewResult.setText("Code: " + response.code());
            return;
                }
                List<Post> posts = response.body();

                for (Post post : posts){
                    String content ="";
                    content +="NAME: " + post.getName() + "\n";
                    content +="ReleaseYear: " + post.getReleaseYear() + "\n";
                    content +="GENRE: " + post.getGenre() + "\n";
                    content +="STARS: " + post.getStars() + "\n";
                    content +="RUNTIME: " + post.getRuntime() + "\n\n";

                    textViewResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
