package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface extending the JpaRepository interface to manage CRUD methods for Users, using Spring DataJPA.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * Method returning a User from the repository given its userName.
     *
     * @param userName The userName of the user
     * @return The User corresponding to the userName or null if there
     * is no User for the given userName
     */
    User findUserByUsername(String userName);
}
