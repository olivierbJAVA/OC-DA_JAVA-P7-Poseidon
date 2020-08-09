package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.RecordNotFoundException;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserImplService implements IUserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Integer id) throws RecordNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No record exist for given id");
        }
        //return userRepository.getOne(id);
    }

    @Override
    public User createUser(User User) {
        return userRepository.save(User);
    }

    @Override
    public User updateUser(User User) {
        return userRepository.save(User);
    }

    @Override
    public void deleteUser(Integer id) throws RecordNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No record exist for given id");
        }

        //userRepository.deleteById(id);
    }
}
