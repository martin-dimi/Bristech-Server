package com.bristech.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EventUserId implements Serializable {

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "user_id")
    private Long userId;

    public EventUserId() {
    }

    public EventUserId(Long eventId, Long userId) {
        this.eventId = eventId;
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        EventUserId that = (EventUserId) o;
        return Objects.equals(eventId, that.eventId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, userId);
    }
}
