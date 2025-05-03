package com.example.demo.controllers;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
import com.example.demo.services.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    
    @GetMapping("/{reviewId}")
    public ModelAndView page(@PathVariable("reviewId") String reviewId,@RequestParam(name = "error", required = false) String error) {
        ModelAndView review = new ModelAndView("reviewPage");

        boolean reviewPage = true;
        review.addObject("reviewPage", reviewPage);

        
        Review reviewObj = reviewService.getReview(reviewId);
        

        List<Comment> commentList = reviewService.getComments(reviewId);

        review.addObject("reviewObj", reviewObj);
        review.addObject("comments",commentList);

        return review;
    }


    @PostMapping("/{reviewId}/createcomment")
    public String createComment(@PathVariable("reviewId") String reviewId, @RequestParam(name="comment") String comment,@RequestParam(name = "error", required = false) String error) {

        System.out.println(comment); 
        boolean outcome = reviewService.createComment(comment, reviewId);



        if (outcome) {
            return "redirect:/review/" + reviewId;
        } else {
            String message = URLEncoder.encode("Failed to create comment. Please try again.", StandardCharsets.UTF_8);
            return "redirect:/review/" + reviewId + "?error=" + message;
        }
        
    }
}
