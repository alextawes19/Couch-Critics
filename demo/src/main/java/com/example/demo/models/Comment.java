package com.example.demo.models;

public class Comment {
    
    private final String commentId;

    private final String commentDate;

    private final String commentText;


    public Comment(String commentId, String commentDate, String commentText) {
        this.commentId = commentId;
        this.commentDate = commentDate;
        this.commentText = commentText;
    }


    public String getCommentId() {
        return commentId;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public String getCommentText() {
        return commentText;
    }

    

}
