package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.RecordNotFoundException;

import java.util.List;

public interface IRatingService {

    List<Rating> findAllRatings();

    Rating findRatingById(Integer id) throws RecordNotFoundException;

    Rating createRating(Rating rating);

    Rating updateRating(Rating rating);

    void deleteRating(Integer id) throws RecordNotFoundException;
}
