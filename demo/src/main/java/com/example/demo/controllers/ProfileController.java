package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.MovieThumbnail;
import com.example.demo.services.ProfileService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("profile")
public class ProfileController {

    // Services
    private final ProfileService profileService;
    private final UserService userService;

    @Autowired 
    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView page() {
        ModelAndView profile = new ModelAndView("profilePage");
        String pageTitle = "Reviewed Movies";
        profile.addObject("pageTitle", pageTitle);

        try {
            String currentUserId = userService.getLoggedInUser().getUserId();
            List<MovieThumbnail> thumbnailList = profileService.getUserReviews(currentUserId);

            // getUserInfo returns a List<String> where index 0=username, 1=firstName, 2=lastName
            List<String> userInfo = profileService.getUserInfo(currentUserId);
            String username = userInfo.get(0);
            String firstName = userInfo.get(1);
            String lastName = userInfo.get(2);

            profile.addObject("thumbnail", thumbnailList);
            profile.addObject("username", username);
            profile.addObject("firstName", firstName);
            profile.addObject("lastName", lastName);
        } catch (Exception e) {
            profile.addObject("errorMessage", "Some error occurred!");
            e.printStackTrace();
        }
        
        return profile;
    }
}
