
package com.bristech.entities;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@SuppressWarnings("unused")
@Entity
@Table(name = "events")
public class Event {

    @Id
    @SerializedName("id")
    @Column(name = "id")
    private Long mId;

    @SerializedName("name")
    @Column(name = "name")
    private String mName;

    @SerializedName("description")
    @Column(name = "description")
    private String mDescription;

    @SerializedName("time")
    @Column(name = "time")
    private Date mTime;

    @SerializedName("duration")
    @Column(name = "duration")
    private Long mDuration;

    @SerializedName("waitlist_count")
    @Column(name = "waitlist_count")
    private Long mWaitlistCount;

    @SerializedName("status")
    @Column(name = "status")
    private String mStatus;

    @SerializedName("event_url")
    @Column(name = "url")
    private String mEventUrl;



    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Long getDuration() {
        return mDuration;
    }

    public void setDuration(Long duration) {
        mDuration = duration;
    }

    public String getEventUrl() {
        return mEventUrl;
    }

    public void setEventUrl(String eventUrl) {
        mEventUrl = eventUrl;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public Date getTime() {
        return mTime;
    }

    public void setTime(Date time) {
        mTime = time;
    }

    public Long getWaitlistCount() {
        return mWaitlistCount;
    }

    public void setWaitlistCount(Long waitlistCount) {
        mWaitlistCount = waitlistCount;
    }

    @Override
    public String toString() {
        return mName;
    }
}
