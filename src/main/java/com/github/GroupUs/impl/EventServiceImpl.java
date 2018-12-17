package com.github.GroupUs.impl;

import com.github.GroupUs.dbc.DatabaseConnection;
import com.github.GroupUs.factory.DAOFactory;
import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.service.IEventService;
import com.github.GroupUs.vo.EventInfo;
import com.github.GroupUs.vo.UserInfo;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

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
            String subjectPattern = "^[a-zA-Z0-9][a-zA-Z0-9_\\s]{0,19}$";
            String textPattern = "^[a-zA-Z0-9][a-zA-Z0-9_%&',;=?\\s]{0,99}$";

            if (!Pattern.matches(subjectPattern, vo.getSubject())){
                throw new Exception("The subject format cannot be special characters or too long, please check your input again!");
            } else if (!Pattern.matches(textPattern, vo.getLocation())) {
                throw new Exception("The location format cannot be special characters or too long, please check your input again!");
            }

            String location = vo.getLocation();
            String[] locationCheck = {location};
            boolean bool = distance.distanceCheck(locationCheck);
            if (!bool) {
                throw new Exception("The location should be valid, please check your input again!");
            } else if (!Pattern.matches(textPattern, vo.getMemo())){
                throw new Exception("The memo format cannot be special characters or too long, please check your input again!");
            } else if (!Pattern.matches(textPattern, vo.getDescription())) {
                throw new Exception("The description format cannot be special characters or too long, please check your input again!");
            }

            String creator = userId;
            Date createdAt = new Date();
            Date modifiedAt = new Date();
            String eventId = userId + createdAt;
            vo.setCreator(creator);
            vo.setCreatedAt(createdAt);
            vo.setModifiedAt(modifiedAt);
            vo.setEventId(eventId);
            UserInfo user = ServiceFactory.getIUserServiceInstance().get(creator);
            System.out.println(user);
            List<String> userPosted = user.getPosted();
            userPosted.add(eventId);
            user.setPosted(userPosted);
            ServiceFactory.getIUserServiceInstance().update(user);
            List<String> userJoined = user.getJoined();
            userJoined.add(eventId);
            user.setJoined(userJoined);
            ServiceFactory.getIUserServiceInstance().update(user);
            return DAOFactory.getIEventDAOInstance(this.dbc.getConnection()).doCreate(vo);
        } catch (Exception e) {
            e.printStackTrace();
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
            if (!distance.distanceCheck(new String[] {currentLocation})) {
                return null;
            }
            List<EventInfo> events = DAOFactory.getIEventDAOInstance(this.dbc.getConnection()).findByCategory(category, currentLocation);
            Calendar calendar = Calendar.getInstance();
            //Date time = calendar.getTime();
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            long timeInMillis = calendar.getTimeInMillis();
//            for (int i = 0; i < events.size(); i ++) {
//                System.out.println("End time " + events.get(i).getEnd().getTime());
//            }

            int index = 0;
            while (index < events.size()) {
                if (events.get(index).getEnd().getTime() < timeInMillis) {
                    events.remove(index);
//                    System.out.println("删除了!");
                } else {
                    index++;
                }
            }
//            for (int i = 0; i < events.size(); i ++) {
//                System.out.println("events " + events.get(i));
//            }
            Collections.sort(events, new EventInfo.SortByDistance());
            return events;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }



}
