package com.example.demo.controllers;

import com.example.demo.models.Movie;
import com.example.demo.services.SavedService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/saved")
public class SavedController {

    private final SavedService savedService;
    private final UserService userService;
    private final DataSource dataSource;

    @Autowired
    public SavedController(SavedService savedService, UserService userService, DataSource dataSource) {
        this.savedService = savedService;
        this.userService = userService;
        this.dataSource = dataSource;
    }

    @GetMapping
    public ModelAndView page() {
        ModelAndView saved = new ModelAndView("savedPage");
        String pageTitle = "Saved Movies";
        saved.addObject("pageTitle", pageTitle);

        try {
            String currentUserId = userService.getLoggedInUser().getUserId();
            List<Movie> movies = savedService.getSavedMovies(currentUserId);
            saved.addObject("movies", movies);

            if (movies.isEmpty()) {
                saved.addObject("isNoContent", true);
            }
        } catch (Exception e) {
            saved.addObject("errorMessage", "Some error occurred!");
            e.printStackTrace();
        }

        return saved;
    }

    @PostMapping("/{movieId}")
    public String save(@PathVariable("movieId") String movieId, @RequestParam(name = "add") boolean add) {
        System.out.println("The user is attempting to save or unsave a movie:");
        System.out.println("\tmovieId: " + movieId);
        System.out.println("\tadd: " + add);

        boolean errorFlag = false;
        String userId = userService.getLoggedInUser().getUserId();

        //String isSavedSQL = "SELECT COUNT(*) AS Saved FROM save WHERE movieId = " + movieId + " AND userId = " + userId + ";";
        String saveSQL = "INSERT INTO save VALUES (" + userId + "," + movieId + ");";
        String unSaveSQL = "DELETE FROM save WHERE movieId = " + movieId + " AND userId = " + userId + ";";

        //int saved = 0;

        try (Connection conn = dataSource.getConnection();
             //PreparedStatement pstmtCheck = conn.prepareStatement(isSavedSQL);
             PreparedStatement pstmtSave = conn.prepareStatement(saveSQL);
             PreparedStatement pstmtUnsave = conn.prepareStatement(unSaveSQL)) {

            /*ResultSet rsIsSaved = pstmtCheck.executeQuery();
            if (rsIsSaved.next()) {
                saved = rsIsSaved.getInt("Saved");
            }*/

            if (add) {
                pstmtSave.executeUpdate();
            } else {
                pstmtUnsave.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Database operation failed");
            e.printStackTrace();
            errorFlag = true;
        }

        String message = URLEncoder.encode("Failed to (un)save the movie. Please try again.", StandardCharsets.UTF_8);
        if (errorFlag) {
            return "redirect:/movie/" + movieId + "?error=" + message;
        } else {
            return "redirect:/movie/" + movieId;
        }
    }
}
