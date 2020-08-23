package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extending the JpaRepository interface to manage CRUD methods for BidList entities, using Spring DataJPA.
 */
@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
