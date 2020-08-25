package com.nnk.springboot.controllers;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Class including unit tests for the ErrorController Class (security is disabled and has dedicated tests).
 */
@WebMvcTest(value = ErrorController.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc(addFilters = false)
public class ErrorControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(ErrorControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    // @GetMapping(value = "/errorAccessDenied")
    @Test
    public void accessDenied() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/errorAccessDenied"))
                    .andExpect(model().attribute("errorMessage", "You are not authorized for the requested data."))
                    .andExpect(view().name("errorAccessDenied"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

}