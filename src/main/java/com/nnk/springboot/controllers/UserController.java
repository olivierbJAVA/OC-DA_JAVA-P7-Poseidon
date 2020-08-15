package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user/list")
    public String home(Model model) {
        logger.info("Request : GET /user/list");

        model.addAttribute("users", userRepository.findAll());

        logger.info("Success : users found, returning 'user/list' view");

        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User user) {

        logger.info("Request : GET /user/add");
        logger.info("Success : returning 'user/add' view");

        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {

        logger.info("Request : POST /rating/validate");

        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);

            logger.info("Success : new user created, redirect to '/user/list' view");

            return "redirect:/user/list";
        }

        logger.error("Error in fields validation : new user not created, returning '/user/add' view");

        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Request : GET /user/update/{}", id);

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);

        logger.info("Success : user with id {} to update found, returning '/user/update' view", id);

        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@Valid User user, BindingResult result, Model model) {

        logger.info("Request : POST /rating/update/{}", user.getId());

        if (result.hasErrors()) {

            logger.error("Error in fields : user with id {} not updated, returning '/user/update' view", user.getId());

            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        logger.info("Success : user with id {} updated, redirect to '/user/list'", user.getId());

        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {

        logger.info("Request : GET /user/delete/{}", id);

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);

        logger.info("Success : rating with id {} deleted, redirect to '/rating/list'", id);

        return "redirect:/user/list";
    }
}
