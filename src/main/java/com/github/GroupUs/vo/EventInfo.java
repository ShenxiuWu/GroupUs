package com.github.GroupUs.vo;

import com.github.GroupUs.impl.distance;

import java.io.Serializable;
import java.util.Comparator;
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
    private String usrCurLocation;
    private String memo;
    private Date createdAt;
    private Date modifiedAt;

    public EventInfo() {};

    public EventInfo(String eventId, String creator, String category, String subject, String description, Date start, Date end, String location, String usrCurLocation, String memo, Date createdAt, Date modifiedAt) {
        this.eventId = eventId;
        this.creator = creator;
        this.category = category;
        this.subject = subject;
        this.description = description;
        this.start = start;
        this.end = end;
        this.location = location;
        this.usrCurLocation = usrCurLocation;
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

    public void setUsrCurLocation(String usrCurLocation) {
        this.usrCurLocation = usrCurLocation;
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

    public String getUsrCurLocation() {
        return usrCurLocation;
    }

    public String getMemo() {
        return memo;
    }

    public Date getCreatedAt() { return createdAt; }

    public Date getModifiedAt() { return modifiedAt; }

//    @Override
//    public int compareTo(EventInfo o) {
//        Integer thisMeters = distance.distanceMatirx(new String [] {this.location}, new String [] {this.usrCurLocation});
//        Integer oMeters = distance.distanceMatirx(new String [] {o.location}, new String [] {o.usrCurLocation});
//        if (thisMeters > oMeters) {
//            return 1;
//        }else if (thisMeters < oMeters) {
//            return -1;
//        } else {
//            return 0;
//        }
//    }
    public static class SortByDistance implements Comparator<EventInfo> {

    @Override
    public int compare(EventInfo o1, EventInfo o2) {
            Integer o1Meters = distance.distanceMatirx(new String [] {o1.location}, new String [] {o1.usrCurLocation});
            Integer o2Meters = distance.distanceMatirx(new String [] {o2.location}, new String [] {o2.usrCurLocation});
            if (o1Meters > o2Meters) {
                return 1;
            } else if (o1Meters < o2Meters) {
                return -1;
            } else {
                return 0;
        }
        }
    }
}

