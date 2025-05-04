package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import javax.sql.DataSource;

import com.example.demo.models.Movie;
import com.example.demo.models.Review;
import com.example.demo.services.UserService;
import com.example.demo.services.MovieService;
import com.example.demo.services.HomeService;

import java.util.List;

import javax.sql.DataSource;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/movie")
public class MovieController {

    private DataSource dataSource;
    private UserService userService;
    private MovieService movieService;
    private HomeService homeService;

    @Autowired
    public MovieController(DataSource dataSource, UserService userService, MovieService movieService, HomeService homeService ) {
        this.dataSource = dataSource;
        this.userService = userService;
        this.movieService = movieService;
        this.homeService = homeService;
    }

    
    @GetMapping("/{movieId}")
    public ModelAndView page(@PathVariable("movieId") String movieId,@RequestParam(name = "error", required = false) String error) {
        //ModelAndView movie = new ModelAndView("moviePage");

        System.out.println("The user is attempting to view movie with id: " + movieId);
        ModelAndView mv = new ModelAndView("moviePage");


        // Get the current user's ID
        String userId = userService.getLoggedInUser().getUserId();
        //List<Comment> comments = new ArrayList<>();
        //ExpandedPost ep = null;
        //SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy, hh:mm a");
        //DATE_FORMAT(postDate, '%Y-%m-%d %h:%i:%s %p')
        final String movieSQL = "SELECT movie.movieId, movie.title, movie.overview, movie.posterLink, movie.runtime, movie.genre, movie.director, movie.year, review.score " +
            "FROM movie LEFT JOIN review on review.movieId = movie.movieId " + 
            "WHERE movie.movieId = " + movieId + ";";

        final String isSavedSQL = "SELECT COUNT(*) AS isSaved " + 
            "FROM Save WHERE movieId = " + movieId + " AND userId = " + userId + ";";

        Movie retrievedMovie = null;
        List<Review> reviews = movieService.getReviews(movieId);
        double averageRating = homeService.getAverageRating(movieId);


        String title = null, overview = null, runtime = null, score = null, genre = null, director = null, posterLink = null, year = null;
        boolean saved = false;

         try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmtMovie = conn.prepareStatement(movieSQL);
            PreparedStatement pstmtIsSaved = conn.prepareStatement(isSavedSQL);
            try {
                ResultSet rsMovie = pstmtMovie.executeQuery();
                ResultSet rsIsSaved = pstmtIsSaved.executeQuery();
                //SELECT postText, postDate, p.userId, userName, firstName, lastName " 
                if (rsMovie.next()) {
                    if (rsMovie.getString("posterLink").equals("N/A") || rsMovie.getString("posterLink").equals("")) {
                        posterLink = "https://upload.wikimedia.org/wikipedia/commons/6/65/No-Image-Placeholder.svg";
                    } else {
                        posterLink =rsMovie.getString("posterLink");
                    }
                    //Int movieId = rsMovie.getString("movieId");
                    title =  rsMovie.getString("title");
                    overview =  rsMovie.getString("overview");
                    runtime =  rsMovie.getString("runtime");
                    genre =  rsMovie.getString("genre");
                    director =  rsMovie.getString("director");
                    year =  Integer.toString(rsMovie.getInt("year"));
                    score =  Integer.toString(rsMovie.getInt("score"));
                }
                if (rsIsSaved.next()) {
                    saved = rsIsSaved.getInt("isSaved") != 0;
                }
                retrievedMovie = new Movie(movieId, title, runtime, year, score, overview, genre, director, posterLink, saved); 
            } catch (SQLException e) {
                System.out.println("Unable to connect or prepare sql statement");
                // Handle SQL exception
                e.printStackTrace();
            } 
        } catch (SQLException e) {
            System.out.println("Unable to connect or prepare sql statement");
            // Handle SQL exception
            e.printStackTrace();
        } 
        System.out.println(movieId + " " + title + " " + genre);
        mv.addObject("movie", retrievedMovie);
        mv.addObject("reviews", reviews);
        mv.addObject("average", averageRating);

        return mv;
    }
    @PostMapping("/{movieId}/createreview")
    public String testForm(@PathVariable("movieId") String movieId, @RequestParam(name="score")int score,@RequestParam(name="review")String reviewContents) {
        
        System.out.println(reviewContents); 
        boolean outcome = movieService.createReview(reviewContents, movieId, Integer.toString(score));

        if (outcome) {
            return "redirect:/movie/" + movieId;
        } 

        String message = URLEncoder.encode("Failed to create Review. Please try again.", StandardCharsets.UTF_8);
        return "redirect:/movie/" + movieId + "?error=" + message;
    }
}

