package com.bristech.service;

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
    private static final String EVENTS_UPCOMING = "upcoming";
    private static final String EVENTS_PAST = "past";

    private final EventRepository mEventRepository;

    @Autowired
    public EventServiceDefault(EventRepository mEventRepository) {
        this.mEventRepository = mEventRepository;
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
        events = mEventRepository.findByMStatus(EVENTS_UPCOMING);

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
        events = mEventRepository.findByMStatus(EVENTS_PAST);

        if (events == null) {
            LOGGER.warn("There are no past events");
            events = new ArrayList<>();
        }

        return events;
    }

    @Override
    public void updateEvents(List<Event> events) {
        if (!(events == null || events.size() == 0)) {
            mEventRepository.save(events);
        }
    }


}
