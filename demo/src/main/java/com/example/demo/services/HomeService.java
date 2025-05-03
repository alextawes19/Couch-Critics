package com.example.demo.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.example.demo.models.Movie;
import com.example.demo.models.MovieThumbnail;

import java.sql.Connection;

@Service
public class HomeService {
    
    private final DataSource dataSource;

    public HomeService(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public List<MovieThumbnail> getAllMovies() throws SQLException {
        List<MovieThumbnail> movieList = new ArrayList<>();
        final String sqlQuery = "SELECT movieId, posterLink, title, year FROM Movie ORDER BY title";
        
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
                    
                    try (ResultSet rs = stmt.executeQuery()) {
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
                            double rating = getAverageRating(movieId);
                            MovieThumbnail movieToAdd = new MovieThumbnail(movieId, title, year, imageLink, rating);
                                movieList.add(movieToAdd);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } 

        return movieList;
    }

    private double getAverageRating(String movieId) {
        double average = 0;

        final String searchStatement = "select r.movieId,avg(r.score) as average from review r where r.movieId=? group by r.movieId;";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedSearch = conn.prepareStatement(searchStatement)) {
            
            preparedSearch.setString(1, movieId);

            try(ResultSet rs = preparedSearch.executeQuery()) {
                if (rs.next()) {
                    average = rs.getDouble("average");
                }
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }

        return average;
    }
}
