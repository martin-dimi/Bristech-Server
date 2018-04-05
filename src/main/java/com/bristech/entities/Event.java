
package com.bristech.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "time")
    private Date time;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "waitlist_count")
    private Long waitlistCount;

    @Column(name = "status")
    private String status;

    @SerializedName("event_url")
    @Column(name = "url")
    private String eventUrl;

    @OneToMany(
            mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    private List<EventUser> users;

    public Event() {
        users = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getWaitlistCount() {
        return waitlistCount;
    }

    public void setWaitlistCount(Long waitlistCount) {
        this.waitlistCount = waitlistCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    public List<EventUser> getUsers() {
        return users;
    }

    public void setUsers(List<EventUser> users) {
        this.users = users;
    }
}
