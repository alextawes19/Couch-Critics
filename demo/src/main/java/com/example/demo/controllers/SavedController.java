package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Comment;
import com.example.demo.models.Review;

@Controller
@RequestMapping("/savedMovies")
public class SavedController {

    @GetMapping
    public ModelAndView page() {
        ModelAndView saved = new ModelAndView("savedPage");

        //Be sure to set has comments to false here
        

        return saved;
    }

    @PostMapping("/{movieId}")
    public String save(@PathVariable("movieId") String movieId, @RequestParam(name="add")boolean add) {
        
        System.out.println(add);
        
        return "redirect:/movie/" + movieId;
    }
}
