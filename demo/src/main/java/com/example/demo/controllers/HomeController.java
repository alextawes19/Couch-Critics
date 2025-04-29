package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController {
    
    @GetMapping
    public ModelAndView page(@RequestParam(name = "error", required = false) String error) {
        ModelAndView home = new ModelAndView("hello");

        String message = "test fast refresh";
        home.addObject("message", message);

        return home;
    }
}
