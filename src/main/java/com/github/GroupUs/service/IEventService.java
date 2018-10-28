package com.github.GroupUs.service;

import com.github.GroupUs.vo.EventInfo;

public interface IEventService {
    public boolean insert(EventInfo vo) throws Exception;
    public EventInfo get(String eventId) throws Exception;
    public boolean join(String userId, String eventId) throws Exception;
}
