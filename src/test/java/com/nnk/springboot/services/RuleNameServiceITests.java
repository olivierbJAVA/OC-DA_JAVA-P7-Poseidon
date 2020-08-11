package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class including integration (with the database) tests for the
 * RuleName Service.
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@Sql({"/schema-test.sql"})
public class RuleNameServiceITests {

    @Autowired
    private RuleNameImplService ruleNameImplServiceUnderTest;

    @Test
    public void ruleNameTests() {
        //RuleName ruleNameTest = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        RuleName ruleNameTest = new RuleName();
        ruleNameTest.setName("Rule Name");
        ruleNameTest.setDescription("Description");
        ruleNameTest.setJson("Json");
        ruleNameTest.setTemplate("Template");
        ruleNameTest.setSqlStr("SQL ");
        ruleNameTest.setSqlPart("SQL Part");

        // Save
        ruleNameTest = ruleNameImplServiceUnderTest.createRuleName(ruleNameTest);
        assertNotNull(ruleNameTest.getId());
        assertTrue(ruleNameTest.getName().equals("Rule Name"));

        // Update
        ruleNameTest.setName("Rule Name Update");
        ruleNameTest = ruleNameImplServiceUnderTest.updateRuleName(ruleNameTest);
        assertTrue(ruleNameTest.getName().equals("Rule Name Update"));

        // Find by id
        RuleName ruleNameGet = ruleNameImplServiceUnderTest.findRuleNameById(ruleNameTest.getId());
        assertNotNull(ruleNameGet);
        assertEquals(ruleNameTest.getName(), ruleNameGet.getName());
        assertEquals(ruleNameTest.getDescription(), ruleNameGet.getDescription());
        assertEquals(ruleNameTest.getJson(), ruleNameGet.getJson());
        assertEquals(ruleNameTest.getTemplate(), ruleNameGet.getTemplate());
        assertEquals(ruleNameTest.getSqlStr(), ruleNameGet.getSqlStr());
        assertEquals(ruleNameTest.getSqlPart(), ruleNameGet.getSqlPart());

        // Find all
        RuleName ruleNameTest2 = new RuleName();
        ruleNameTest2.setName("Rule Name");
        ruleNameTest2.setDescription("Description");
        ruleNameTest2.setJson("Json");
        ruleNameTest2.setTemplate("Template");
        ruleNameTest2.setSqlStr("SQL ");
        ruleNameTest2.setSqlPart("SQL Part");
        ruleNameImplServiceUnderTest.createRuleName(ruleNameTest2);

        RuleName ruleNameTest3 = new RuleName();
        ruleNameTest3.setName("Rule Name");
        ruleNameTest3.setDescription("Description");
        ruleNameTest3.setJson("Json");
        ruleNameTest3.setTemplate("Template");
        ruleNameTest3.setSqlStr("SQL ");
        ruleNameTest3.setSqlPart("SQL Part");
        ruleNameImplServiceUnderTest.createRuleName(ruleNameTest3);

        List<RuleName> listResult = ruleNameImplServiceUnderTest.findAllRuleNames();
        assertTrue(listResult.size() == 3);

        // Delete
        Integer id = ruleNameTest.getId();
        ruleNameImplServiceUnderTest.deleteRuleNameById(id);
        assertThrows(RecordNotFoundException.class, () -> {
            ruleNameImplServiceUnderTest.findRuleNameById(id);
        });
    }
}
