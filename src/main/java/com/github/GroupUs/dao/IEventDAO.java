package com.github.GroupUs.dao;

import com.github.GroupUs.vo.EventInfo;

public interface IEventDAO {
    // add event entity into database
    public boolean doCreate(EventInfo vo) throws Exception;

    // find the event according to eventID
    public EventInfo findByEventId(String eventId) throws Exception;

    // TODO: find all the events according to category and list them in the order of distance to user
}
