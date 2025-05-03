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
import com.example.demo.models.MovieThumbnail;

@Service
public class SavedService {
    
    private final DataSource dataSource;
    private final HomeService homeService;

    @Autowired
    public SavedService(DataSource dataSource, HomeService homeService) {
        this.dataSource = dataSource;
        this.homeService = homeService;
    }

    // Get the current user's saved movies
    public List<MovieThumbnail> getSavedMovies(String currentUserId) {
        System.out.println("SavedService: User is attempting to view saved movies");
        System.out.println("DEBUG SavedService: currentUserId: " + currentUserId);
        List<MovieThumbnail> savedList = new ArrayList<>();
        final String sqlQuery = "SELECT m.movieId, posterLink, title, year FROM Movie m JOIN Save s ON s.movieId = m.movieId AND s.userId = ?";
        
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
                    
                    stmt.setInt(1, Integer.parseInt(currentUserId));

                    try (ResultSet rs = stmt.executeQuery()) {
                        System.out.println("DEBUG: inside query try SavedService");
                        while (rs.next()) {
                            String imageLink;
                            if (rs.getString("posterLink").equals("N/A") || rs.getString("posterLink").equals("")) {
                                imageLink = "https://upload.wikimedia.org/wikipedia/commons/6/65/No-Image-Placeholder.svg";
                            } else {
                                imageLink =rs.getString("posterLink");
                            }
                            String movieId = rs.getString("movieId");
                            String title = rs.getString("title");
                            String year = rs.getString("year");
                            double rating = homeService.getAverageRating(movieId);
                            MovieThumbnail movieToAdd = new MovieThumbnail(movieId, title, year, imageLink, rating);
                            savedList.add(movieToAdd);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } 

        return savedList;
    }
}
