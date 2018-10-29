package com.github.GroupUs.test;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.EventInfo;

import java.util.List;

import static com.github.GroupUs.Main.userId;

public class testEvent {
    EventInfo vo = new EventInfo();

    public static void main(String[] args) {
        testEvent testObj = new testEvent();
        //testObj.testInsert();
        testObj.testSearchByCategory();

    }

    public void testInsert() {
        userId = "rg3105@gmail.com";
        vo.setCategory("restaurant");
        vo.setLocation("Stanford University");
        try {
            boolean res = ServiceFactory.getIEventServiceInstance().insert(vo);
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

    public void testSearchByCategory() {
        String category = "restaurant";
        String currentLocation = "Columbia University";
        try {
            List<EventInfo> res = ServiceFactory.getIEventServiceInstance().searchByCategory(category, currentLocation);
            for (int i = 0; i < res.size(); i++) {
                System.out.println(res.get(i).getLocation());
                System.out.println(res.get(i).getUsrCurLocation());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
