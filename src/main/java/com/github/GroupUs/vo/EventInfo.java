package com.github.GroupUs.vo;

import java.io.Serializable;
import java.util.Date;


//{
//        “_id”: ObjectID,
//        “EventId”: 1,
//        “Creator”: “XX@columbia.edu”,
//        “Category”: “XX”,
//        “Subject”: “XX”,
//        “Description”: ”XX”,
//        “Start”: 12345,
//        “End”: 23456,
//        “Location”: “Columbia University”,
//        “Geo”: 123, 456,
//        “Memo”: “NWC”
//        }

@SuppressWarnings("serial")
public class EventInfo implements Serializable {
    private String eventId;
    private String creator;
    private String category;
    private String subject;
    private String description;
    private Date start;
    private Date end;
    private String location;
    private String geo;
    private String memo;
    private Date createdAt;
    private Date modifiedAt;

    public EventInfo() { };

    public EventInfo(String eventId, String creator, String category, String subject, String description, Date start, Date end, String location, String geo, String memo, Date createdAt, Date modifiedAt) {
        this.eventId = eventId;
        this.creator = creator;
        this.category = category;
        this.subject = subject;
        this.description = description;
        this.start = start;
        this.end = end;
        this.location = location;
        this.geo = geo;
        this.memo = memo;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public void setModifiedAt(Date modifiedAt) { this.modifiedAt = modifiedAt; }

    public String getEventId() {
        return eventId;
    }

    public String getCreator() {
        return creator;
    }

    public String getCategory() {
        return category;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getLocation() {
        return location;
    }

    public String getGeo() {
        return geo;
    }

    public String getMemo() {
        return memo;
    }

    public Date getCreatedAt() { return createdAt; }

    public Date getModifiedAt() { return modifiedAt; }
}

