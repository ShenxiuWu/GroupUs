package com.github.GroupUs.impl;


import com.github.GroupUs.dbc.DatabaseConnection;
import com.github.GroupUs.factory.DAOFactory;
import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.service.IUserService;
import com.github.GroupUs.vo.EventInfo;
import com.github.GroupUs.vo.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements IUserService {
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(UserInfo vo) throws Exception {
        try {
            // TODO: check username(email), password, name


            if (DAOFactory.getIUserDAOInstance(this.dbc.getConnection()).findByEmail(vo.getEmail()) == null) {
                return DAOFactory.getIUserDAOInstance(this.dbc.getConnection()).doCreate(vo);
            }
            return false;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(UserInfo vo) throws Exception {
        try {
            return DAOFactory.getIUserDAOInstance(this.dbc.getConnection()).doUpdate(vo);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public UserInfo get(String email) throws Exception {
        // TODO: check username(email), password, name

        try {
            return DAOFactory.getIUserDAOInstance(this.dbc.getConnection()).findByEmail(email);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public List<EventInfo> getJoinedEvent(String email) throws Exception {
        try {
            UserInfo user = get(email);
            List<String> userJoined = user.getJoined();
            List<EventInfo> joinedEvents = new ArrayList<>();
            for (int i = 0; i < userJoined.size(); i++) {
                String eventId = userJoined.get(i);
                EventInfo event = ServiceFactory.getIEventServiceInstance().get(eventId);
                joinedEvents.add(event);
            }
            return joinedEvents;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public List<EventInfo> getPostedEvent(String email) throws Exception {
        try {
            UserInfo user = get(email);
            List<String> userPosted = user.getPosted();
            List<EventInfo> postedEvents = new ArrayList<>();
            for (int i = 0; i < userPosted.size(); i++) {
                String eventId = userPosted.get(i);
                EventInfo event = ServiceFactory.getIEventServiceInstance().get(eventId);
                postedEvents.add(event);
            }
            return postedEvents;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
