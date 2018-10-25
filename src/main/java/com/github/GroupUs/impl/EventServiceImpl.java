package com.github.GroupUs.impl;

import com.github.GroupUs.dbc.DatabaseConnection;
import com.github.GroupUs.factory.DAOFactory;
import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.service.IEventService;
import com.github.GroupUs.vo.EventInfo;
import com.github.GroupUs.vo.UserInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.github.GroupUs.Main.userId;

public class EventServiceImpl implements IEventService {
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(EventInfo vo) throws Exception {
        try {
            if (DAOFactory.getIEventDAOInstance(this.dbc.getConnection()).findByEventId(vo.getEventId()) == null) {
                String creator = userId;
                Date createdAt = new Date();
                Date modifiedAt = new Date();
                String eventId = userId + createdAt;
                vo.setCreator(creator);
                vo.setCreatedAt(createdAt);
                vo.setModifiedAt(modifiedAt);
                vo.setEventId(eventId);
                UserInfo user = ServiceFactory.getIUserServiceInstance().get(creator);
                List<String> userPosted = user.getPosted();
                userPosted.add(eventId);
                user.setPosted(userPosted);
                ServiceFactory.getIUserServiceInstance().update(user);
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
