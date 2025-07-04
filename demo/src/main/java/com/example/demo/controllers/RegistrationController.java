package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.services.UserService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This function serves the /register page.
     */
    @GetMapping
    public ModelAndView page(@RequestParam(name = "error", required = false) String error) {
        ModelAndView mv = new ModelAndView("registerPage");

        
        mv.addObject("errorMessage", error);

        return mv;
    }

    /**
     * Handles user registration form submissions.
     */
    @PostMapping
    public String register(@RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("passwordRepeat") String passwordRepeat,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) throws UnsupportedEncodingException {
        // Passwords should have at least 3 chars.
        if (password.trim().length() < 3) {
            String message = URLEncoder.encode("Passwords should have at least 3 nonempty letters.", "UTF-8");
            return "redirect:/register?error=" + message;
        }

        if (!password.equals(passwordRepeat)) {
            // If the password repeat does not match the password redirect to the registration page with an error message.
            String message = URLEncoder.encode("Passwords do not match.", "UTF-8");
            return "redirect:/register?error=" + message;
        }

        try {
            boolean registrationSuccess = userService.registerUser(username,
                    password, firstName, lastName);
            if (registrationSuccess) {
                // If the registration worked redirect to the login page.
                return "redirect:/login";
            } else {
                // If the registration fails redirect to registration page with a message.
                String message = URLEncoder.encode("Registration failed. Please try again.", "UTF-8");
                return "redirect:/register?error=" + message;
            }
        } catch (Exception e) {
            // If the registration fails redirect to registration page with a message.
            String message = URLEncoder.encode("An error occurred: " + e.getMessage(), "UTF-8");
            return "redirect:/register?error=" + message;
        }
    }

}
