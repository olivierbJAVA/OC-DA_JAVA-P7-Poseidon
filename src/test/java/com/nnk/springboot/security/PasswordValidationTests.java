package com.nnk.springboot.security;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.IUserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class including unit tests for password validation.
 */
@WebMvcTest(value = UserController.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc(addFilters = false)
public class PasswordValidationTests {

    private static final Logger logger = LoggerFactory.getLogger(PasswordValidationTests.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService mockUserService;

    @Test
    public void validate_whenNoErrorInPassword() {
        //ARRANGE
        User userTest = new User();
        userTest.setId(1);
        userTest.setUsername("user");
        userTest.setPassword("%Password1");
        userTest.setFullname("User");
        userTest.setRole("USER");

        doReturn(null).when(mockUserService).findUserByUsername("user");
        doReturn(userTest).when(mockUserService).createUser(userTest);

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/validate")
                    .param("username", "user")
                    .param("password", "%Password1") // Password contains more than 8 characters, a digit occurs at least once, an upper case letter occurs at least once, a special character at least once and there is no whitespace
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/user/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, times(1)).createUser(any(User.class));
        verify(mockUserService, times(1)).findUserByUsername("user");
    }

    // @PostMapping(value = "/user/validate"")
    @Test
    public void validate_whenPasswordLessThanEightCharacters() {
        //ARRANGE
        doReturn(null).when(mockUserService).findUserByUsername("user");

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/1")
                    .param("username", "user")
                    .param("password", "%Passw1") // password contains only 7 characters
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(model().attributeHasFieldErrors("user", "password"))
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, never()).createUser(any(User.class));
        verify(mockUserService, times(1)).findUserByUsername("user");
    }

    @Test
    public void validate_whenDigitIsMissingInPassword() {
        //ARRANGE
        doReturn(null).when(mockUserService).findUserByUsername("user");

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/1")
                    .param("username", "user")
                    .param("password", "%Password") // digit is missing in password
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(model().attributeHasFieldErrors("user", "password"))
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, never()).createUser(any(User.class));
        verify(mockUserService, times(1)).findUserByUsername("user");
    }

    @Test
    public void validate_whenSpecialCharacterIsMissingInPassword() {
        //ARRANGE
        doReturn(null).when(mockUserService).findUserByUsername("user");

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/1")
                    .param("username", "user")
                    .param("password", "Password1") // special character is missing in password
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(model().attributeHasFieldErrors("user", "password"))
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, never()).createUser(any(User.class));
        verify(mockUserService, times(1)).findUserByUsername("user");
    }

    @Test
    public void validate_whenUpperCaseIsMissing() {
        //ARRANGE
        doReturn(null).when(mockUserService).findUserByUsername("user");

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/1")
                    .param("username", "user")
                    .param("password", "%password1") // upper case letter is missing in password
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(model().attributeHasFieldErrors("user", "password"))
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, never()).createUser(any(User.class));
        verify(mockUserService, times(1)).findUserByUsername("user");
    }
}