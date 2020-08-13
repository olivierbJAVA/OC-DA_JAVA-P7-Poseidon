package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
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
 * Class including unit tests for the UserController Class.
 */
@WebMvcTest(value = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ActiveProfiles("test")
public class UserControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository mockUserRepository;

    @BeforeEach
    private void setUpPerTest() {
    }

    // @RequestMapping(value = "/user/list")
    @Test
    public void home() {
        //ARRANGE
        User userTest1 = new User();
        userTest1.setUsername("user");
        userTest1.setPassword("password");
        userTest1.setFullname("User");
        userTest1.setRole("USER");

        User userTest2 = new User();
        userTest2.setUsername("user");
        userTest2.setPassword("password");
        userTest2.setFullname("User");
        userTest2.setRole("USER");

        User userTest3 = new User();
        userTest3.setUsername("user");
        userTest3.setPassword("password");
        userTest3.setFullname("User");
        userTest3.setRole("USER");

        List<User> allUsersToFind = new ArrayList<>();
        allUsersToFind.add(userTest1);
        allUsersToFind.add(userTest2);
        allUsersToFind.add(userTest3);

        doReturn(allUsersToFind).when(mockUserRepository).findAll();

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/user/list"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("users", allUsersToFind))
                    .andExpect(view().name("user/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserRepository, times(1)).findAll();
    }

    // @GetMapping(value = "/user/add"")
    @Test
    public void addUserForm() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/user/add"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("user/add"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }
    }

    // @PostMapping(value = "/user/validate"")
    @Test
    public void validate_whenNoError() {
        //ARRANGE
        User userTest = new User();
        userTest.setUsername("user");
        userTest.setPassword("password");
        userTest.setFullname("User");
        userTest.setRole("USER");

        doReturn(userTest).when(mockUserRepository).save(userTest);

        List<User> listUsers = new ArrayList<>();
        listUsers.add(userTest);

        doReturn(listUsers).when(mockUserRepository).findAll();

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/validate")
                    .param("username", "user")
                    .param("password", "password")
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/user/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserRepository, times(1)).save(any(User.class));
        verify(mockUserRepository, times(1)).findAll();
    }

    // @PostMapping(value = "/user/validate"")
    @Test
    public void validate_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/1")
                    .param("username", "")
                    .param("password", "password")
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserRepository, never()).save(any(User.class));
        verify(mockUserRepository, never()).findAll();
    }

    // @GetMapping(value = "/user/update/{id}"")
    @Test
    public void showUpdateForm() {
        //ARRANGE
        User userTest = new User();
        userTest.setUsername("user");
        userTest.setPassword("password");
        userTest.setFullname("User");
        userTest.setRole("USER");

        Optional<User> optUserTest = Optional.of(userTest);
        doReturn(optUserTest).when(mockUserRepository).findById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/user/update/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("user", userTest))
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserRepository, times(1)).findById(1);
    }

    // @PostMapping(value = "/user/update/{id}"")
    @Test
    public void updateUser_whenNoError() {
        //ARRANGE
        User userTest = new User();
        userTest.setUsername("user");
        userTest.setPassword("password");
        userTest.setFullname("User");
        userTest.setRole("USER");

        doReturn(userTest).when(mockUserRepository).save(userTest);

        List<User> listUsers = new ArrayList<>();
        listUsers.add(userTest);
        doReturn(listUsers).when(mockUserRepository).findAll();

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/1")
                    .param("username", "user")
                    .param("password", "password")
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/user/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserRepository, times(1)).save(any(User.class));
        verify(mockUserRepository, times(1)).findAll();
    }

    // @PostMapping(value = "/user/update/{id}"")
    @Test
    public void updateUser_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/1")
                    .param("username", "")
                    .param("password", "password")
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserRepository, never()).save(any(User.class));
        verify(mockUserRepository, never()).findAll();
    }

    // @GetMapping(value = "/user/delete/{id}"")
    @Test
    public void deleteUser_whenUserExist() {
        //ARRANGE
        User userTest1 = new User();
        userTest1.setUsername("user");
        userTest1.setPassword("password");
        userTest1.setFullname("User");
        userTest1.setRole("USER");

        User userTest2 = new User();
        userTest2.setUsername("user");
        userTest2.setPassword("password");
        userTest2.setFullname("User");
        userTest2.setRole("USER");

        User userTest3 = new User();
        userTest3.setUsername("user");
        userTest3.setPassword("password");
        userTest3.setFullname("User");
        userTest3.setRole("USER");

        List<User> allUsersToFind = new ArrayList<>();
        allUsersToFind.add(userTest1);
        allUsersToFind.add(userTest2);
        allUsersToFind.add(userTest3);

        doReturn(allUsersToFind).when(mockUserRepository).findAll();

        Optional<User> optUserTest = Optional.of(userTest1);
        doReturn(optUserTest).when(mockUserRepository).findById(1);

        doNothing().when(mockUserRepository).delete(userTest1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/user/delete/1"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/user/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserRepository, times(1)).delete(optUserTest.get());
        verify(mockUserRepository, times(1)).findAll();
    }

    // @GetMapping(value = "/user/delete/{id}"")
    @Test
    public void deleteUser_whenUserNotExist() {
        //ARRANGE
        doThrow(IllegalArgumentException.class).when(mockUserRepository).findById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/user/delete/1"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("errorRecordNotFound"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserRepository, never()).deleteById(1);
        verify(mockUserRepository, never()).findAll();
    }

}