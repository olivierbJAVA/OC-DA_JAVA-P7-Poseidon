package com.nnk.springboot.password;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class including unit tests for the password validation.
 */
@WebMvcTest(value = UserController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class PasswordValidationTests {

    private static final Logger logger = LoggerFactory.getLogger(PasswordValidationTests.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository mockUserRepository;

    @Test
    public void validate_whenNoErrorInPassword() {
        //ARRANGE
        User userTest = new User();
        userTest.setId(1);
        userTest.setUsername("user");
        userTest.setPassword("%Password1");
        userTest.setFullname("User");
        userTest.setRole("USER");

        doReturn(userTest).when(mockUserRepository).save(userTest);

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

        verify(mockUserRepository, times(1)).save(any(User.class));
    }

    // @PostMapping(value = "/user/validate"")
    @Test
    public void validate_whenPasswordLessThanEightCharacters() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/1")
                    .param("username", "")
                    .param("password", "%Passw1") // password contains only 7 characters
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(model().attributeHasFieldErrors("user", "password"))
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserRepository, never()).save(any(User.class));
    }

    @Test
    public void validate_whenDigitIsMissingInPassword() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/1")
                    .param("username", "")
                    .param("password", "%Password") // digit is missing in password
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(model().attributeHasFieldErrors("user", "password"))
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserRepository, never()).save(any(User.class));
    }

    @Test
    public void validate_whenSpecialCharacterIsMissingInPassword() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/1")
                    .param("username", "")
                    .param("password", "Password1") // special character is missing in password
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(model().attributeHasFieldErrors("user", "password"))
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserRepository, never()).save(any(User.class));
    }

    @Test
    public void validate_whenUpperCaseIsMissing() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/1")
                    .param("username", "")
                    .param("password", "%password1") // upper case letter is missing in password
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(model().attributeHasFieldErrors("user", "password"))
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserRepository, never()).save(any(User.class));
    }
}