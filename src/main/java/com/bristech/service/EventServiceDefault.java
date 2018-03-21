package com.bristech.service;

import com.bristech.controllers.UserController;
import com.bristech.entities.Event;
import com.bristech.repositories.EventRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceDefault implements EventService {

    private static final Logger LOGGER = LogManager.getLogger(EventServiceDefault.class);

    private EventRepository mEventRepository;

    @Autowired
    public EventServiceDefault(EventRepository mEventRepository) {
        this.mEventRepository = mEventRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = null;
        events = mEventRepository.findAll();

        if(events == null){
            LOGGER.warn("Events are null");
            events = new ArrayList<>();
        }

        return events;
    }
}
