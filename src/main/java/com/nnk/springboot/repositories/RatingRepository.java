package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extending the JpaRepository interface to manage CRUD methods for Rating entities, using Spring DataJPA.
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
