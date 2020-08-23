package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class including unit tests for the UserController Class (security is disabled and has dedicated tests).
 */
@WebMvcTest(value = UserController.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTests {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerTests.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService mockUserService;

    // @RequestMapping(value = "/user/list")
    @Test
    public void home() {
        //ARRANGE
        User userTest1 = new User();
        userTest1.setId(1);
        userTest1.setUsername("user");
        userTest1.setPassword("%Password1");
        userTest1.setFullname("User");
        userTest1.setRole("USER");

        User userTest2 = new User();
        userTest2.setId(2);
        userTest2.setUsername("user");
        userTest2.setPassword("%Password2");
        userTest2.setFullname("User");
        userTest2.setRole("USER");

        User userTest3 = new User();
        userTest3.setId(3);
        userTest3.setUsername("user");
        userTest3.setPassword("%Password3");
        userTest3.setFullname("User");
        userTest3.setRole("USER");

        List<User> allUsersToFind = new ArrayList<>();
        allUsersToFind.add(userTest1);
        allUsersToFind.add(userTest2);
        allUsersToFind.add(userTest3);

        doReturn(allUsersToFind).when(mockUserService).findAllUsers();

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/user/list"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("users", allUsersToFind))
                    .andExpect(view().name("user/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, times(1)).findAllUsers();
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
    public void validate_whenNoErrorAndUsernameNotAlreadyExist() {
        //ARRANGE
        doReturn(null).when(mockUserService).findUserByUsername("user");

        User userTest = new User();
        userTest.setId(1);
        userTest.setUsername("user");
        userTest.setPassword("%Password1");
        userTest.setFullname("User");
        userTest.setRole("USER");

        doReturn(userTest).when(mockUserService).createUser(userTest);

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/validate")
                    .param("username", "user")
                    .param("password", "%Password1")
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
    public void validate_whenNoErrorAndUsernameAlreadyExist() {
        //ARRANGE
        User usernameAlreadyExist = new User();
        usernameAlreadyExist.setId(1);
        usernameAlreadyExist.setUsername("user");
        usernameAlreadyExist.setPassword("%Password1");
        usernameAlreadyExist.setFullname("User");
        usernameAlreadyExist.setRole("USER");

        doReturn(usernameAlreadyExist).when(mockUserService).findUserByUsername("user");

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/validate")
                    .param("username", "user")
                    .param("password", "%Password2")
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(status().isBadRequest())
                    .andExpect(view().name("errorResourceAlreadyExist"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, times(1)).findUserByUsername("user");
        verify(mockUserService, never()).createUser(any(User.class));
    }

    // @PostMapping(value = "/user/validate"")
    @Test
    public void validate_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            // Error in username (mandatory field)
            mockMvc.perform(post("/user/update/1")
                    .param("username", "")
                    .param("password", "%Password1")
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(model().attributeHasFieldErrors("user", "username"))
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, never()).createUser(any(User.class));
    }

    // @GetMapping(value = "/user/update/{id}"")
    @Test
    public void showUpdateForm() {
        //ARRANGE
        User userTest = new User();
        userTest.setId(1);
        userTest.setUsername("user");
        userTest.setPassword("");
        userTest.setFullname("User");
        userTest.setRole("USER");

        doReturn(userTest).when(mockUserService).findUserById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/user/update/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("user", userTest))
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, times(1)).findUserById(1);
    }

    // @PostMapping(value = "/user/update/{id}"")
    @Test
    public void updateUser_whenNoErrorAndUsernameNotAlreadyExist() {
        //ARRANGE
        doReturn(null).when(mockUserService).findUserByUsername("user");

        User userTest = new User();
        userTest.setId(1);
        userTest.setUsername("user");
        userTest.setPassword("%Password1");
        userTest.setFullname("User");
        userTest.setRole("USER");

        doReturn(userTest).when(mockUserService).updateUser(userTest);

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/1")
                    .param("id", "1")
                    .param("username", "user")
                    .param("password", "%Password1")
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/user/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, times(1)).updateUser(any(User.class));
    }

    // @PostMapping(value = "/user/update/{id}"")
    @Test
    public void updateUser_whenNoErrorAndUsernameAlreadyExistAndIsUsernameToUpdate() {
        //ARRANGE
        User usernameAlreadyExist = new User();
        // the username already existing is the one of the user to update so no issue
        usernameAlreadyExist.setId(1);
        usernameAlreadyExist.setUsername("user");
        usernameAlreadyExist.setPassword("%Password1");
        usernameAlreadyExist.setFullname("User");
        usernameAlreadyExist.setRole("USER");

        doReturn(usernameAlreadyExist).when(mockUserService).findUserByUsername("user");

        User userTest = new User();
        userTest.setId(1);
        userTest.setUsername("user");
        userTest.setPassword("%Password2");
        userTest.setFullname("User");
        userTest.setRole("USER");

        doReturn(userTest).when(mockUserService).updateUser(userTest);

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/1")
                    .param("id", "1")
                    .param("username", "user")
                    .param("password", "%Password2")
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/user/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, times(1)).findUserByUsername("user");
        verify(mockUserService, times(1)).updateUser(any(User.class));
    }

    // @PostMapping(value = "/user/update/{id}"")
    @Test
    public void updateUser_whenNoErrorAndUsernameAlreadyExistAndIsNotUsernameToUpdate() {
        //ARRANGE
        User usernameAlreadyExist = new User();
        // the username already existing belongs to another user than the one to update so there is an issue
        usernameAlreadyExist.setId(1);
        usernameAlreadyExist.setUsername("user");
        usernameAlreadyExist.setPassword("%Password1");
        usernameAlreadyExist.setFullname("User");
        usernameAlreadyExist.setRole("USER");

        doReturn(usernameAlreadyExist).when(mockUserService).findUserByUsername("user");

        User userTest = new User();
        userTest.setId(2);
        userTest.setUsername("user");
        userTest.setPassword("%Password2");
        userTest.setFullname("User");
        userTest.setRole("USER");

        doReturn(userTest).when(mockUserService).updateUser(userTest);

        //ACT & ASSERT
        try {
            mockMvc.perform(post("/user/update/2")
                    .param("id", "2")
                    .param("username", "user")
                    .param("password", "%Password2")
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(status().isBadRequest())
                    .andExpect(view().name("errorResourceAlreadyExist"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, times(1)).findUserByUsername("user");
        verify(mockUserService, never()).updateUser(any(User.class));
    }

    // @PostMapping(value = "/user/update/{id}"")
    @Test
    public void updateUser_whenError() {
        //ARRANGE

        //ACT & ASSERT
        try {
            // Error in username (mandatory field)
            mockMvc.perform(post("/user/update/1")
                    .param("id", "1")
                    .param("username", "")
                    .param("password", "%Password1")
                    .param("fullname", "User")
                    .param("role", "USER"))
                    .andExpect(model().attributeHasFieldErrors("user", "username"))
                    .andExpect(view().name("user/update"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, never()).updateUser(any(User.class));
    }

    // @GetMapping(value = "/user/delete/{id}"")
    @Test
    public void deleteUser_whenUserExist() {
        //ARRANGE
        User userTest = new User();
        userTest.setId(1);
        userTest.setUsername("user");
        userTest.setPassword("%Password1");
        userTest.setFullname("User");
        userTest.setRole("USER");

        doReturn(userTest).when(mockUserService).findUserById(1);

        doNothing().when(mockUserService).deleteUserById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/user/delete/1"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/user/list"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, times(1)).deleteUserById(1);
    }

    // @GetMapping(value = "/user/delete/{id}"")
    @Test
    public void deleteUser_whenUserNotExist() {
        //ARRANGE
        doThrow(ResourceNotFoundException.class).when(mockUserService).deleteUserById(1);

        //ACT & ASSERT
        try {
            mockMvc.perform(get("/user/delete/1"))
                    .andExpect(status().isNotFound())
                    .andExpect(view().name("errorResourceNotFound"));
        } catch (Exception e) {
            logger.error("Error in MockMvc", e);
        }

        verify(mockUserService, times(1)).deleteUserById(1);
    }
}