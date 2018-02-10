package com.bristech.controllers;

import com.bristech.entities.Event;
import com.bristech.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bristech.config.ControllerConfiguration.*;


@RestController
@RequestMapping(value = EVENT_MAIN_URL)
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(method = RequestMethod.GET, value = PATH_ALL)
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @RequestMapping(method = RequestMethod.GET, value = PATH_ID)
    public Event getEventById(@PathVariable long id) {
        return eventService.getEventById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = PATH_CREATE)
    public void createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = PATH_ID)
    public void deleteEventById(@PathVariable long id) {
        eventService.deleteEventById(id);
    }

}

