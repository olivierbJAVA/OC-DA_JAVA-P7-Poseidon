package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RuleNameImplService implements IRuleNameService {

    @Autowired
    RuleNameRepository ruleNameRepository;

    public List<RuleName> getAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName getRuleNameById(Integer id) {
        return ruleNameRepository.getOne(id);
    }

    @Override
    public RuleName saveOrUpdate(RuleName RuleName) {
        return ruleNameRepository.save(RuleName);
    }

    @Override
    public void delete (Integer id) {
        ruleNameRepository.deleteById(id);
    }
}
