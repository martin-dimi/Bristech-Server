package com.bristech.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@Table(name = "users")
public class User {

    //This is the user class

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "backdrop")
    private String backdrop;


    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<EventUser> events;

    public User() {
        //for Hibernate
        events = new ArrayList<>();
    }

    public User(String email, String name, String backdrop) {
        this.email = email;
        this.name = name;
        this.backdrop = backdrop;
    }

    public boolean userRegisteringForEvent(Event event){

        long eventId = event.getId();

        for (EventUser eventUser : events) {
            if(eventUser.getEvent().getId().equals(eventId)) {
                events.remove(eventUser);
                return false;
            }
        }

        EventUserId id = new EventUserId(eventId, this.id);
        EventUser eventUser = new EventUser(id, event, this, false);
        events.add(eventUser);
        return true;
    }

    public boolean userAttendingEvent(Event event){
        for (EventUser eventUser : events) {
            if(eventUser.getEvent().getId().equals(event.getId())) {
                eventUser.setAttended(true);
                return true;
            }
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public List<EventUser> getEvents() {
        return events;
    }

    public void setEvents(List<EventUser> events) {
        this.events = events;
    }
}
