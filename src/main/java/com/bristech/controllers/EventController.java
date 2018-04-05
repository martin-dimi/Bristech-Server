package com.bristech.controllers;

import com.bristech.entities.Event;
import com.bristech.entities.User;
import com.bristech.service.EventService;
import com.bristech.service.UserService;
import com.bristech.utils.EventsUtils;
import com.bristech.utils.MeetupUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.List;

import static com.bristech.config.ControllerConfiguration.*;

@SuppressWarnings("unused")
@RestController
@RequestMapping(value = EVENT_MAIN_URL)
public class EventController {

    private static final Logger LOGGER = LogManager.getLogger(EventController.class);
    private final EventService mEventService;

    @Autowired
    public EventController(EventService mEventService) {
        this.mEventService = mEventService;
    }

    @RequestMapping(value = PATH_ALL, method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getAllEvents(HttpServletRequest request) {
        LOGGER.info("Request UPCOMING EVENTS from " + request.getRemoteAddr());

        List<Event> events = mEventService.getAllEvents();

        if (events == null || events.size() == 0) {
            LOGGER.warn("No events where found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value = PATH_EVENT_UPCOMING, method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getUpcomingEvents(HttpServletRequest request) {
        LOGGER.info("Request UPCOMING EVENTS from " + request.getRemoteAddr());
        List<Event> events = mEventService.getUpcomingEvents();

        if (events == null || events.size() == 0) {
            LOGGER.warn("No events where found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @RequestMapping(value = PATH_EVENT_PAST, method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getPastEvents(HttpServletRequest request) {
        LOGGER.info("Request PAST EVENTS from " + request.getRemoteAddr());
        List<Event> events = mEventService.getPastEvents();

        if (events == null || events.size() == 0) {
            LOGGER.warn("No events where found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }


    @RequestMapping(value = PATH_EVENT_UPDATE, method = RequestMethod.GET)
    public ResponseEntity<String> updateEventsFromMeetup() {
        LOGGER.info("Request UPDATE EVENTS");

        URL url = MeetupUtils.getUpcomingEventsURL();
        String result = MeetupUtils.getResponseFromURL(url);
        List<Event> events = EventsUtils.getEventsFromJSON(result);

        if (events != null) {
            mEventService.updateEvents(events);
            LOGGER.info("Successfully updated " + events.size() + "events from Meetup.");
            return new ResponseEntity<>("Successfully updated events from Meetup.", HttpStatus.OK);
        } else {
            LOGGER.info("Couldn't retrieve events from Meetup.");
            return new ResponseEntity<>("Couldn't update events from Meetup", HttpStatus.I_AM_A_TEAPOT);
        }

    }



}

