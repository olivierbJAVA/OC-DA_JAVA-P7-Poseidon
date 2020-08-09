package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RatingImplService implements IRatingService {

    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating findRatingById(Integer id) throws RecordNotFoundException {
        Optional<Rating> rating = ratingRepository.findById(id);

        if(rating.isPresent()) {
            return rating.get();
        } else {
            throw new RecordNotFoundException("No record exist for given id");
        }

        //return ratingRepository.getOne(id);
    }

    @Override
    public Rating createRating(Rating Rating) {
        return ratingRepository.save(Rating);
    }

    @Override
    public Rating updateRating(Rating Rating) {
        return ratingRepository.save(Rating);
    }

    @Override
    public void deleteRating(Integer id) throws RecordNotFoundException {
        Optional<Rating> rating = ratingRepository.findById(id);

        if(rating.isPresent()) {
            ratingRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No record exist for given id");
        }

        //ratingRepository.deleteById(id);
    }
}
