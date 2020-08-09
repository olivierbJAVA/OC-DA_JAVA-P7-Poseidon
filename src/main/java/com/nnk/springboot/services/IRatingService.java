package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;

import java.util.List;

public interface IRatingService {

    List<Rating> getAllRatings();

    Rating getRatingById(Integer id);

    Rating saveRating(Rating rating);

    Rating updateRating(Rating rating);

    void deleteRating(Integer id);
}
