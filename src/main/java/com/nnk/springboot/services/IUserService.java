package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.RecordNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAllUsers();

    User findUserById(Integer id) throws RecordNotFoundException;

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Integer id) throws RecordNotFoundException;
}
