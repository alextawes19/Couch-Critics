package com.example.demo.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Review;
import com.example.demo.models.User;
import com.example.demo.models.Comment;
import javax.sql.DataSource;



import com.example.demo.models.User;

@Service
public class MovieService {

    private final DataSource dataSource;
    private final UserService userService;

    @Autowired
    public MovieService(DataSource dataSource, UserService userService) {
        this.dataSource = dataSource;
        this.userService = userService;
    }


    public boolean createReview(String reviewContents, String movieId, String score) {
        System.out.println("Adding review: " + reviewContents);

        if (reviewContents.length() <= 0) {
            return false;
        }

        final String insertReview = "INSERT INTO review (userId, movieId, reviewDate, reviewText, score) values (?, ?, NOW(), ?, ?)";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedCommentInsert = conn.prepareStatement(insertReview)) {
            
            User currUser = userService.getLoggedInUser();
            preparedCommentInsert.setString(1, currUser.getUserId());
            preparedCommentInsert.setString(2, movieId);
            preparedCommentInsert.setString(3, reviewContents);
            preparedCommentInsert.setString(4, score);

            preparedCommentInsert.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }


        return true;
    }

    public List<Review> getReviews(String movieId) {
        System.out.println("Retrieving reviews from movie: " + movieId);

        final String getReviews = "SELECT r.reviewId, r.movieId, r.userId, u.firstName, u.lastName, r.reviewDate, r.reviewText, r.score, " + 
        "IF(COUNT(c.commentId) = 0, FALSE, TRUE) AS hasComments, " +
        " COUNT(c.commentId) AS commentCount " +
        " FROM review r " + 
        " JOIN user u ON r.userId = u.userId " +
        " LEFT JOIN comment c ON r.reviewId = c.reviewId " + 
        " WHERE r.movieId = ? " + 
        " GROUP BY r.reviewId, r.movieId, r.userId, u.firstName, u.lastName, r.reviewDate, r.reviewText, r.score;";

        List<Review> result = new ArrayList<>();

        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedComments = conn.prepareStatement(getReviews)) {
            
            preparedComments.setString(1, movieId);

            try(ResultSet rs = preparedComments.executeQuery()) {
                while(rs.next()) {
                    String reviewId = rs.getString("reviewId");
                    String userId = rs.getString("userId");
                    String reviewDate = rs.getString("reviewDate");
                    String reviewText = rs.getString("reviewText");
                    int score = rs.getInt("score");
                    boolean hasComments = rs.getBoolean("hasComments");
                    int commentCount = rs.getInt("commentCount");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    result.add(new Review(reviewId, userId, movieId, reviewDate, reviewText, score, hasComments, commentCount, firstName, lastName));
                }
                return result;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
