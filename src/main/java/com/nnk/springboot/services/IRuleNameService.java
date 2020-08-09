package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.RecordNotFoundException;

import java.util.List;

public interface IRuleNameService {

    List<RuleName> findAllRuleNames();

    RuleName findRuleNameById(Integer id) throws RecordNotFoundException;

    RuleName createRuleName(RuleName ruleName);

    RuleName updateRuleName(RuleName ruleName);

    void deleteRuleName(Integer id) throws RecordNotFoundException;
}
