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

    /**
     * @param vo
     *   @feature length
     *     @ equivalence partition：length smaller than 100 / length larger than 100
     *       @ boundary condition: length = 99, length = 100, length = 101
     *   @feature consist of 26 letters or numbers
     *     @ equivalence partition: string only contains 26 letters or numbers / string contains other special characters
     *       @ boundary condition: string with $, string with space.
     *   @feature valid/invalid locations(locations could/couldn't be found in matrixDistance class)
     *     @ equivalence partition: string enables method distanceCheck return true/false
     *       @ boundary condition: string = null, string = test...
     */
    @Override
    public boolean insert(EventInfo vo) throws Exception {
        try {
            // Test Case: subject
            String subject = vo.getSubject();
            // null
            if (subject == null) {
                return false;
            }
            // length
            if (subject.length() > 20) {
                return false;
            }
            // character
            if (!subject.matches("[a-zA-Z0-9]*")) {
                return false;
            } 
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
                    return false;
                } else {
                    System.out.println(user);
                    List<String> userPosted = user.getPosted();
                    userPosted.add(eventId);
                    user.setPosted(userPosted);
                    ServiceFactory.getIUserServiceInstance().update(user);
                    List<String> userJoined = user.getJoined();
                    userJoined.add(eventId);
                    user.setJoined(userJoined);
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
    public boolean join(String userId, String eventId) throws Exception {
        try {
            String creator = userId;
            UserInfo user = ServiceFactory.getIUserServiceInstance().get(creator);
            List<String> userJoined = user.getJoined();
            if (userJoined.contains(eventId)) {
                System.out.println("event already exist, failed to join");
                return false;
            }
            userJoined.add(eventId);
            user.setJoined(userJoined);
            return ServiceFactory.getIUserServiceInstance().update(user);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public List<EventInfo> searchByCategory(String category, String currentLocation) throws Exception {

        try {
            if (distance.distanceCheck(new String []{currentLocation}) == false) {
                return null;
            }
            List<EventInfo> events = DAOFactory.getIEventDAOInstance(this.dbc.getConnection()).findByCategory(category, currentLocation);
            if (events != null) {
                Calendar calendar = Calendar.getInstance();
                //Date time = calendar.getTime();
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                long timeInMillis = calendar.getTimeInMillis();
                for (int i = 0; i < events.size(); i ++) {
                    if (events.get(i).getEnd().getTime() < timeInMillis) {
                        events.remove(i);
                    }
                }
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
