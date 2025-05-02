package com.example.demo.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Review;
import com.example.demo.models.Movie;

@Service
public class SavedService {
    
    private final DataSource dataSource;

    @Autowired
    public SavedService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Get the current user's saved movies
    public List<Movie> getSavedMovies(String currentUserId) {
        System.out.println("Service: User is attempting to view saved movies");
        List<Movie> savedMovies = new ArrayList<>();

        // Query to get a user's saved movies
        final String sql = "SELECT m.movieId, title, runtime, year, overview, genre, director, posterLink FROM Save s JOIN Movie m ON s.movieId=m.movieId WHERE s.userId = ?";

        // Run query with datasource 
        try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(currentUserId));

            try (ResultSet rs = stmt.executeQuery()) {
                // Create movie objects to be displayed
                while(rs.next()) {
                    String imageLink;
                    if (rs.getString("posterLink").equals("N/A") || rs.getString("posterLink").equals("")) {
                        imageLink = "https://upload.wikimedia.org/wikipedia/commons/6/65/No-Image-Placeholder.svg";
                    } else {
                        imageLink =rs.getString("posterLink");
                    }
                    // Get movie information
                    String movieId = rs.getString("movieId");
                    String title = rs.getString("title");
                    String runtime =  rs.getString("runtime");
                    String year = rs.getString("year");
                    String overview = rs.getString("overview");
                    String genre = rs.getString("genre");
                    String director = rs.getString("director");
                    String posterUrl = imageLink;

                    String rating = "0";

                    // Create movie object 
                    Movie movie = new Movie(movieId, title, runtime, year, rating, overview, genre, director, posterUrl, true);

                    // Add movie to list
                    savedMovies.add(movie);
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log error
        }

        return savedMovies;
    }
}
