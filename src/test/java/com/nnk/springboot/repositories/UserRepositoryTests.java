package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class including integration (with the database) tests for the
 * User Repository.
 */
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-test.sql"})
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepositoryUnderTest;

    @Test
    public void saveUser() {
        // ARRANGE
        User userToSave = new User("user", "password", "User", "USER");

        // ACT
        User userSaved = userRepositoryUnderTest.save(userToSave);

        // ASSERT
        assertNotNull(userSaved.getId());
        assertEquals(userToSave.getUsername(), userSaved.getUsername());
        assertEquals(userToSave.getPassword(), userSaved.getPassword());
        assertEquals(userToSave.getFullname(), userSaved.getFullname());
        assertEquals(userToSave.getRole(), userSaved.getRole());
    }

    @Test
    public void updateUser() {
        // ARRANGE
        User userToUpdate = new User("user", "password", "User", "USER");
        userRepositoryUnderTest.save(userToUpdate);

        // ACT
        userToUpdate.setFullname("User updated");
        User userUpdated = userRepositoryUnderTest.save(userToUpdate);

        // ASSERT
        assertEquals(userToUpdate.getUsername(), userUpdated.getUsername());
        assertEquals(userToUpdate.getPassword(), userUpdated.getPassword());
        assertEquals(userToUpdate.getFullname(), userUpdated.getFullname());
        assertEquals(userToUpdate.getRole(), userUpdated.getRole());
    }

    @Test
    public void findUserById() {
        // ARRANGE
        User userToFind = new User("user", "password", "User", "USER");
        userToFind = userRepositoryUnderTest.save(userToFind);

        // ACT
        Optional<User> userFound = userRepositoryUnderTest.findById(userToFind.getId());

        // ASSERT
        assertTrue(userFound.isPresent());
        assertEquals(userToFind.getUsername(), userFound.get().getUsername());
        assertEquals(userToFind.getPassword(), userFound.get().getPassword());
        assertEquals(userToFind.getFullname(), userFound.get().getFullname());
        assertEquals(userToFind.getRole(), userFound.get().getRole());
    }

    @Test
    public void findAllUsers() {
        // ARRANGE
        User userToFind1 = new User("user1", "password", "User", "USER");
        userRepositoryUnderTest.save(userToFind1);

        User userToFind2 = new User("user2", "password", "User", "USER");
        userRepositoryUnderTest.save(userToFind2);

        User userToFind3 = new User("user3", "password", "User", "USER");
        userRepositoryUnderTest.save(userToFind3);

        // ACT
        List<User> listUsers = userRepositoryUnderTest.findAll();

        // ASSERT
        assertTrue(listUsers.size() == 3);
    }

    @Test
    public void deleteUserById() {
        // ARRANGE
        User userToDelete = new User("user", "password", "User", "USER");
        userToDelete = userRepositoryUnderTest.save(userToDelete);

        // ACT
        Integer id = userToDelete.getId();
        userRepositoryUnderTest.deleteById(id);

        // ASSERT
        Optional<User> userDeleted = userRepositoryUnderTest.findById(id);
        assertFalse(userDeleted.isPresent());
    }
}
