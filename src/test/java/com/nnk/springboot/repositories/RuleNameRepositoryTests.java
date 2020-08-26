package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

/**
 * Class including unit tests for the RuleName entity Repository.
 */
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql"})
public class RuleNameRepositoryTests {

    @Autowired
    private RuleNameRepository ruleNameRepositoryUnderTest;

    @Test
    public void saveRuleName() {
        // ARRANGE
        RuleName ruleNameToSave = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        // ACT
        RuleName ruleNameSaved = ruleNameRepositoryUnderTest.save(ruleNameToSave);

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
        ruleNameRepositoryUnderTest.save(ruleNameToUpdate);

        // ACT
        ruleNameToUpdate.setName("Rule Name Update");
        RuleName ruleNameUpdated = ruleNameRepositoryUnderTest.save(ruleNameToUpdate);

        // ASSERT
        assertEquals(ruleNameToUpdate.getName(), ruleNameUpdated.getName());
        assertEquals(ruleNameToUpdate.getDescription(), ruleNameUpdated.getDescription());
        assertEquals(ruleNameToUpdate.getJson(), ruleNameUpdated.getJson());
        assertEquals(ruleNameToUpdate.getTemplate(), ruleNameUpdated.getTemplate());
        assertEquals(ruleNameToUpdate.getSqlStr(), ruleNameUpdated.getSqlStr());
        assertEquals(ruleNameToUpdate.getSqlPart(), ruleNameUpdated.getSqlPart());
    }

    @Test
    public void findRuleNameById() {
        // ARRANGE
        RuleName ruleNameToFind = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameToFind = ruleNameRepositoryUnderTest.save(ruleNameToFind);

        // ACT
        Optional<RuleName> ruleNameFound = ruleNameRepositoryUnderTest.findById(ruleNameToFind.getId());

        // ASSERT
        assertTrue(ruleNameFound.isPresent());
        assertEquals(ruleNameToFind.getName(), ruleNameFound.get().getName());
        assertEquals(ruleNameToFind.getDescription(), ruleNameFound.get().getDescription());
        assertEquals(ruleNameToFind.getJson(), ruleNameFound.get().getJson());
        assertEquals(ruleNameToFind.getTemplate(), ruleNameFound.get().getTemplate());
        assertEquals(ruleNameToFind.getSqlStr(), ruleNameFound.get().getSqlStr());
        assertEquals(ruleNameToFind.getSqlPart(), ruleNameFound.get().getSqlPart());
    }

    @Test
    public void findAllRuleNames() {
        // ARRANGE
        RuleName ruleNameToFind = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameRepositoryUnderTest.save(ruleNameToFind);

        RuleName ruleNameToFind2 = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameRepositoryUnderTest.save(ruleNameToFind2);

        RuleName ruleNameToFind3 = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameRepositoryUnderTest.save(ruleNameToFind3);

        // ACT
        List<RuleName> listRuleNames = ruleNameRepositoryUnderTest.findAll();

        // ASSERT
        assertEquals(3, listRuleNames.size());
    }

    @Test
    public void deleteRuleNameById() {
        // ARRANGE
        RuleName ruleNameToDelete = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameToDelete = ruleNameRepositoryUnderTest.save(ruleNameToDelete);

        // ACT
        Integer id = ruleNameToDelete.getId();
        ruleNameRepositoryUnderTest.deleteById(id);

        // ASSERT
        Optional<RuleName> ruleNameDeleted = ruleNameRepositoryUnderTest.findById(id);
        assertFalse(ruleNameDeleted.isPresent());
    }
}
