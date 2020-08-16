package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import com.nnk.springboot.services.IRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RatingController {

    private static final Logger logger = LoggerFactory.getLogger(RatingController.class);

    @Autowired
    IRatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model) {

        logger.info("Request : GET /rating/list");

        List<Rating> ratings = ratingService.findAllRatings();
        model.addAttribute("ratings", ratings);

        logger.info("Success : ratings found, returning 'rating/list' view");

        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {

        logger.info("Request : GET /rating/add");
        logger.info("Success : returning 'rating/add' view");

        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {

        logger.info("Request : POST /rating/validate");

        if (!result.hasErrors()) {
            ratingService.createRating(rating);
            //model.addAttribute("ratings", ratingService.findAllRatings());

            logger.info("Success : new rating created, redirect to '/rating/list' view");

            return "redirect:/rating/list";
        }

        logger.error("Error in fields validation : new rating not created, returning '/rating/add' view");

        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Request : GET /rating/update/{}", id);

        Rating rating = ratingService.findRatingById(id);
        model.addAttribute("rating", rating);

        logger.info("Success : rating with id {} to update found, returning '/rating/update' view", id);

        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@Valid Rating rating, BindingResult result, Model model) {
        //public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,BindingResult result, Model model) {

        logger.info("Request : POST /rating/update/{}", rating.getId());

        if (result.hasErrors()) {

            logger.error("Error in fields : rating with id {} not updated, returning '/rating/update' view", rating.getId());

            return "rating/update";
        }
        //rating.setId(id);// fonctionne ? -> oui car id dans le Rating
        ratingService.updateRating(rating);
        //model.addAttribute("ratings", ratingService.findAllRatings());

        logger.info("Success : rating with id {} updated, redirect to '/rating/list'", rating.getId());

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {

        logger.info("Request : GET /rating/delete/{}", id);

        //Rating rating = ratingService.findRatingById(id);
        ratingService.deleteRatingById(id);

        /*
        try {
            ratingService.deleteRatingById(id);
        } catch (RecordNotFoundException e) {
            System.out.println("Record not found");
            return "/errorRecordNotFound";
        }
        */

        //model.addAttribute("ratings", ratingService.findAllRatings());

        logger.info("Success : rating with id {} deleted, redirect to '/rating/list'", id);

        return "redirect:/rating/list";
    }
}
