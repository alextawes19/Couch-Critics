package com.example.demo.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Review;
import com.example.demo.models.Movie;

public class SavedService {
    
    private final DataSource dataSource;

    @Autowired
    public SavedService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Get the current user's saved movies
    public List<Movie> getSavedMovies(String currentUserId) {
        List<Movie> savedMovies = null;

        return savedMovies;
    }
}
