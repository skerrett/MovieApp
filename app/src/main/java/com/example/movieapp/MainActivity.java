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

public class MainActivity extends AppCompatActivity implements Dialog.DialogListener,Dialog2.DialogListener,Dialog3.DialogListener{
    private TextView textViewResult;
    private MovieActorsApi movieActorsApi;
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;


    public static final String EXTRA_TEXT = "com.example.movieapp.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.movieapp.EXTRA_NUMBER";

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

        button4 = findViewById(R.id.button_id4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog3();
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

    public void sendReleaseYear(int name){
        int text = name;

        Intent intent = new Intent(this, Activity5.class);
        intent.putExtra(EXTRA_NUMBER,text);
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

    public void openDialog3() {
        Dialog3 dialog = new Dialog3();
        dialog.show(getSupportFragmentManager(), "dialog3");
    }


}
