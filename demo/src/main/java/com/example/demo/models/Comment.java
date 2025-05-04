package com.example.demo.models;

public class Comment {
    
    private final String commentId;

    private final String commentDate;

    private final String commentText;

    private final String firstName;

    private final String lastName;


    public Comment(String commentId, String commentDate, String commentText, String firstName, String lastName) {
        this.commentId = commentId;
        this.commentDate = commentDate;
        this.commentText = commentText;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
