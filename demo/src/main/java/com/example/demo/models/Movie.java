package com.example.demo.models;

import java.util.List;

public class Movie {
    
    //Unique identifier for the movie
    private final String movieId;

    //Movie title
    private final String title;

    //Movie runtime
    private final String runtime;

    //Year movie was made
    private final String year;

    //MPA rating system for the movie (PG, PG-13, R, etc.)
    private final String score; 

    //Description of the movie
    private final String overview;

    //List of genres that apply to the movie
    private final String genre;

    //Director of the movie
    private final String director;

    //Url to the movie poster
    private final String posterLink;

    //Whether the current user has the movie saved
    private final boolean isSaved;

    public Movie(String movieId, String title, String runtime, String year, String score, String overview, String genre, String director, String posterLink, boolean isSaved) {
        this.movieId = movieId;
        this.title = title;
        this.runtime = runtime;
        this.year = year;
        this.score = score;
        this.overview = overview;
        this.genre = genre;
        this.director = director;
        this.posterLink = posterLink; 
        this.isSaved = isSaved;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }
    public String getRuntime() {
        return runtime;
    }

    public String getYear() {
        return year;
    }

    public String getScore() {
        return score;
    }

    public String getOverview() {
        return overview;
    }

    public String getGenres() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public boolean getIsSaved() {
        return isSaved;
    }
}
