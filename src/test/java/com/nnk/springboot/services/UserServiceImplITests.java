package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class including integration (with the repository layer) tests for the
 * User entity Service.
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/schema-test.sql")
public class UserServiceImplITests {

    @Autowired
    private UserServiceImpl userServiceImplUnderTest;

    @Test
    @Sql("/cleandb-test.sql")
    public void createUser() {
        // ARRANGE
        User userToCreate = new User("user", "%Password1", "User", "USER");
            
        // ACT
        User userCreated = userServiceImplUnderTest.createUser(userToCreate);

        // ASSERT
        assertNotNull(userCreated.getId());
        assertEquals(userToCreate.getUsername(), userCreated.getUsername());
        assertEquals(userToCreate.getPassword(), userCreated.getPassword());
        assertEquals(userToCreate.getFullname(), userCreated.getFullname());
        assertEquals(userToCreate.getRole(), userCreated.getRole());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void updateUser() {
        // ARRANGE
        User userToUpdate = new User("user", "%Password1", "User", "USER");

        userServiceImplUnderTest.createUser(userToUpdate);

        // ACT
        userToUpdate.setFullname("UserUpdated");
        User userUpdated = userServiceImplUnderTest.updateUser(userToUpdate);

        // ASSERT
        assertEquals(userToUpdate.getUsername(), userUpdated.getUsername());
        assertEquals(userToUpdate.getPassword(), userUpdated.getPassword());
        assertEquals(userToUpdate.getFullname(), userUpdated.getFullname());
        assertEquals(userToUpdate.getRole(), userUpdated.getRole());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void findUserById() {
        // ARRANGE
        User userToFind = new User("user", "%Password1", "User", "USER");
        userToFind = userServiceImplUnderTest.createUser(userToFind);

        // ACT
        User userFound = userServiceImplUnderTest.findUserById(userToFind.getId());

        // ASSERT
        assertNotNull(userFound);
        assertEquals(userToFind.getUsername(), userFound.getUsername());
        assertEquals(userToFind.getPassword(), userFound.getPassword());
        assertEquals(userToFind.getFullname(), userFound.getFullname());
        assertEquals(userToFind.getRole(), userFound.getRole());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void findUserByUsername() {
        // ARRANGE
        User userToFind = new User("user", "%Password1", "User", "USER");
        userToFind = userServiceImplUnderTest.createUser(userToFind);

        // ACT
        User userFound = userServiceImplUnderTest.findUserByUsername(userToFind.getUsername());

        // ASSERT
        assertNotNull(userFound);
        assertEquals(userToFind.getUsername(), userFound.getUsername());
        assertEquals(userToFind.getPassword(), userFound.getPassword());
        assertEquals(userToFind.getFullname(), userFound.getFullname());
        assertEquals(userToFind.getRole(), userFound.getRole());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void findAllUsers() {
        // ARRANGE
        User userToFind1 = new User("user1", "%Password1", "User1", "USER");
        userServiceImplUnderTest.createUser(userToFind1);

        User userToFind2 = new User("user2", "%Password2", "User2", "USER");
        userServiceImplUnderTest.createUser(userToFind2);

        User userToFind3 = new User("user3", "%Password3", "User3", "USER");
        userServiceImplUnderTest.createUser(userToFind3);

        // ACT
        List<User> listUsers = userServiceImplUnderTest.findAllUsers();

        // ASSERT
        assertEquals(3, listUsers.size());
    }

    @Test
    @Sql("/cleandb-test.sql")
    public void deleteUserById() {
        // ARRANGE
        User userToDelete = new User("user", "%Password1", "User", "USER");
        userToDelete = userServiceImplUnderTest.createUser(userToDelete);

        // ACT
        Integer id = userToDelete.getId();
        userServiceImplUnderTest.deleteUserById(id);

        // ASSERT
        assertThrows(ResourceNotFoundException.class, () -> {
            userServiceImplUnderTest.findUserById(id);
        });
    }
}
