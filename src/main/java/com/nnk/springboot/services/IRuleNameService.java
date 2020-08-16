package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IRuleNameService {

    List<RuleName> findAllRuleNames();

    RuleName findRuleNameById(Integer id) throws ResourceNotFoundException;

    RuleName createRuleName(RuleName ruleName);

    RuleName updateRuleName(RuleName ruleName) throws ResourceNotFoundException;

    void deleteRuleNameById(Integer id) throws ResourceNotFoundException;
}
