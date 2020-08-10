package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/data-test.sql"})
public class RuleNameTests {

    @Autowired
    private RuleNameRepository ruleNameRepositoryUnderTest;

    @Test
    public void ruleNameTests() {
        RuleName ruleNameTest = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        // Save
        ruleNameTest = ruleNameRepositoryUnderTest.save(ruleNameTest);
        assertNotNull(ruleNameTest.getId());
        assertTrue(ruleNameTest.getName().equals("Rule Name"));

        // Update
        ruleNameTest.setName("Rule Name Update");
        ruleNameTest = ruleNameRepositoryUnderTest.save(ruleNameTest);
        assertTrue(ruleNameTest.getName().equals("Rule Name Update"));

        // Find by id
        Optional<RuleName> ruleNameGet = ruleNameRepositoryUnderTest.findById(ruleNameTest.getId());
        assertTrue(ruleNameGet.isPresent());
        assertEquals(ruleNameTest.getName(), ruleNameGet.get().getName());
        assertEquals(ruleNameTest.getDescription(), ruleNameGet.get().getDescription());
        assertEquals(ruleNameTest.getJson(), ruleNameGet.get().getJson());
        assertEquals(ruleNameTest.getTemplate(), ruleNameGet.get().getTemplate());
        assertEquals(ruleNameTest.getSqlStr(), ruleNameGet.get().getSqlStr());
        assertEquals(ruleNameTest.getSqlPart(), ruleNameGet.get().getSqlPart());

        // Find all
        List<RuleName> listResult = ruleNameRepositoryUnderTest.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = ruleNameTest.getId();
        ruleNameRepositoryUnderTest.delete(ruleNameTest);
        Optional<RuleName> ruleList = ruleNameRepositoryUnderTest.findById(id);
        assertFalse(ruleList.isPresent());
    }
}
