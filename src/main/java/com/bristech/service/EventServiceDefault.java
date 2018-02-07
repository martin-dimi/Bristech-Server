package com.bristech.service;

import com.bristech.entities.Event;
import com.bristech.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceDefault implements EventService {

    @Autowired
    private EventRepository eventRepo;

    @Override
    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    @Override
    public Event getEventById(long id) {
        return eventRepo.findOne(id);
    }

    @Override
    public void createEvent(Event event) {
        eventRepo.save(event);
    }

    @Override
    public void deleteEventById(long id) {
        eventRepo.delete(id);
    }
}
