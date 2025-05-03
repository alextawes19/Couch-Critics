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
import com.example.demo.models.User;
import com.example.demo.models.Comment;

@Service
public class ReviewService {
    
    private final DataSource dataSource;
    private final UserService userService;

    @Autowired
    public ReviewService(DataSource dataSource, UserService userService) {
        this.dataSource = dataSource;
        this.userService = userService;
    }

    public Review getReview(String reviewId) {
        System.out.println("Retrieving: " + reviewId);

        final String getReview = "SELECT userId,movieId,reviewDate,reviewText,score FROM review WHERE reviewId=?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedReview = conn.prepareStatement(getReview)) {
            
            preparedReview.setString(1, reviewId);

            try(ResultSet rs = preparedReview.executeQuery()) {
                if(rs.next()) {
                    String userId = rs.getString("userId");
                    String movieId = rs.getString("movieId");
                    String reviewDate = rs.getString("reviewDate");
                    String reviewText = rs.getString("reviewText");
                    int score = rs.getInt("score");
                    Review returnedReview = new Review(reviewId,userId, movieId, reviewDate, reviewText, score, false, 0);
                    return returnedReview;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        return null;
    }

    public List<Comment> getComments(String reviewId) {
        System.out.println("Retrieving comments from review: " + reviewId);

        final String getComments = "SELECT commentId,commentDate,commentText FROM comment WHERE reviewId=?;";

        List<Comment> result = new ArrayList<>();

        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedComments = conn.prepareStatement(getComments)) {
            
            preparedComments.setString(1, reviewId);

            try(ResultSet rs = preparedComments.executeQuery()) {
                while(rs.next()) {
                    String commentId = rs.getString("commentId");
                    String commentDate = rs.getString("commentDate");
                    String commentText = rs.getString("commentText");
                    result.add(new Comment(commentId,commentDate,commentText));
                }
                return result;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean createComment(String commentText, String reviewId) {
        System.out.println("Adding comment: " + commentText);

        if (commentText.length() <= 0) {
            return false;
        }

        final String insertComments = "INSERT INTO comment (userId,reviewId,commentDate,commentText) values (?,?,NOW(),?)";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement preparedCommentInsert = conn.prepareStatement(insertComments)) {
            
            User currUser = userService.getLoggedInUser();
            preparedCommentInsert.setString(1, currUser.getUserId());
            preparedCommentInsert.setString(2,reviewId);
            preparedCommentInsert.setString(3, commentText);

            preparedCommentInsert.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }


        return true;
    }
}
