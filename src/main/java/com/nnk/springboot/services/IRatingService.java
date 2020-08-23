package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.ResourceNotFoundException;

import java.util.List;

/**
 * Interface to implement for managing the services for Rating entities.
 */
public interface IRatingService {

    /**
     * Return all Ratings.
     *
     * @return The list of all Ratings
     */
    List<Rating> findAllRatings();

    /**
     * Return a Rating given its id.
     *
     * @param id The id of the Rating
     * @return The Rating corresponding to the id
     * A ResourceNotFoundException is thrown if no Rating is found for the given id
     */
    Rating findRatingById(Integer id) throws ResourceNotFoundException;

    /**
     * Create a Rating.
     *
     * @param rating The Rating to create
     * @return The Rating created
     */
    Rating createRating(Rating rating);

    /**
     * Update a Rating.
     *
     * @param rating The Rating to update
     * @return The Rating updated
     * A ResourceNotFoundException is thrown if the Rating to update does not exist
     */
    Rating updateRating(Rating rating) throws ResourceNotFoundException;

    /**
     * Delete a Rating.
     *
     * @param id The id of the Rating
     * A ResourceNotFoundException is thrown if the Rating to delete does not exist
     */
    void deleteRatingById(Integer id) throws ResourceNotFoundException;
}
