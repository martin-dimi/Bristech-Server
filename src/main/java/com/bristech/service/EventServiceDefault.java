package com.bristech.service;

import com.bristech.entities.Event;
import com.bristech.entities.User;
import com.bristech.repositories.EventRepository;
import com.bristech.repositories.UserRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceDefault implements EventService {

    private static final Logger LOGGER = LogManager.getLogger(EventServiceDefault.class);
    private static final String EVENTS_UPCOMING = "upcoming";
    private static final String EVENTS_PAST = "past";

    private final EventRepository mEventRepository;
    private final UserRepository mUserRepo;


    @Autowired
    public EventServiceDefault(EventRepository mEventRepository, UserRepository mUserRepo) {
        this.mEventRepository = mEventRepository;
        this.mUserRepo = mUserRepo;
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events;
        events = mEventRepository.findAll();

        if (events == null) {
            LOGGER.warn("Events are null");
            events = new ArrayList<>();
        }

        return events;
    }

    @Override
    public List<Event> getUpcomingEvents() {
        List<Event> events;
        events = mEventRepository.findByStatus(EVENTS_UPCOMING);

        LOGGER.info(events.get(0).getDescription());

        if (events == null) {
            LOGGER.warn("There are no upcoming events.");
            events = new ArrayList<>();
        }

        return events;
    }

    @Override
    public List<Event> getPastEvents() {
        List<Event> events;
        events = mEventRepository.findByStatus(EVENTS_PAST);

        if (events == null) {
            LOGGER.warn("There are no past events");
            events = new ArrayList<>();
        }

        return events;
    }

    @Override
    public Event getEventById(long eventId) {
        Event event;

        // TODO Create checks
        event = mEventRepository.findOne(eventId);
        if(event != null){
            System.out.println("EVENT FOUND");
        }

        return event;
    }


    @Override
    public void updateEvents(List<Event> events) {
        if (!(events == null || events.size() == 0)) {
            mEventRepository.save(events);
        }
    }

}
