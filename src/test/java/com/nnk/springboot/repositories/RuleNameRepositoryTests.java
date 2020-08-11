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

/**
 * Class including integration (with the database) tests for the
 * RuleName Repository.
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql"})
public class RuleNameRepositoryTests {

    @Autowired
    private RuleNameRepository ruleNameRepositoryUnderTest;

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
        RuleName ruleNameTest2 = new RuleName();
        ruleNameTest2.setName("Rule Name");
        ruleNameTest2.setDescription("Description");
        ruleNameTest2.setJson("Json");
        ruleNameTest2.setTemplate("Template");
        ruleNameTest2.setSqlStr("SQL ");
        ruleNameTest2.setSqlPart("SQL Part");
        ruleNameRepositoryUnderTest.save(ruleNameTest2);

        RuleName ruleNameTest3 = new RuleName();
        ruleNameTest3.setName("Rule Name");
        ruleNameTest3.setDescription("Description");
        ruleNameTest3.setJson("Json");
        ruleNameTest3.setTemplate("Template");
        ruleNameTest3.setSqlStr("SQL ");
        ruleNameTest3.setSqlPart("SQL Part");
        ruleNameRepositoryUnderTest.save(ruleNameTest3);

        List<RuleName> listResult = ruleNameRepositoryUnderTest.findAll();
        assertTrue(listResult.size() == 3);

        // Delete
        Integer id = ruleNameTest.getId();
        //ruleNameRepositoryUnderTest.delete(ruleNameTest);
        ruleNameRepositoryUnderTest.deleteById(id);
        Optional<RuleName> ruleList = ruleNameRepositoryUnderTest.findById(id);
        assertFalse(ruleList.isPresent());
    }
}
