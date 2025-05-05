-- Create Database 
CREATE DATABASE if NOT EXISTS couch_critics;

-- Use database 
use couch_critics;

-- Create the User table
CREATE TABLE User (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    firstName VARCHAR(100),
    lastName VARCHAR(100)
);

-- Create the Movie table
CREATE TABLE Movie (
    movieId INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    overview TEXT,
    posterLink TEXT,
    runtime VARCHAR(20),
    genre VARCHAR(100),
    director VARCHAR(100),
    year INT
);

-- Create the Review table
CREATE TABLE Review (
    reviewId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    movieId INT NOT NULL,
    reviewDate DATE,
    reviewText TEXT,
    score INT,
    FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE,
    FOREIGN KEY (movieId) REFERENCES Movie(movieId) ON DELETE CASCADE
);

-- Create the Comment table
CREATE TABLE Comment (
    commentId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    reviewId INT NOT NULL,
    commentDate DATE,
    commentText TEXT,
    FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE,
    FOREIGN KEY (reviewId) REFERENCES Review(reviewId) ON DELETE CASCADE
);

-- Create the Save table
CREATE TABLE Save (
    userId INT NOT NULL,
    movieId INT NOT NULL,
    PRIMARY KEY (userId, movieId),
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (movieId) REFERENCES Movie(movieId)
);

-- Index for finding movies by title
CREATE INDEX idx_movie_title ON Movie(title);

-- Index for finding movies by year
CREATE INDEX idx_movie_year ON Movie(year);
