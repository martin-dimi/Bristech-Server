package com.bristech.service;

import com.bristech.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User getUserById(long id);

    User getUserByUsername(String username);

    void createUser(User user);

    void deleteUserById(long id);

}
