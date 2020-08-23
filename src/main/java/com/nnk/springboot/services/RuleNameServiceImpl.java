package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Class in charge of managing the services for the RuleNames.
 */
@Service
@Transactional
public class RuleNameServiceImpl implements IRuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    /**
     * Return all RuleNames.
     *
     * @return The list of all RuleNames
     */
    public List<RuleName> findAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    /**
     * Return a RuleName given its id.
     *
     * @param id The id of the RuleName
     * @return The RuleName corresponding to the id
     * A ResourceNotFoundException is thrown if no RuleName is found for the given id
     */
    @Override
    public RuleName findRuleNameById(Integer id) throws ResourceNotFoundException {
        return ruleNameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "RuleName"));
    }

    /**
     * Create a RuleName.
     *
     * @param ruleName The RuleName to create
     * @return The RuleName created
     */
    @Override
    public RuleName createRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Update a RuleName.
     *
     * @param ruleName The RuleName to update
     * @return The RuleName updated
     * A ResourceNotFoundException is thrown if the RuleName to update does not exist
     */
    @Override
    public RuleName updateRuleName(RuleName ruleName) {
        ruleNameRepository.findById(ruleName.getId()).orElseThrow(() -> new ResourceNotFoundException(ruleName.getId(), "RuleName"));
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Delete a RuleName.
     *
     * @param id The id of the RuleName
     * A ResourceNotFoundException is thrown if the RuleName to delete does not exist
     */
    @Override
    public void deleteRuleNameById(Integer id) throws ResourceNotFoundException {
        ruleNameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "RuleName"));
        ruleNameRepository.deleteById(id);
    }
}
