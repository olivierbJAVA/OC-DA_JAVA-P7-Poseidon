package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Class in charge of managing the services for Rating entities.
 */
@Service
@Transactional
public class RatingServiceImpl implements IRatingService {

    @Autowired
    RatingRepository ratingRepository;

    /**
     * Return all Ratings.
     *
     * @return The list of all Ratings
     */
    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }

    /**
     * Return a Rating given its id.
     *
     * @param id The id of the Rating
     * @return The Rating corresponding to the id
     * A ResourceNotFoundException is thrown if no Rating is found for the given id
     */
    @Override
    public Rating findRatingById(Integer id) throws ResourceNotFoundException {
        return ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "Rating"));
    }

    /**
     * Create a Rating.
     *
     * @param rating The Rating to create
     * @return The Rating created
     */
    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Update a Rating.
     *
     * @param rating The Rating to update
     * @return The Rating updated
     * A ResourceNotFoundException is thrown if the Rating to update does not exist
     */
    @Override
    public Rating updateRating(Rating rating) {
        ratingRepository.findById(rating.getId()).orElseThrow(() -> new ResourceNotFoundException(rating.getId(), "Rating"));
        return ratingRepository.save(rating);
    }

    /**
     * Delete a Rating.
     *
     * @param id The id of the Rating
     * A ResourceNotFoundException is thrown if the Rating to delete does not exist
     */
    @Override
    public void deleteRatingById(Integer id) throws ResourceNotFoundException {
        ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "Rating"));
        ratingRepository.deleteById(id);
    }
}
