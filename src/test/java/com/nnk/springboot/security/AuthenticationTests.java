package com.nnk.springboot.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/schema-test.sql", "/data-test.sql"})
public class AuthenticationTests {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationTests.class);

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
    public void loginPageAccess() {
        // ARRANGE, ACT & ASSERT
        try {
            mockMvc.perform(get("/login"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

    @Test
    public void userLogin_whenPasswordIsCorrectAndWhenRoleUser() {
        // ARRANGE
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");

        // ACT & ASSERT
        try {
            mockMvc.perform(formLogin("/login").user("user").password("%Password1User"))
                   .andExpect(authenticated().withAuthorities(Arrays.asList(grantedAuthority)));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

    @Test
    public void userLogin_whenPasswordIsCorrectAndWhenRoleAdmin() {
        // ARRANGE
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ADMIN");

        // ARRANGE, ACT & ASSERT
        try {
            mockMvc.perform(formLogin("/login").user("admin").password("%Password1Admin"))
                    .andExpect(authenticated().withAuthorities(Arrays.asList(grantedAuthority)));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

    @Test
    public void userLogin_whenPasswordIsWrong() {
        // ARRANGE, ACT & ASSERT
        try {
            mockMvc.perform(formLogin("/login").user("user").password("WrongPassword"))
                    .andExpect(unauthenticated());
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

}
