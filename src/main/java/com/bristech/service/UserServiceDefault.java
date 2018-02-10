package com.bristech.service;

import com.bristech.entities.User;
import com.bristech.entities.UserCredentials;
import com.bristech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceDefault implements UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserServiceDefault(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getUserByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException(username);

        return new UserCredentials(user);
    }

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
    public User getUserByUsername(String username) {
        //TODO check if user exists
        return userRepo.getUserByUsername(username);
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
