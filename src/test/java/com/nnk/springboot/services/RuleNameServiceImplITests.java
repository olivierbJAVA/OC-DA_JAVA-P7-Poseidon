package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class including integration (with the database) tests for the
 * RuleName Service.
 */
@ActiveProfiles("test")
@SpringBootTest
@Sql({"/schema-test.sql"})
public class RuleNameServiceImplITests {

    @Autowired
    private RuleNameServiceImpl ruleNameServiceImplUnderTest;

    @Test
    public void createRuleName() {
        // ARRANGE
        RuleName ruleNameToSave = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        // ACT
        RuleName ruleNameSaved = ruleNameServiceImplUnderTest.createRuleName(ruleNameToSave);

        // ASSERT
        assertNotNull(ruleNameSaved.getId());
        assertEquals(ruleNameToSave.getName(), ruleNameSaved.getName());
        assertEquals(ruleNameToSave.getDescription(), ruleNameSaved.getDescription());
        assertEquals(ruleNameToSave.getJson(), ruleNameSaved.getJson());
        assertEquals(ruleNameToSave.getTemplate(), ruleNameSaved.getTemplate());
        assertEquals(ruleNameToSave.getSqlStr(), ruleNameSaved.getSqlStr());
        assertEquals(ruleNameToSave.getSqlPart(), ruleNameSaved.getSqlPart());
    }

    @Test
    public void updateRuleName() {
        // ARRANGE
        RuleName ruleNameToUpdate = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameServiceImplUnderTest.createRuleName(ruleNameToUpdate);

        // ACT
        ruleNameToUpdate.setName("Rule Name Update");
        RuleName ruleNameToUpdated = ruleNameServiceImplUnderTest.updateRuleName(ruleNameToUpdate);

        // ASSERT
        assertEquals(ruleNameToUpdate.getName(), ruleNameToUpdated.getName());
        assertEquals(ruleNameToUpdate.getDescription(), ruleNameToUpdated.getDescription());
        assertEquals(ruleNameToUpdate.getJson(), ruleNameToUpdated.getJson());
        assertEquals(ruleNameToUpdate.getTemplate(), ruleNameToUpdated.getTemplate());
        assertEquals(ruleNameToUpdate.getSqlStr(), ruleNameToUpdated.getSqlStr());
        assertEquals(ruleNameToUpdate.getSqlPart(), ruleNameToUpdated.getSqlPart());
    }

    @Test
    public void findRuleNameById() {
        // ARRANGE
        RuleName ruleNameToFind = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameToFind = ruleNameServiceImplUnderTest.createRuleName(ruleNameToFind);

        // ACT
        RuleName ruleNameFound = ruleNameServiceImplUnderTest.findRuleNameById(ruleNameToFind.getId());

        // ASSERT
        assertNotNull(ruleNameFound);
        assertEquals(ruleNameToFind.getName(), ruleNameFound.getName());
        assertEquals(ruleNameToFind.getDescription(), ruleNameFound.getDescription());
        assertEquals(ruleNameToFind.getJson(), ruleNameFound.getJson());
        assertEquals(ruleNameToFind.getTemplate(), ruleNameFound.getTemplate());
        assertEquals(ruleNameToFind.getSqlStr(), ruleNameFound.getSqlStr());
        assertEquals(ruleNameToFind.getSqlPart(), ruleNameFound.getSqlPart());
    }

    @Test
    public void findAllRuleNames() {
        // ARRANGE
        RuleName ruleNameToFind = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameServiceImplUnderTest.createRuleName(ruleNameToFind);

        RuleName ruleNameToFind2 = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameServiceImplUnderTest.createRuleName(ruleNameToFind2);

        RuleName ruleNameToFind3 = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameServiceImplUnderTest.createRuleName(ruleNameToFind3);

        // ACT
        List<RuleName> listRuleNames = ruleNameServiceImplUnderTest.findAllRuleNames();

        // ASSERT
        assertTrue(listRuleNames.size() == 3);
    }

    @Test
    public void deleteRuleNameById() {
        // ARRANGE
        RuleName ruleNameToDelete = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameToDelete = ruleNameServiceImplUnderTest.createRuleName(ruleNameToDelete);

        // ACT
        Integer id = ruleNameToDelete.getId();
        ruleNameServiceImplUnderTest.deleteRuleNameById(id);

        // ASSERT
        assertThrows(RecordNotFoundException.class, () -> {
            ruleNameServiceImplUnderTest.findRuleNameById(id);
        });
    }
}