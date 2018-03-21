package com.bristech.controllers;

import com.bristech.entities.Event;
import com.bristech.entities.User;
import com.bristech.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    @RequestMapping(value = "/meetup", method = RequestMethod.GET)
    public String getEmployees(){
        final String uri = "https://api.meetup.com/bristech/events?page=20&sig_id=250691112&sig=35e5ab975c6a2a217eedb6aeef7faacae5965005";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return result;
    }

}

