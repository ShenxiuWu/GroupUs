package com.github.GroupUs.service;

import com.github.GroupUs.vo.EventInfo;

import java.util.List;

public interface IEventService {
    public boolean insert(EventInfo vo) throws Exception;
    public EventInfo get(String eventId) throws Exception;
    public boolean join(String userId, String eventId) throws Exception;
    public List<EventInfo> searchByCategory(String category, String currentLocation) throws Exception;
}
