package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.MovieThumbnail;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @GetMapping
    public ModelAndView page() {
        ModelAndView profile = new ModelAndView("profilePage");

        String pageTitle = "Reviewed Movies";
        profile.addObject("pageTitle", pageTitle);


        //Will change when User model implemented

        String firstName = "Evan";
        String lastName = "Gavino";
        String username = "evanGavino";

        profile.addObject("firstName", firstName);
        profile.addObject("lastName", lastName);
        profile.addObject("username", username);

        //Be sure to set has comments to false here
        MovieThumbnail thumbnail1 = new MovieThumbnail("1", "Pulp Fiction", "1994", "https://m.media-amazon.com/images/M/MV5BYTViYTE3ZGQtNDBlMC00ZTAyLTkyODMtZGRiZDg0MjA2YThkXkEyXkFqcGc@._V1_SX300.jpg",4.32);
        MovieThumbnail thumbnail2 = new MovieThumbnail("2", "Pulp Fiction", "1994", "https://m.media-amazon.com/images/M/MV5BYTViYTE3ZGQtNDBlMC00ZTAyLTkyODMtZGRiZDg0MjA2YThkXkEyXkFqcGc@._V1_SX300.jpg",4.32);
        MovieThumbnail thumbnail3 = new MovieThumbnail("3", "Pulp Fiction", "1994", "https://m.media-amazon.com/images/M/MV5BYTViYTE3ZGQtNDBlMC00ZTAyLTkyODMtZGRiZDg0MjA2YThkXkEyXkFqcGc@._V1_SX300.jpg",4.32);
        List<MovieThumbnail> thumbnailList = new ArrayList<>();
        thumbnailList.add(thumbnail1);
        thumbnailList.add(thumbnail2);
        thumbnailList.add(thumbnail3);

        profile.addObject("thumbnail", thumbnailList);

        return profile;
    }
}
