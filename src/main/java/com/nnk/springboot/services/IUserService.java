package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IUserService {

    List<User> findAllUsers();

    User findUserById(Integer id) throws ResourceNotFoundException;

    User createUser(User user);

    User updateUser(User user);

    void deleteUserById(Integer id) throws ResourceNotFoundException;
}
