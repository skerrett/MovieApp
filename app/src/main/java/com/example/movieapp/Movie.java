package com.example.movieapp;

public class Movie {

    private String name;

    private int releaseYear;

    private String genre;

    private int stars;

    private int runtime;

    private String coverUrl;

    private String trailerUrl;

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public int getStars() {
        return stars;
    }

    public int getRuntime() {
        return runtime;
    }
}
