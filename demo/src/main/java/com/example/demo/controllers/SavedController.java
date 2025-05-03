package com.example.demo.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Comment;
import com.example.demo.models.MovieThumbnail;
import com.example.demo.models.Review;
import com.example.demo.models.Movie;
import com.example.demo.services.HomeService;
import com.example.demo.services.SavedService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("/saved")
public class SavedController {

    private final SavedService savedService;
    private final UserService userService;
    private final HomeService homeService;

    @Autowired
    public SavedController(SavedService savedService, UserService userService, HomeService homeService) {
        this.savedService = savedService;
        this.userService = userService;
        this.homeService = homeService;
    }

    @GetMapping
    public ModelAndView page() {
        ModelAndView saved = new ModelAndView("savedPage");

        String pageTitle = "Saved Movies";

        saved.addObject("pageTitle", pageTitle);

        //Be sure to set has comments to false here
        
        try {
            System.out.println("DEBUG: SavedController try block");
            // Render saved movies for current user
            String currentUserId = userService.getLoggedInUser().getUserId();
            List<MovieThumbnail> savedMovies = savedService.getSavedMovies(currentUserId);
            System.out.println("DEBUG SavedController: saved movie list size: " + savedMovies.size());
            saved.addObject("thumbnail", savedMovies);

            // No content message
            if (savedMovies.isEmpty()) {
                System.out.println("DEBUG: saved movie list is empty");
                saved.addObject("isNoContent", true);
            }
        } catch (Exception e) {
            // If an error occured
            String errorMessage = "Some error occured!";
            saved.addObject("errorMessage", errorMessage);
        }

        return saved;
    }

    //Save a movie
    @PostMapping("/{movieId}")
    public String save(@PathVariable("movieId") String movieId, @RequestParam(name="add")boolean add) {
        
        System.out.println(add);
        
        return "redirect:/movie/" + movieId;
    }
}
