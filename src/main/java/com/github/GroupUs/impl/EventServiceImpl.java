package com.github.GroupUs.impl;

import com.github.GroupUs.dbc.DatabaseConnection;
import com.github.GroupUs.factory.DAOFactory;
import com.github.GroupUs.service.IEventService;
import com.github.GroupUs.vo.EventInfo;

public class EventServiceImpl implements IEventService {
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(EventInfo vo) throws Exception {
        try {
            if (DAOFactory.getIEventDAOInstance(this.dbc.getConnection()).findByEventId(vo.getEventId()) == null) {
                return DAOFactory.getIEventDAOInstance(this.dbc.getConnection()).doCreate(vo);
            }
            return false;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public EventInfo get(String eventId) throws Exception {
        try {
            return DAOFactory.getIEventDAOInstance(this.dbc.getConnection()).findByEventId(eventId);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
