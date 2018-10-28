package com.github.GroupUs.test;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.EventInfo;

import java.util.List;

import static com.github.GroupUs.Main.userId;

public class testMethod {
    EventInfo vo = new EventInfo();

    public static void main(String[] args) {
        testMethod testObj = new testMethod();
        // testObj.testInsert();
        // testObj.testJoin();
        // testObj.testFindByEventId();
        testObj.testGetJoinedEvent();
        testObj.testGetPostedEvent();
    }

    public void testInsert() {
        userId = "rz2390@columbia.edu";
        try {
            boolean res = ServiceFactory.getIEventServiceInstance().insert(vo);
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testJoin() {
        userId = "rz2390@columbia.edu";
        String eventId = "rz2390@columbia.eduSun Oct 28 14:07:06 EDT 2018";
        try {
            boolean res = ServiceFactory.getIEventServiceInstance().join(userId, eventId);
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testFindByEventId() {
        String eventId = "testEventId";
        try {
            EventInfo res = ServiceFactory.getIEventServiceInstance().get(eventId);
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testGetJoinedEvent() {
        userId = "rz2390@columbia.edu";
        try {
            List<EventInfo> res = ServiceFactory.getIUserServiceInstance().getJoinedEvent(userId);
            for (int i = 0; i < res.size(); i++) {
                EventInfo event = res.get(i);
                System.out.println(event.getEventId());
            }
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testGetPostedEvent() {
        userId = "rz2390@columbia.edu";
        try {
            List<EventInfo> res = ServiceFactory.getIUserServiceInstance().getPostedEvent(userId);
            for (int i = 0; i < res.size(); i++) {
                EventInfo event = res.get(i);
                System.out.println(event.getEventId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
