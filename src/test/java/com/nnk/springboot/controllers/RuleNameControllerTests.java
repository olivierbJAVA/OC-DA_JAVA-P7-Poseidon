package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.services.IRuleNameService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class including unit tests for the RuleNameController Class.
 */
@WebMvcTest(value = RuleNameController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class RuleNameControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(RuleNameControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IRuleNameService mockRuleNameService;

    // @RequestMapping(value = "/ruleName/list")
    @Test
    public void home() {
        //ARRANGE
        RuleName ruleNameTest1 = new RuleName();
        ruleNameTest1.setId(1);
        ruleNameTest1.setName("Rule Name");
        ruleNameTest1.setDescription("Description");
        ruleNameTest1.setJson("Json");
        ruleNameTest1.setTemplate("Template");
        ruleNameTest1.setSqlStr("SQL ");
        ruleNameTest1.setSqlPart("SQL Part");

        RuleName ruleNameTest2 = new RuleName();
        ruleNameTest1.setId(2);
        ruleNameTest2.setName("Rule Name");
        ruleNameTest2.setDescription("Description");
        ruleNameTest2.setJson("Json");
        ruleNameTest2.setTemplate("Template");
        ruleNameTest2.setSqlStr("SQL ");
        ruleNameTest2.setSqlPart("SQL Part");

        RuleName ruleNameTest3 = new RuleName();
        ruleNameTest1.setId(3);
        ruleNameTest3.setName("Rule Name");
        ruleNameTest3.setDescription("Description");
        ruleNameTest3.setJson("Json");
        ruleNameTest3.setTemplate("Template");
        ruleNameTest3.setSqlStr("SQL ");
        ruleNameTest3.setSqlPart("SQL Part");

        List<RuleName> allRuleNamesToFind = new ArrayList<>();
        allRuleNamesToFind.add(ruleNameTest1);
        allRuleNamesToFind.add(ruleNameTest1);
        allRuleNamesToFind.add(ruleNameTest1);

        doReturn(allRuleNamesToFind).when(mockRuleNameService).findAllRuleNames();

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/ruleName/list"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("ruleNames", allRuleNamesToFind))
                    .andExpect(view().name("ruleName/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRuleNameService, times(1)).findAllRuleNames();
    }

    // @GetMapping(value = "/ruleName/add"")
    @Test
    public void addRuleNameForm() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/ruleName/add"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("ruleName/add"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

    // @PostMapping(value = "/ruleName/validate"")
    @Test
    public void validate_whenNoError() {
        //ARRANGE
        RuleName ruleNameTest = new RuleName();
        ruleNameTest.setName("Rule Name");
        ruleNameTest.setDescription("Description");
        ruleNameTest.setJson("Json");
        ruleNameTest.setTemplate("Template");
        ruleNameTest.setSqlStr("SQL ");
        ruleNameTest.setSqlPart("SQL Part");

        doReturn(ruleNameTest).when(mockRuleNameService).createRuleName(ruleNameTest);

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/ruleName/validate")
                    .param("name", "Rule Name")
                    .param("description", "Description")
                    .param("json", "Json")
                    .param("template", "Template")
                    .param("sqlStr", "SQL ")
                    .param("sqlPart", "SQL Part"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/ruleName/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRuleNameService, times(1)).createRuleName(any(RuleName.class));
    }

    // @PostMapping(value = "/ruleName/validate"")
    @Test
    public void validate_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            // Error in name (mandatory field)
            mockMvc.perform(post("/ruleName/update/1")
                    .param("name", "")
                    .param("description", "Description")
                    .param("json", "Json")
                    .param("template", "Template")
                    .param("sqlStr", "SQL ")
                    .param("sqlPart", "SQL Part"))
                    .andExpect(model().attributeHasFieldErrors("ruleName", "name"))
                    .andExpect(view().name("ruleName/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRuleNameService, never()).createRuleName(any(RuleName.class));
    }

    // @GetMapping(value = "/ruleName/update/{id}"")
    @Test
    public void showUpdateForm() {
        //ARRANGE
        RuleName ruleNameTest = new RuleName();
        ruleNameTest.setId(1);
        ruleNameTest.setName("Rule Name");
        ruleNameTest.setDescription("Description");
        ruleNameTest.setJson("Json");
        ruleNameTest.setTemplate("Template");
        ruleNameTest.setSqlStr("SQL ");
        ruleNameTest.setSqlPart("SQL Part");

        doReturn(ruleNameTest).when(mockRuleNameService).findRuleNameById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/ruleName/update/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("ruleName", ruleNameTest))
                    .andExpect(view().name("ruleName/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRuleNameService, times(1)).findRuleNameById(1);
    }

    // @PostMapping(value = "/ruleName/update/{id}"")
    @Test
    public void updateRuleName_whenNoError() {
        //ARRANGE
        RuleName ruleNameTest = new RuleName();
        ruleNameTest.setId(1);
        ruleNameTest.setName("Rule Name");
        ruleNameTest.setDescription("Description");
        ruleNameTest.setJson("Json");
        ruleNameTest.setTemplate("Template");
        ruleNameTest.setSqlStr("SQL ");
        ruleNameTest.setSqlPart("SQL Part");

        doReturn(ruleNameTest).when(mockRuleNameService).findRuleNameById(1);
        doReturn(ruleNameTest).when(mockRuleNameService).updateRuleName(ruleNameTest);

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/ruleName/update/1")
                    .param("id", "1")
                    .param("name", "Rule Name")
                    .param("description", "Description")
                    .param("json", "Json")
                    .param("template", "Template")
                    .param("sqlStr", "SQL ")
                    .param("sqlPart", "SQL Part"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/ruleName/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRuleNameService, times(1)).updateRuleName(any(RuleName.class));
    }

    // @PostMapping(value = "/ruleName/update/{id}"")
    @Test
    public void updateRuleName_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            // Error in name (mandatory field)
            mockMvc.perform(post("/ruleName/update/1")
                    .param("id", "1")
                    .param("name", "")
                    .param("description", "Description")
                    .param("json", "Json")
                    .param("template", "Template")
                    .param("sqlStr", "SQL ")
                    .param("sqlPart", "SQL Part"))
                    .andExpect(model().attributeHasFieldErrors("ruleName", "name"))
                    .andExpect(view().name("ruleName/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRuleNameService, never()).updateRuleName(any(RuleName.class));
    }

    // @GetMapping(value = "/ruleName/delete/{id}"")
    @Test
    public void deleteRuleName_whenRuleNameExist() {
        //ARRANGE
        doNothing().when(mockRuleNameService).deleteRuleNameById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/ruleName/delete/1"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/ruleName/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRuleNameService, times(1)).deleteRuleNameById(1);
    }

    // @GetMapping(value = "/ruleName/delete/{id}"")
    @Test
    public void deleteRuleName_whenRuleNameNotExist() {
        //ARRANGE
        doThrow(ResourceNotFoundException.class).when(mockRuleNameService).deleteRuleNameById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/ruleName/delete/1"))
                    .andExpect(status().isNotFound())
                    .andExpect(view().name("errorResourceNotFound"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockRuleNameService, times(1)).deleteRuleNameById(1);
    }
}