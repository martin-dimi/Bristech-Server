package com.bristech.service;


import com.bristech.entities.Event;
import com.bristech.entities.User;

import java.util.List;

public interface UserService{

    List<User> getAllUsers();

    User getOrCreateUserFromToken(String token);

    User getUserFromToken(String token);

    User getUserFromEmail(String email);

    boolean userRegisterForEvent(User user, Event event);

    boolean userAttendingEvent(User user, Event event);

    User createUser(User user);

}
