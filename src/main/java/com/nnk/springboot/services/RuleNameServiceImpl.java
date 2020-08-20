package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RuleNameServiceImpl implements IRuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    public List<RuleName> findAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName findRuleNameById(Integer id) throws ResourceNotFoundException {
        return ruleNameRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id, "RuleName"));
    }

    @Override
    public RuleName createRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    @Override
    public RuleName updateRuleName(RuleName ruleName) {
        ruleNameRepository.findById(ruleName.getId()).orElseThrow(()-> new ResourceNotFoundException(ruleName.getId(), "RuleName"));
        return ruleNameRepository.save(ruleName);
    }

    @Override
    public void deleteRuleNameById(Integer id) throws ResourceNotFoundException {
        ruleNameRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id, "RuleName"));
        ruleNameRepository.deleteById(id);
    }
}
