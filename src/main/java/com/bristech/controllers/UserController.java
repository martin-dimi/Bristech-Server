package com.bristech.controllers;


import com.bristech.entities.User;
import com.bristech.service.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.bristech.config.ControllerConfiguration.*;
import static com.bristech.config.JWTConfiguration.*;


@RestController
@RequestMapping(value = USER_MAIN_URL)
public class UserController {

    private UserService userService;
    private BCryptPasswordEncoder bCrypt;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCrypt) {
        this.userService = userService;
        this.bCrypt = bCrypt;
    }

    @RequestMapping(method = RequestMethod.GET)
    public User getUser(HttpServletRequest request){
        String token = request.getHeader(HEADER);
        long userId = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .get(CLAIM_USER_ID, Integer.class);

        return userService.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = PATH_ALL)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = PATH_ID)
    public User getUserById(@PathVariable long id){
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = PATH_CREATE)
    public void createUser(@RequestBody User user){
        user.setPassword(bCrypt.encode(user.getPassword()));
        userService.createUser(user);
    }

    //TODO add getUserByUsername and delete with query


}
