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
    private final String rating; 

    //Description of the movie
    private final String description;

    //List of genres that apply to the movie
    private final List<String> genres;

    //Actors in the movie
    private final List<String> actors;

    //Director of the movie
    private final String director;

    //Url to the movie poster
    private final String posterUrl;

    public Movie(String movieId, String title, String runtime, String year, String rating, String description, List<String> genres, List<String> actors, String director, String posterUrl) {
        this.movieId = movieId;
        this.title = title;
        this.runtime = runtime;
        this.year = year;
        this.rating = rating;
        this.description = description;
        this.genres = genres;
        this.actors = actors;
        this.director = director;
        this.posterUrl = posterUrl; 
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

    public String getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getActors() {
        return actors;
    }

    public String getDirector() {
        return director;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
}
