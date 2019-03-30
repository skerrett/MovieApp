package com.example.movieapp;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.gif.GifResourceEncoder;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context mContext;
    private ArrayList<Movie> MovieList;

    public MovieAdapter(Context context, ArrayList<Movie> movieList){
        this.mContext = context;
        this.MovieList = movieList;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent,false);
        return new MovieViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return MovieList.size();
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
    Movie currentItem = MovieList.get(position);

    String coverUrl = currentItem.getCoverUrl();
    String name = currentItem.getName();
    String genre = currentItem.getGenre();
    int runtime = currentItem.getRuntime();
    int stars = currentItem.getStars();
    String trailerUrl = currentItem.getTrailerUrl();
    int releaseYear = currentItem.getReleaseYear();


        holder.name.setText(name);
        holder.genre.setText("Genre"+genre);
        holder.trailerUrl.setText("trailerUrl:" + trailerUrl);
        holder.releaseYear.setText("releaseYear: "+releaseYear);
        holder.runtime.setText("runtime: "+runtime);
        holder.stars.setText("stars: "+stars);
        Picasso.with(mContext).load(coverUrl).fit().centerInside().into(holder.coverUrl);
    }



    public class  MovieViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView releaseYear;
        public TextView genre;
        public TextView stars;
        public TextView runtime;
        public ImageView coverUrl;
        public TextView trailerUrl;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_view_name);
            releaseYear = itemView.findViewById(R.id.text_view_releaseYear);
            genre = itemView.findViewById(R.id.text_view_genre);
            runtime = itemView.findViewById(R.id.text_view_runtime);
            coverUrl = itemView.findViewById(R.id.image_view);
            trailerUrl = itemView.findViewById(R.id.text_view_trailer);
            stars = itemView.findViewById(R.id.text_view_stars);
        }
    }
}
