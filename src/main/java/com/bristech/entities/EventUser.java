package com.bristech.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_event")
public class EventUser implements Serializable{

    @EmbeddedId
    @JsonBackReference
    private EventUserId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("eventId")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JsonBackReference
    private User user;

    @Column(name = "attend")
    private Boolean attended;

    public EventUser() {
    }

    public EventUser(EventUserId id, Event event, User user, Boolean attended) {
        this.id = id;
        this.event = event;
        this.user = user;
        this.attended = attended;
    }

    public EventUserId getId() {
        return id;
    }

    public void setId(EventUserId id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }
}
