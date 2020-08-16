package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IRatingService {

    List<Rating> findAllRatings();

    Rating findRatingById(Integer id) throws ResourceNotFoundException;

    Rating createRating(Rating rating);

    Rating updateRating(Rating rating);

    void deleteRatingById(Integer id) throws ResourceNotFoundException;
}
