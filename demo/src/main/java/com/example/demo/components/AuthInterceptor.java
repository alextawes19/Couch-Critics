package com.example.demo.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.demo.services.UserService;

/**
 * This class intercepts requests that goes into controllers.
 * The intercepted requests are redirected to the login page if the
 * user is not logged in.
 * The intercepter is selectively applied to different URL patterns.
 * See WebConfig.java.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    // This service enables user services.
    private UserService userService;

    /**
     * This is an example of injecting an object as a dependency.
     * In this case Spring Boot will initialize a UserService
     * instance and provide it as AuthInterceptor is initialized.
     * Note: AuthInterceptor is also initialized by Spring Boot.
     * This is a part of inversion of control.
     */
    @Autowired
    public AuthInterceptor(UserService userService) {
        this.userService = userService;
    }

    /**
     * This is the overriden method that does the actual redirection
     * if the user is not logged in.
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        if (!userService.isAuthenticated()) {
            // Redirect the user to login.
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}

