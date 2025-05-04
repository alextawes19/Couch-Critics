package com.example.demo.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.demo.services.UserService;

/**
    Intercepts web requests
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {


    private UserService userService;

    @Autowired
    public AuthInterceptor(UserService userService) {
        this.userService = userService;
    }

    /*Redirects if user not logged in*/
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        if (!userService.isAuthenticated()) {

            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}

