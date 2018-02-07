package com.bristech.controllers;


import com.bristech.entities.User;
import com.bristech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public User getUserById(@PathVariable long id){
        System.out.println("Get user by id CALLING, id=" + id);
        return userService.getUserById(id);
        //lolg
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody User user){
        userService.createUser(user);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteUserById(@PathVariable long id){
        userService.deleteUserById(id);
    }

}
