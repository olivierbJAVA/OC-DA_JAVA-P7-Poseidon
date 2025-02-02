package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.ResourceNotFoundException;

import java.util.List;

/**
 * Interface to be implemented for managing the services for User entities.
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
     * @throws ResourceNotFoundException if no User is found for the given id
     */
    User findUserById(Integer id) throws ResourceNotFoundException;

    /**
     * Return a User given its username.
     *
     * @param username The username of the User
     * @return The User corresponding to the username
     */
    User findUserByUsername(String username);

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
     * @throws ResourceNotFoundException if the User to update does not exist
     */
    User updateUser(User user) throws ResourceNotFoundException;

    /**
     * Delete a User.
     *
     * @param id The id of the User
     * @throws ResourceNotFoundException if the User to delete does not exist
     */
    void deleteUserById(Integer id) throws ResourceNotFoundException;
}
