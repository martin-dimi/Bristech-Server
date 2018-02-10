package com.bristech.service;

import com.bristech.entities.AppUser;

import java.util.List;

public interface UserService {

    List<AppUser> getAllUsers();
    AppUser getUserById(long id);
    AppUser getUserByUsername(String username);
    void createUser(AppUser appUser);
    void deleteUserById(long id);

}
