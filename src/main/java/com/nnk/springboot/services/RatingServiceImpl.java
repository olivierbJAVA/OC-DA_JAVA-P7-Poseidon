package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RatingServiceImpl implements IRatingService {

    @Autowired
    RatingRepository ratingRepository;

    public List<Rating> findAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating findRatingById(Integer id) throws ResourceNotFoundException {
        Optional<Rating> rating = ratingRepository.findById(id);

        if(rating.isPresent()) {
            return rating.get();
        } else {
            throw new ResourceNotFoundException(id);
        }
    /*
        return ratingRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException("No record exist for given id"));
    */
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
    public void deleteRatingById(Integer id) throws ResourceNotFoundException {
        Optional<Rating> rating = ratingRepository.findById(id);

        if(rating.isPresent()) {
            ratingRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
            //throw new RessourceNotFoundException(HttpStatus.NOT_FOUND, "No record exist for given id", id);
        }

        //ratingRepository.deleteById(id);
    }
}
