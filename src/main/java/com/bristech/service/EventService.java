package com.bristech.service;

import com.bristech.entities.Event;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents();

    Event getEventById(long id);

    void createEvent(Event event);

    void deleteEventById(long id);

}
