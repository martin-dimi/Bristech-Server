package com.bristech.service;


import com.bristech.entities.User;

import java.util.List;

public interface UserService{

    List<User> getAllUsers();

    User getOrCreateUserFromToken(String token);

    User getUserFromToken(String token);

    User getUserFromEmail(String email);

    User createUser(User user);

}
