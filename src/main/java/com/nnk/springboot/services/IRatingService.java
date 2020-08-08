package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;

import java.util.List;

public interface IRatingService {

    List<Rating> getAllRatings();

    Rating getRatingById(Integer id);

    Rating saveOrUpdate(Rating rating);

    void delete (Integer id);

}
