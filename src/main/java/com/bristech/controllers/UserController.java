package com.bristech.controllers;


import com.bristech.config.FirebaseConfiguration;
import com.bristech.entities.User;
import com.bristech.service.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.bristech.config.ControllerConfiguration.*;


@RestController
@RequestMapping(value = USER_MAIN_URL)
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private UserService userService;
    private final FirebaseAuth firebase;

    @Autowired
    public UserController(UserService userService, FirebaseAuth firebase) {
        this.userService = userService;
        this.firebase = firebase;
    }

    @RequestMapping(method = RequestMethod.GET)
    public User getUser(@RequestHeader("token") String token) throws ExecutionException, InterruptedException {
        User user = null;

        if(token != null && !token.equals("")){
            LOGGER.info("token: " + token);
            FirebaseToken decodedToken = firebase.verifyIdTokenAsync(token).get();
            String email = decodedToken.getEmail();
            String backdrop = decodedToken.getPicture();
            LOGGER.info("email: " + email);

            user = userService.getUserByUsername(email);
            if(user == null) {
                user = new User();
                user.setEmail(email);
                user.setPicture(backdrop);
                userService.createUser(user);
            }
            LOGGER.info("user: " + user.getFirstName());
        }

        return user;
    }

    @RequestMapping(method = RequestMethod.GET, value = PATH_ALL)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = PATH_ID)
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }


    //TODO add getUserByUsername and delete with query
}
