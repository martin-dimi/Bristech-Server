package com.bristech.controllers;

import com.bristech.entities.Event;
import com.bristech.entities.User;
import com.bristech.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.bristech.config.ControllerConfiguration.EVENT_MAIN_URL;
import static com.bristech.config.ControllerConfiguration.PATH_ALL;
import static com.bristech.config.ControllerConfiguration.PATH_LOGIN;


@RestController
@RequestMapping(value = EVENT_MAIN_URL)
public class EventController {

    public static final String TAG = "EventController";

    private EventService mEventService;

    @Autowired
    public EventController(EventService mEventService) {
        this.mEventService = mEventService;
    }

    @RequestMapping(value = PATH_ALL, method = RequestMethod.GET)
    public List<Event> getAllEvents(){
        List<Event> events = mEventService.getAllEvents();
        //TODO event logic
        //TODO return 401 if error
        return events;
    }

}

