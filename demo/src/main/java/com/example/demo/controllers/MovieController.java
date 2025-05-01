package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Movie;
import com.example.demo.models.Review;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/movie")
public class MovieController {
    
    @GetMapping("/{movieId}")
    public ModelAndView page(@PathVariable("movieId") String movieId,@RequestParam(name = "error", required = false) String error) {
        ModelAndView movie = new ModelAndView("moviePage");

        String genre = "Crime, Drama";
        Movie test = new Movie("1","Pulp Fiction","154 min","1994","A","The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",genre,"Quentin Tarantino","https://m.media-amazon.com/images/M/MV5BYTViYTE3ZGQtNDBlMC00ZTAyLTkyODMtZGRiZDg0MjA2YThkXkEyXkFqcGc@._V1_SX300.jpg", false);

        movie.addObject("placeholder", test);

        List<Review> reviewList = new ArrayList<>();
        Review review1 = new Review("1", "1", "1", "4/29/25 5:40pm", "God this movie is shit", 1, false, 0);
        Review review2 = new Review("2", "1", "1", "4/29/25 6:40pm", "slkfdmkmmmmmkmkmkmka;kmdklfamd;flkamsfl;kdmflkadfnmasdjklnvdfjkbnsfkdjbnfkjvnsfdjklvnsdfkjlvnsdfkjlvnsdfkbjnsdfgkbjnsdfjkbnsdfjkbnfsdgjkbnsdfjkvnsfdkbjlnsdfkjvnsdfjkbnsdfjknsdfkjbnsdfkjbnskdfgjbnsdfkljbnsdjkfbnsdfkjbnsdfjkbnsfgjkbnskdfjvnafkljbnsfgkjbnafklvjnsgkbjnsdfkbjlns jkbnsdf jkbnsdfkjbnsd kfjn sdfjkbnsdf jklbnsdjkf nbsdfkjbnsdfjklbnsdfkjlbnadfkj", 4, true, 3);
        reviewList.add(review1);
        reviewList.add(review2);

        movie.addObject("reviews", reviewList);

        return movie;
    }
    @PostMapping("/{movieId}/createreview")
    public String testForm(@PathVariable("movieId") String movieId, @RequestParam(name="score")int score,@RequestParam(name="review")String reviewContents) {
        
        System.out.println(score);
        System.out.println(reviewContents);
        
        return "redirect:/movie/" + movieId;
    }
}

