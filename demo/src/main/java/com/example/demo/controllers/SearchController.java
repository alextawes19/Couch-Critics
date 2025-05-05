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

import com.example.demo.models.MovieThumbnail;
import com.example.demo.services.SearchService;


@Controller
@RequestMapping("search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping
    public ModelAndView page(@RequestParam(name="movieName") String movieName, @RequestParam(name="year") String year) {
        ModelAndView search = new ModelAndView("searchPage");

        if (movieName != "" || year != "") {
            
            List<MovieThumbnail> thumbnailList = searchService.search(movieName,year);
            search.addObject("results", thumbnailList);

        }
        

        return search;
    }

}
