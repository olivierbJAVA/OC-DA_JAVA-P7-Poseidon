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
        Optional<RuleName> ruleName = ruleNameRepository.findById(id);

        if(ruleName.isPresent()) {
            return ruleName.get();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    @Override
    public RuleName createRuleName(RuleName RuleName) {
        return ruleNameRepository.save(RuleName);
    }

    @Override
    public RuleName updateRuleName(RuleName RuleName) {
        return ruleNameRepository.save(RuleName);
    }

    @Override
    public void deleteRuleNameById(Integer id) throws ResourceNotFoundException {
        Optional<RuleName> ruleName = ruleNameRepository.findById(id);

        if(ruleName.isPresent()) {
            ruleNameRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }
}
