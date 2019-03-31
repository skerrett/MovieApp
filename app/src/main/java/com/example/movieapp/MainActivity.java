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

public class MainActivity extends AppCompatActivity implements Dialog.DialogListener,Dialog2.DialogListener,Dialog3.DialogListener,Dialog4.DialogListener,Dialog5.DialogListener,Dialog6.DialogListener{
    private Button showAllButton;
    private Button showByGenreButton;
    private Button showByNameButton;
    private Button showByReleaseYearButton;
    private Button showByStarsButton;
    private Button showByLengthLess;
    private Button showByLengthMore;


    public static final String EXTRA_TEXT = "com.example.movieapp.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.movieapp.EXTRA_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showAllButton = findViewById(R.id.show_all_button);
        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();

            }
        });

        showByGenreButton = findViewById(R.id.search_by_genre_button);
        showByGenreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        showByNameButton = findViewById(R.id.search_by_name_button);
        showByNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog2();
            }
        });

        showByReleaseYearButton = findViewById(R.id.get_by_releaseyear);
        showByReleaseYearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog3();
            }
        });

        showByStarsButton = findViewById(R.id.get_by_stars);
        showByStarsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog4();
            }
        });
        showByStarsButton = findViewById(R.id.get_by_stars);
        showByStarsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog4();
            }
        });

        showByLengthLess = findViewById(R.id.get_by_lengthLess);
        showByLengthLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog5();
            }
        });

        showByLengthMore = findViewById(R.id.get_by_lengthMore);
        showByLengthMore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                openDialog6();
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

    public void sendReleaseYear(int ReleaseYear){

        Intent intent = new Intent(this, Activity5.class);
        intent.putExtra(EXTRA_NUMBER,ReleaseYear);
        startActivity(intent);
    }

    public void sendStars(int stars){
        Intent intent = new Intent(this, Activity6.class);
        intent.putExtra(EXTRA_NUMBER,stars);
        startActivity(intent);
    }

    public void sendLength(int Length){
        Intent intent = new Intent(this, Activity7.class);
        intent.putExtra(EXTRA_NUMBER,Length);
        startActivity(intent);
    }

    public void sendLengthMore(int Length){
        Intent intent = new Intent(this, Activity8.class);
        intent.putExtra(EXTRA_NUMBER,Length);
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

    public void openDialog4() {
        Dialog4 dialog = new Dialog4();
        dialog.show(getSupportFragmentManager(), "dialog4");
    }

    public void openDialog5() {
        Dialog5 dialog = new Dialog5();
        dialog.show(getSupportFragmentManager(), "dialog5");
    }

    public void openDialog6() {
        Dialog6 dialog = new Dialog6();
        dialog.show(getSupportFragmentManager(), "dialog6");
    }

}
