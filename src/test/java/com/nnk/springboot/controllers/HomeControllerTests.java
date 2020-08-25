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

/**
 * Class including unit tests for the HomeController Class (security is disabled and has dedicated tests).
 */
@WebMvcTest(value = HomeController.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc(addFilters = false)
public class HomeControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(HomeControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    // @RequestMapping(value = "/")
    @Test
    public void home() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/bidList/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

    // @RequestMapping(value = "/admin/home")
    @Test
    public void adminHome() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/admin/home"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/user/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

}