package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.constraints.NotBlank;
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
    public void ratingTests() {

        User userTest = new User();
        userTest.setUsername("user");
        userTest.setPassword("password");
        userTest.setFullname("User");
        userTest.setRole("USER");

        // Save
        userTest = userRepositoryUnderTest.save(userTest);
        assertNotNull(userTest.getId());
        assertTrue(userTest.getFullname().equals("User"));

        // Update
        userTest.setFullname("User updated");
        userTest = userRepositoryUnderTest.save(userTest);
        assertTrue(userTest.getFullname().equals("User updated"));

        // Find by id
        Optional<User> userGet = userRepositoryUnderTest.findById(userTest.getId());
        assertTrue(userGet.isPresent());
        assertEquals(userTest.getUsername(), userGet.get().getUsername());
        assertEquals(userTest.getPassword(), userGet.get().getPassword());
        assertEquals(userTest.getFullname(), userGet.get().getFullname());
        assertEquals(userTest.getRole(), userGet.get().getRole());

        // Find all
        User userTest2 = new User();
        userTest2.setUsername("user");
        userTest2.setPassword("password");
        userTest2.setFullname("User");
        userTest2.setRole("USER");
        userRepositoryUnderTest.save(userTest2);

        User userTest3 = new User();
        userTest3.setUsername("user");
        userTest3.setPassword("password");
        userTest3.setFullname("User");
        userTest3.setRole("USER");
        userRepositoryUnderTest.save(userTest3);

        List<User> listResult = userRepositoryUnderTest.findAll();
        assertTrue(listResult.size() == 3);

        // Delete
        Integer id = userTest.getId();
        //ratingRepositoryUnderTest.delete(ratingTest);
        userRepositoryUnderTest.deleteById(id);
        Optional<User> userDeleted = userRepositoryUnderTest.findById(id);
        assertFalse(userDeleted.isPresent());
    }
}
