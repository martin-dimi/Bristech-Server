package com.bristech.controllers;

import com.bristech.entities.Event;
import com.bristech.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Event getEventById(@PathVariable long id){
        return eventService.getEventById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createEvent(@RequestBody Event event){
        eventService.createEvent(event);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteEventById(@PathVariable long id){
        eventService.deleteEventById(id);
    }

}

