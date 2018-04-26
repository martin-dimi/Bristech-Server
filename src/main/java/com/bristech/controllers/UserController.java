package com.bristech.controllers;


import com.bristech.entities.Event;
import com.bristech.entities.User;
import com.bristech.service.EventService;
import com.bristech.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bristech.config.ControllerConfiguration.*;


@SuppressWarnings("unused")
@RestController
@RequestMapping(value = USER_MAIN_URL)
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private final UserService userService;
    private final EventService eventService;

    @Autowired
    public UserController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
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
    public User createUser(@RequestBody User user){
        LOGGER.info("Request user creation:" + user.getEmail());
        //TODO return appropriate responseentity see eventController

        return userService.createUser(user);
    }

    @RequestMapping(value = PATH_USER_REGISTER, method = RequestMethod.POST)
    public ResponseEntity<Boolean> registerToEvent(@RequestHeader("email") String email, @RequestHeader("event_id") long eventId){
        LOGGER.info("Request USER ATTEND EVENT");

        User user = userService.getUserFromEmail(email);

        if(user == null){
            LOGGER.warn("User doesn't exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Event event = eventService.getEventById(eventId);


        boolean isGoing = userService.userRegisterForEvent(user, event);
        return new ResponseEntity<>(isGoing, HttpStatus.OK);
    }

    @RequestMapping(value = PATH_USER_ATTEND, method = RequestMethod.POST)
    public ResponseEntity<Boolean> attendEvent(@RequestHeader("email") String email, @RequestHeader("event_id") long eventId){
        LOGGER.info("Request USER ATTEND EVENT");

        System.out.println("REQEUST IS WORKING!");

        User user = userService.getUserFromEmail(email);

        if(user == null){
            LOGGER.warn("User doesn't exist");
            System.out.println("User doesn't exist");

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Event event = eventService.getEventById(eventId);

        boolean isGoing = userService.userAttendingEvent(user, event);
        LOGGER.info(isGoing);
        return new ResponseEntity<>(isGoing, HttpStatus.OK);
    }
}
