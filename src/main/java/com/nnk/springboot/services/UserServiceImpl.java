package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Class in charge of managing the services for the Users.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Return all Users.
     *
     * @return The list of all Users
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Return a User given its id.
     *
     * @param id The id of the User
     * @return The User corresponding to the id
     * A ResourceNotFoundException is thrown if no User is found for the given id
     */
    @Override
    public User findUserById(Integer id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "User"));
    }

    /**
     * Return a User given its userName.
     *
     * @param userName The userName of the User
     * @return The User corresponding to the userName
     * A ResourceNotFoundException is thrown if no User is found for the given userName
     */
    @Override
    public User findUserByUsername(String userName) throws ResourceNotFoundException {
        return userRepository.findUserByUsername(userName);
    }

    /**
     * Create a User.
     *
     * @param user The User to create
     * @return The User created
     */
    @Override
    public User createUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Update a User.
     *
     * @param user The User to update
     * @return The User updated
     * A ResourceNotFoundException is thrown if the User to update does not exist
     */
    @Override
    public User updateUser(User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException(user.getId(), "User"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Delete a User.
     *
     * @param id The id of the User
     * A ResourceNotFoundException is thrown if the User to delete does not exist
     */
    @Override
    public void deleteUserById(Integer id) throws ResourceNotFoundException {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "User"));
        userRepository.deleteById(id);
    }
}
