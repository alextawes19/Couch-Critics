package com.example.demo.models;

public class MovieThumbnail {

    //Identifies movie
    private final String movieId;

    //Movie title
    private final String title;

    //Year movie was made
    private final String year;

    //Url to the movie poster
    private final String posterUrl;

    private final double averageRating;

    public MovieThumbnail(String movieId, String title, String year, String posterUrl, double averageRating) {
        this.movieId = movieId;
        this.title = title;
        this.year = year;
        this.posterUrl = posterUrl;
        this.averageRating = averageRating;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public double getAverageRating() {
        return averageRating;
    }

    
}
