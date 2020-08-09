package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RatingImplService implements IRatingService {

    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(Integer id) {
        return ratingRepository.getOne(id);
    }

    @Override
    public Rating saveRating(Rating Rating) {
        return ratingRepository.save(Rating);
    }

    @Override
    public Rating updateRating(Rating Rating) {
        return ratingRepository.save(Rating);
    }

    @Override
    public void deleteRating(Integer id) {
        ratingRepository.deleteById(id);
    }
}
