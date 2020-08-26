package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class including integration (with the repository layer) tests for the
 * RuleName entity Service.
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/schema-test.sql")
public class RuleNameServiceImplITests {

    @Autowired
    private RuleNameServiceImpl ruleNameServiceImplUnderTest;

    @Test
    @Sql("/cleandb-test.sql")
    public void createRuleName() {
        // ARRANGE
        RuleName ruleNameToCreate = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        // ACT
        RuleName ruleNameCreated = ruleNameServiceImplUnderTest.createRuleName(ruleNameToCreate);

        // ASSERT
        assertNotNull(ruleNameCreated.getId());
        assertEquals(ruleNameToCreate.getName(), ruleNameCreated.getName());
        assertEquals(ruleNameToCreate.getDescription(), ruleNameCreated.getDescription());
        assertEquals(ruleNameToCreate.getJson(), ruleNameCreated.getJson());
        assertEquals(ruleNameToCreate.getTemplate(), ruleNameCreated.getTemplate());
        assertEquals(ruleNameToCreate.getSqlStr(), ruleNameCreated.getSqlStr());
        assertEquals(ruleNameToCreate.getSqlPart(), ruleNameCreated.getSqlPart());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void updateRuleName() {
        // ARRANGE
        RuleName ruleNameToUpdate = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameServiceImplUnderTest.createRuleName(ruleNameToUpdate);

        // ACT
        ruleNameToUpdate.setName("Rule Name Update");
        RuleName ruleNameUpdated = ruleNameServiceImplUnderTest.updateRuleName(ruleNameToUpdate);

        // ASSERT
        assertEquals(ruleNameToUpdate.getName(), ruleNameUpdated.getName());
        assertEquals(ruleNameToUpdate.getDescription(), ruleNameUpdated.getDescription());
        assertEquals(ruleNameToUpdate.getJson(), ruleNameUpdated.getJson());
        assertEquals(ruleNameToUpdate.getTemplate(), ruleNameUpdated.getTemplate());
        assertEquals(ruleNameToUpdate.getSqlStr(), ruleNameUpdated.getSqlStr());
        assertEquals(ruleNameToUpdate.getSqlPart(), ruleNameUpdated.getSqlPart());
    }

    @Test
    @Sql("/cleandb-test.sql")
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
    @Sql("/cleandb-test.sql")
    public void findAllRuleNames() {
        // ARRANGE
        RuleName ruleNameToFind1 = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameServiceImplUnderTest.createRuleName(ruleNameToFind1);

        RuleName ruleNameToFind2 = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameServiceImplUnderTest.createRuleName(ruleNameToFind2);

        RuleName ruleNameToFind3 = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameServiceImplUnderTest.createRuleName(ruleNameToFind3);

        // ACT
        List<RuleName> listRuleNames = ruleNameServiceImplUnderTest.findAllRuleNames();

        // ASSERT
        assertEquals(3, listRuleNames.size());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void deleteRuleNameById() {
        // ARRANGE
        RuleName ruleNameToDelete = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        ruleNameToDelete = ruleNameServiceImplUnderTest.createRuleName(ruleNameToDelete);

        // ACT
        Integer id = ruleNameToDelete.getId();
        ruleNameServiceImplUnderTest.deleteRuleNameById(id);

        // ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            ruleNameServiceImplUnderTest.findRuleNameById(id);
        });
    }
}
