package com.bristech.service;

import com.bristech.entities.Event;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents();

    List<Event> getUpcomingEvents();

    List<Event> getPastEvents();

    void updateEvents(List<Event> events);

}
