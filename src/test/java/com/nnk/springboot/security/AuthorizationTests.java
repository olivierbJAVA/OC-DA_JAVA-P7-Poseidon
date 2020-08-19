package com.nnk.springboot.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationTests {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationTests.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles="ADMIN")
    public void getAuthorizedForAllPage_whenAdminConnected() {
        // ARRANGE, ACT & ASSERT
        try {
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

    @Test
    @WithMockUser(roles="USER")
    public void getAuthorizedForAllPage_whenUserConnected() {
        // ARRANGE, ACT & ASSERT
        try {
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

    @Test
    @WithMockUser(roles="ADMIN")
    public void getAdminAuthorizedOnlyPage_whenAdminConnected() {
        // ARRANGE, ACT & ASSERT
        try {
            mockMvc.perform(get("/admin/home"))
                   .andExpect(status().isFound());
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

    @Test
    @WithMockUser(roles="USER")
    public void getAdminAuthorizedOnlyPage_whenUserConnected() {
        // ARRANGE, ACT & ASSERT
        try {
            mockMvc.perform(get("/admin/home"))
                   .andExpect(status().isForbidden());
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

}
