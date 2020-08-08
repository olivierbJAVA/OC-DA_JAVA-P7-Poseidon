package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    User getUserById(Integer id);

    User saveOrUpdate(User user);

    void delete (Integer id);

}
