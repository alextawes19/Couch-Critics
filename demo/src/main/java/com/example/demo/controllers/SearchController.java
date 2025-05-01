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

import com.example.demo.models.MovieThumbnail;

@Controller
@RequestMapping("search")
public class SearchController {
    @GetMapping
    public ModelAndView page(@RequestParam(name="movieName") String movieName) {
        ModelAndView search = new ModelAndView("searchPage");

        MovieThumbnail thumbnail1 = new MovieThumbnail("1", "Pulp Fiction", "1994", "https://m.media-amazon.com/images/M/MV5BYTViYTE3ZGQtNDBlMC00ZTAyLTkyODMtZGRiZDg0MjA2YThkXkEyXkFqcGc@._V1_SX300.jpg",4.32);
        MovieThumbnail thumbnail2 = new MovieThumbnail("2", "Pulp Fiction", "1994", "https://m.media-amazon.com/images/M/MV5BYTViYTE3ZGQtNDBlMC00ZTAyLTkyODMtZGRiZDg0MjA2YThkXkEyXkFqcGc@._V1_SX300.jpg",4.32);
        MovieThumbnail thumbnail3 = new MovieThumbnail("3", "Pulp Fiction", "1994", "https://m.media-amazon.com/images/M/MV5BYTViYTE3ZGQtNDBlMC00ZTAyLTkyODMtZGRiZDg0MjA2YThkXkEyXkFqcGc@._V1_SX300.jpg",4.32);
        List<MovieThumbnail> thumbnailList = new ArrayList<>();
        thumbnailList.add(thumbnail1);
        thumbnailList.add(thumbnail2);
        thumbnailList.add(thumbnail3);

        if (movieName != "") {
            search.addObject("results", thumbnailList);
        }
        

        return search;
    }

}
