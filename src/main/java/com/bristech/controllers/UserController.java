package com.bristech.controllers;


import com.bristech.entities.User;
import com.bristech.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bristech.config.ControllerConfiguration.*;


@SuppressWarnings("unused")
@RestController
@RequestMapping(value = USER_MAIN_URL)
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method that's called when SSO from app
     * @param token Firebase ID Token
     * @return the user found in the db or created
     */
    @RequestMapping(value = PATH_LOGIN, method = RequestMethod.GET)
    public User getUser(@RequestHeader("token") String token){
        User user = userService.getOrCreateUserFromToken(token);
        LOGGER.info("Request user:" + user.getEmail());
        // Extra logic here
        //TODO return appropriate responseentity see eventController
        return user;
    }

    /**
     * Method called by the app admin
     * @return returns a list of all users
     */
    @RequestMapping(value = PATH_ALL, method = RequestMethod.GET)
    public List<User> getAllUsers() {
        LOGGER.info("Request all users");
        //TODO return appropriate responseentity see eventController

        return userService.getAllUsers();
    }

    /**
     * Creates a given user
     * @param user to be created
     */
    @RequestMapping(value = PATH_CREATE, method = RequestMethod.POST)
    public void createUser(@RequestBody User user){
        LOGGER.info("Request user creation:" + user.getEmail());
        //TODO return appropriate responseentity see eventController

        userService.createUser(user);
    }
}
