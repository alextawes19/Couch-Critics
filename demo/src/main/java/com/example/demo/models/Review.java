package com.example.demo.models;

public class Review {
    
    private final String reviewId;

    private final String userId;

    private final String movieId;

    private final String reviewDate;

    private final String reviewText;

    private final int score;

    private final boolean hasComments;

    private final int commentCount;

    public Review(String reviewId, String userId, String movieId, String reviewDate, String reviewText, int score, boolean hasComments, int commentCount) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.movieId = movieId;
        this.reviewDate = reviewDate;
        this.reviewText = reviewText;
        this.score = score;
        this.hasComments = hasComments;
        this.commentCount = commentCount;
    }

    public String getReviewId() {
        return reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getScore() {
        return score;
    }

    public boolean getHasComments() {
        return hasComments;
    }

    public int getCommentCount() {
        return commentCount;
    }
}
