package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extending the JpaRepository interface to manage CRUD methods for RuleName entities, using Spring DataJPA.
 */
@Repository
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {

}
