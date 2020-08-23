package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extending the JpaRepository interface to manage CRUD methods for Trades, using Spring DataJPA.
 */
@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {

}
