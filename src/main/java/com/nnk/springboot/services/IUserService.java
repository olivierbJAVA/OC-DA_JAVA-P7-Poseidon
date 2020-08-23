package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.ResourceNotFoundException;

import java.util.List;

/**
 * Interface to implement for managing the services for User entities.
 */
public interface IUserService {

    /**
     * Return all Users.
     *
     * @return The list of all Users
     */
    List<User> findAllUsers();

    /**
     * Return a User given its id.
     *
     * @param id The id of the User
     * @return The User corresponding to the id
     * A ResourceNotFoundException is thrown if no User is found for the given id
     */
    User findUserById(Integer id) throws ResourceNotFoundException;

    /**
     * Return a User given its userName.
     *
     * @param userName The userName of the User
     * @return The User corresponding to the userName
     * A ResourceNotFoundException is thrown if no User is found for the given userName
     */
    User findUserByUsername(String userName);

    /**
     * Create a User.
     *
     * @param user The User to create
     * @return The User created
     */
    User createUser(User user);

    /**
     * Update a User.
     *
     * @param user The User to update
     * @return The User updated
     * A ResourceNotFoundException is thrown if the User to update does not exist
     */
    User updateUser(User user) throws ResourceNotFoundException;

    /**
     * Delete a User.
     *
     * @param id The id of the User
     * A ResourceNotFoundException is thrown if the User to delete does not exist
     */
    void deleteUserById(Integer id) throws ResourceNotFoundException;
}
