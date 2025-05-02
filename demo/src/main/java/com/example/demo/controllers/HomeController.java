package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.MovieThumbnail;
import com.example.demo.services.HomeService;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequestMapping
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }
    
    @GetMapping
    public ModelAndView page(@RequestParam(name = "error", required = false) String error) {
        ModelAndView home = new ModelAndView("home");

        String pageTitle = "Movie List";
        home.addObject("pageTitle", pageTitle);

        List<MovieThumbnail> thumbnailList;
        try {
            thumbnailList = homeService.getAllMovies();
        } catch (SQLException e) {
            e.printStackTrace();
            thumbnailList = Collections.emptyList();
        }
        home.addObject("thumbnail", thumbnailList);

        return home;
    }

}