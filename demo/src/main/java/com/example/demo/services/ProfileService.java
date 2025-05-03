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
public class ProfileService {

    private final DataSource dataSource;
    private final HomeService homeService;

    @Autowired
    public ProfileService(DataSource dataSource, HomeService homeService) {
        this.dataSource = dataSource;
        this.homeService = homeService;
    }

    public List<MovieThumbnail> getUserReviews(String currentUserId) {
        System.out.println("ProfileService: User is attempting to view their reviewed movies");
        List<MovieThumbnail> reviewedMovies = new ArrayList<>();

        // Query to get a user's reviews
        final String sql = "SELECT m.movieId, posterLink, title, year FROM Movie m JOIN Review r ON r.movieId = m.movieId AND r.userId = ?";

        // Run query with datasource 
        try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(currentUserId));

            try (ResultSet rs = stmt.executeQuery()) {
                // Create review objects to be displayed
                while(rs.next()) {
                    // Get review information
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
                    reviewedMovies.add(movieToAdd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reviewedMovies;
    }

    // getUserInfo returns a List<String> where index 0=username, 1=firstName, 2=lastName
    public List<String> getUserInfo(String currentUserId) {
        System.out.println("ProfileService: getting current user's information");
        List<String> userInfo = new ArrayList<>();
    
        // Query to get user's info
        final String sql = "SELECT username, firstName, lastName from User where userId = ?";

        try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, Integer.parseInt(currentUserId));

            // Execute query to get user info
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    userInfo.add(username);

                    String firstName = rs.getString("firstName");
                    userInfo.add(firstName);
                   
                    String lastName = rs.getString("lastName");
                    userInfo.add(lastName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 


        return userInfo;
    }
}
