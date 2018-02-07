package com.bristech.service;

import com.bristech.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(long id);
    void createUser(User user);
    void deleteUserById(long id);

}
