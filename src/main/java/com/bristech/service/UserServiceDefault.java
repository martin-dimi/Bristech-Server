package com.bristech.service;

import com.bristech.entities.AppUser;
import com.bristech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceDefault implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.getUserByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException(username);

        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public AppUser getUserById(long id) {
        //Todo does the user exist?
        return userRepo.findOne(id);
    }

    @Override
    public AppUser getUserByUsername(String username) {
        //TODO check if user exists
        return userRepo.getUserByUsername(username);
    }


    @Override
    public void createUser(AppUser appUser) {
        userRepo.save(appUser);
    }

    @Override
    public void deleteUserById(long id) {
        //Todo Does the user exit
        //Todo Is the user an Admin
        userRepo.delete(id);
    }


}
