package com.example.demo.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Comment;
import com.example.demo.models.Review;

@Controller
@RequestMapping("/review")
public class ReviewController {
    
    @GetMapping("/{reviewId}")
    public ModelAndView page(@PathVariable("reviewId") String reviewId,@RequestParam(name = "error", required = false) String error) {
        ModelAndView review = new ModelAndView("reviewPage");

        //Be sure to set has comments to false here
        Review reviewObj = new Review("2", "1", "1", "4/29/25 6:40pm", "slkfdmkmmmmmkmkmkmka;kmdklfamd;flkamsfl;kdmflkadfnmasdjklnvdfjkbnsfkdjbnfkjvnsfdjklvnsdfkjlvnsdfkjlvnsdfkbjnsdfgkbjnsdfjkbnsdfjkbnfsdgjkbnsdfjkvnsfdkbjlnsdfkjvnsdfjkbnsdfjknsdfkjbnsdfkjbnskdfgjbnsdfkljbnsdjkfbnsdfkjbnsdfjkbnsfgjkbnskdfjvnafkljbnsfgkjbnafklvjnsgkbjnsdfkbjlns jkbnsdf jkbnsdfkjbnsd kfjn sdfjkbnsdf jklbnsdjkf nbsdfkjbnsdfjklbnsdfkjlbnadfkj",1, false, 3);

        List<Comment> commentList = new ArrayList<>();
        Comment comment1 = new Comment("1","4/30/25 7:40pm","Great Insight");
        Comment comment2 = new Comment("1","4/30/25 7:40pm","Totally Disagree");
        Comment comment3 = new Comment("1","4/30/25 7:40pm","awmdioawmdowiaiomaaaaaaaaaaamamamamamamamamaaowdmaiwkdmaowidmawiodm awoidm awoidm aowidmawio dmawoidm awoiwmdoiwmodimwmdoaiwdmaowidmawoidm");

        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);

        review.addObject("reviewObj", reviewObj);
        review.addObject("comments",commentList);

        return review;
    }
}
