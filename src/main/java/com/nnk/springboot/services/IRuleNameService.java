package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.ResourceNotFoundException;

import java.util.List;

/**
 * Interface to be implemented for managing the services for RuleName entities.
 */
public interface IRuleNameService {

    /**
     * Return all RuleNames.
     *
     * @return The list of all RuleNames
     */
    List<RuleName> findAllRuleNames();

    /**
     * Return a RuleName given its id.
     *
     * @param id The id of the RuleName
     * @return The RuleName corresponding to the id
     * @throws ResourceNotFoundException if no RuleName is found for the given id
     */
    RuleName findRuleNameById(Integer id) throws ResourceNotFoundException;

    /**
     * Create a RuleName.
     *
     * @param ruleName The RuleName to create
     * @return The RuleName created
     */
    RuleName createRuleName(RuleName ruleName);

    /**
     * Update a RuleName.
     *
     * @param ruleName The RuleName to update
     * @return The RuleName updated
     * @throws ResourceNotFoundException if the RuleName to update does not exist
     */
    RuleName updateRuleName(RuleName ruleName) throws ResourceNotFoundException;

    /**
     * Delete a RuleName.
     *
     * @param id The id of the RuleName
     * @throws ResourceNotFoundException if the RuleName to delete does not exist
     */
    void deleteRuleNameById(Integer id) throws ResourceNotFoundException;
}
