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
public class ProfileService {

    private final DataSource dataSource;

    @Autowired
    public ProfileService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Review> getUserReviews(String currentUserId) {
        System.out.println("ProfileService: User is attempting to view user reviews");
        List<Review> reviews = new ArrayList<>();

        // Query to get a user's reviews
        final String sql = "";

        // Run query with datasource 
        try (Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(currentUserId));

            try (ResultSet rs = stmt.executeQuery()) {
                // Create review objects to be displayed
                while(rs.next()) {
                    // Get review information
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reviews;
    }
}
