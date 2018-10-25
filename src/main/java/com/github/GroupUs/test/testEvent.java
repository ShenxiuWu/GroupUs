package com.github.GroupUs.test;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.EventInfo;

public class testEvent {
    EventInfo vo = new EventInfo();

    public static void main(String[] args) {
        testEvent testObj = new testEvent();
        testObj.testInsert();
        testObj.testFindByEventId();
    }

    public void testInsert() {
        String eventId = "testEventId";
        vo.setEventId(eventId);
        try {
            boolean res = ServiceFactory.getIEventServiceInstance().insert(vo);
            System.out.println(res);
        } catch (Exception e) { }
    }

    public void testFindByEventId() {
        String eventId = "testEventId";
        try {
            EventInfo res = ServiceFactory.getIEventServiceInstance().get(eventId);
            System.out.println(res.getEventId());
        } catch (Exception e) { }
    }
}
