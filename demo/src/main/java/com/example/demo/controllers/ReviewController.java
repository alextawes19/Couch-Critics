package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/review")
public class ReviewController {
    
    @GetMapping("/{reviewId}")
    public ModelAndView page(@PathVariable("reviewId") String reviewId,@RequestParam(name = "error", required = false) String error) {
        ModelAndView review = new ModelAndView("reviewPage");

        review.addObject("reviewId", reviewId);

        return review;
    }
}
