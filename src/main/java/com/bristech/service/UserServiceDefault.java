package com.bristech.service;

import com.bristech.entities.User;
import com.bristech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceDefault implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(long id) {
        //Todo does the user exist?
        return userRepo.findOne(id);
    }

    @Override
    public void createUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        //Todo Does the user exit
        //Todo Is the user an Admin
        userRepo.delete(id);
    }
}
