package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extending the JpaRepository interface to manage CRUD methods for User entities, using Spring DataJPA.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Method returning a User from the repository given its username.
     *
     * @param username The userName of the user
     * @return The User corresponding to the username or null if there
     * is no User for the given username
     */
    User findUserByUsername(String username);
}
