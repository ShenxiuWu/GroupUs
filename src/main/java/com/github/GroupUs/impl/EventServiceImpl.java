package com.github.GroupUs.impl;

import com.github.GroupUs.dbc.DatabaseConnection;
import com.github.GroupUs.factory.DAOFactory;
import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.service.IEventService;
import com.github.GroupUs.vo.EventInfo;
import com.github.GroupUs.vo.UserInfo;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;

import java.util.*;

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
                if (user == null) {
                    System.out.println("user is null, no need to add posted events to");
                } else {
                    System.out.println(user);
                    List<String> userPosted = user.getPosted();
                    userPosted.add(eventId);
                    user.setPosted(userPosted);
                    ServiceFactory.getIUserServiceInstance().update(user);
                }
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

    @Override
    public List<EventInfo> searchByCategory(String category, String currentLocation) throws Exception {
        try {
            List<EventInfo> events = DAOFactory.getIEventDAOInstance(this.dbc.getConnection()).findByCategory(category, currentLocation);
            if (events != null) {
                Calendar calendar = Calendar.getInstance();
                Date time = calendar.getTime();
                long timeInMillis = calendar.getTimeInMillis();
                for (int i = 0; i < events.size(); i ++) {
                    if (events.get(i).getEnd().getTime() > timeInMillis) {
                        events.remove(i);
                    }                }
                Collections.sort(events, new EventInfo.SortByDistance());
                return events;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

}
