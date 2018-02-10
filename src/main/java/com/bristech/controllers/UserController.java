package com.bristech.controllers;


import com.bristech.entities.AppUser;
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
    public List<AppUser> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public AppUser getUserById(@PathVariable long id){
        System.out.println("Get user by id CALLING, id=" + id);
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/username/{username}")
    public AppUser getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@RequestBody AppUser appUser){
        userService.createUser(appUser);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteUserById(@PathVariable long id){
        userService.deleteUserById(id);
    }

}
