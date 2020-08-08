package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;

import java.util.List;

public interface IRuleNameService {

    List<RuleName> getAllRuleNames();

    RuleName getRuleNameById(Integer id);

    RuleName saveOrUpdate(RuleName ruleName);

    void delete (Integer id);

}
