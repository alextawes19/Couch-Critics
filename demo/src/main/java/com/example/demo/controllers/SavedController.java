package com.example.demo.controllers;

import java.util.ArrayList;
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
import com.example.demo.models.Review;
import com.example.demo.models.Movie;
import com.example.demo.services.SavedService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("/saved")
public class SavedController {

    private final SavedService savedService;
    private final UserService userService;

    @Autowired
    public SavedController(SavedService savedService, UserService userService) {
        this.savedService = savedService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView page() {
        ModelAndView saved = new ModelAndView("savedPage");

        //Be sure to set has comments to false here
        
        try {
            // Render saved movies for current user
            String currentUserId = userService.getLoggedInUser().getUserId();
            List<Movie> movies = savedService.getSavedMovies(currentUserId);
            saved.addObject("movies", movies);

            // No content message
            if (movies.isEmpty()) {
                saved.addObject("isNoContent", true);
            }
        } catch (Exception e) {
            // If an error occured
            String errorMessage = "Some error occured!";
            saved.addObject("errorMessage", errorMessage);
        }

        return saved;
    }

    @PostMapping("/{movieId}")
    public String save(@PathVariable("movieId") String movieId, @RequestParam(name="add")boolean add) {
        
        System.out.println(add);
        
        return "redirect:/movie/" + movieId;
    }
}
