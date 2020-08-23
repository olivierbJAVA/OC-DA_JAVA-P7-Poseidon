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
@SpringBootTest
@Sql({"/schema-test.sql"})
public class UserServiceImplITests {

    @Autowired
    private UserServiceImpl userServiceImplUnderTest;

    @Test
    public void createUser() {
        // ARRANGE
        User userToCreate = new User("Username", "Password", "Fullname", "Role"  );
            
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
    public void updateUser() {
        // ARRANGE
        User userToUpdate = new User("Username", "Password", "Fullname", "Role"  );

        userServiceImplUnderTest.createUser(userToUpdate);

        // ACT
        userToUpdate.setRole("UpdatedRole");
        User userUpdated = userServiceImplUnderTest.updateUser(userToUpdate);

        // ASSERT
        assertEquals(userToUpdate.getUsername(), userUpdated.getUsername());
        assertEquals(userToUpdate.getPassword(), userUpdated.getPassword());
        assertEquals(userToUpdate.getFullname(), userUpdated.getFullname());
        assertEquals(userToUpdate.getRole(), userUpdated.getRole());
    }

    @Test
    public void findUserById() {
        // ARRANGE
        User userToFind = new User("Username", "Password", "Fullname", "Role"  );
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
    public void findAllUsers() {
        // ARRANGE
        User userToFind1 = new User("Username1", "Password1", "Fullname1", "Role1"  );
        userServiceImplUnderTest.createUser(userToFind1);

        User userToFind2 = new User("Username2", "Password2", "Fullname2", "Role2"  );
        userServiceImplUnderTest.createUser(userToFind2);

        User userToFind3 = new User("Username3", "Password3", "Fullname3", "Role3"  );
        userServiceImplUnderTest.createUser(userToFind3);

        // ACT
        List<User> listUsers = userServiceImplUnderTest.findAllUsers();

        // ASSERT
        assertTrue(listUsers.size() == 3);
    }

    @Test
    public void deleteUserById() {
        // ARRANGE
        User userToDelete = new User("Username", "Password", "Fullname", "Role"  );
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
