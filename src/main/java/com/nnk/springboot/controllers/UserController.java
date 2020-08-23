package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.ResourceAlreadyExistException;
import com.nnk.springboot.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller in charge of managing the endpoints for User entities.
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    /**
     * Method managing the "/user/list" endpoint HTTP request to get the list of all Users.
     *
     * @param model The Model
     * @return The name of the View
     */
    @RequestMapping("/user/list")
    public String home(Model model) {
        logger.info("Request : /user/list");

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);

        logger.info("Success : users found, returning 'user/list' view");

        return "user/list";
    }

    /**
     * Method managing the GET "/user/add" endpoint HTTP request to add a User.
     *
     * @param user The User
     * @return The name of the View
     */
    @GetMapping("/user/add")
    public String addUser(User user) {

        logger.info("Request : GET /user/add");
        logger.info("Success : returning 'user/add' view");

        return "user/add";
    }

    /**
     * Method managing the POST "/user/validate" endpoint HTTP request to add a User.
     *
     * @param user The User to add
     * @param result The BindingResult containing the result of the fields validation
     * @return The name of the View
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result) {

        logger.info("Request : POST /rating/validate");

        // username must be unique, so we check that the username for the new user to create does not already exist
        if (userService.findUserByUsername(user.getUsername()) != null) {
            throw new ResourceAlreadyExistException(user.getUsername(), "User");
        }

        if (!result.hasErrors()) {
            userService.createUser(user);

            logger.info("Success : new user created, redirect to '/user/list' view");

            return "redirect:/user/list";
        }

        logger.error("Error in fields validation : new user not created, returning '/user/add' view");

        return "user/add";
    }

    /**
     * Method managing the GET "/user/update/{id}" endpoint HTTP request to update a User.
     *
     * @param id The id of the User to update
     * @param model The Model
     * @return The name of the View
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        logger.info("Request : GET /user/update/{}", id);

        User user = userService.findUserById(id);
        user.setPassword("");
        model.addAttribute("user", user);

        logger.info("Success : user with id {} to update found, returning '/user/update' view", id);

        return "user/update";
    }

    /**
     * Method managing the POST "/user/update/{id}" endpoint HTTP request to update a User.
     *
     * @param user The User to update
     * @param result The BindingResult containing the result of the fields validation
     * @return The name of the View
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@Valid User user, BindingResult result) {

        logger.info("Request : POST /user/update/{}", user.getId());

        // username must be unique, so we check that the username for the new user to create does not already exist (except if it is the user to update)
        User usernameAlreadyExist = userService.findUserByUsername(user.getUsername());
        if ( usernameAlreadyExist != null && !usernameAlreadyExist.getId().equals(user.getId()) ) {
            throw new ResourceAlreadyExistException(user.getUsername(), "User");
        }

        if (result.hasErrors()) {

            logger.error("Error in fields : user with id {} not updated, returning '/user/update' view", user.getId());

            return "user/update";
        }

        userService.updateUser(user);

        logger.info("Success : user with id {} updated, redirect to '/user/list'", user.getId());

        return "redirect:/user/list";
    }

    /**
     * Method managing the GET "/user/delete/{id}" endpoint HTTP request to delete a User.
     *
     * @param id The id of the User to delete
     * @return The name of the View
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {

        logger.info("Request : GET /user/delete/{}", id);

        userService.deleteUserById(id);

        logger.info("Success : user with id {} deleted, redirect to '/user/list'", id);

        return "redirect:/user/list";
    }
}
