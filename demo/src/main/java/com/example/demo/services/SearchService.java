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
import com.example.demo.models.MovieThumbnail;

@Service
public class SearchService {

    private final DataSource dataSource;

    @Autowired
    public SearchService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<MovieThumbnail> search(String movieName) {
        System.out.println("Searching for " + movieName);
        List<MovieThumbnail> result = new ArrayList<>();

        final String searchStatement = "SELECT m.title,m.year,m.movieId,m.posterLink FROM movie m WHERE m.title LIKE ?;";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedSearch = conn.prepareStatement(searchStatement)) {
            
            preparedSearch.setString(1, "%"+movieName+"%");

            
            try(ResultSet rs = preparedSearch.executeQuery()) {
                while(rs.next()) {
                    String imageLink;
                    if (rs.getString("posterLink").equals("N/A") || rs.getString("posterLink").equals("")) {
                        imageLink = "https://upload.wikimedia.org/wikipedia/commons/6/65/No-Image-Placeholder.svg";
                    } else {
                        imageLink =rs.getString("posterLink");
                    }
                    String movieId = rs.getString("m.movieId");
                    String movieTitle = rs.getString("m.title");
                    String movieYear = rs.getString("m.year");
                    String moviePoster = imageLink;
                    double average = getAverageScore(movieId);
                    MovieThumbnail movie = new MovieThumbnail(movieId, movieTitle, movieYear, moviePoster, average);
                    result.add(movie);
                }
            }
            
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }

        return result;
    }


    public double getAverageScore(String movieId) {
        double average = 0;

        final String searchStatement = "SELECT r.movieId,avg(r.score) as average FROM review r WHERE r.movieId=? GROUP BY r.movieId;";
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
