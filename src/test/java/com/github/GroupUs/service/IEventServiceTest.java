package com.github.GroupUs.service;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.EventInfo;
import com.github.GroupUs.vo.UserInfo;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.GroupUs.Main.userId;
import static org.junit.Assert.*;

public class IEventServiceTest {
    EventInfo vo = new EventInfo();

    @Test
    public void insert() {
        userId = null;
        try {
            TestCase.assertFalse(ServiceFactory.getIEventServiceInstance().insert(vo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        try {
            TestCase.assertNull(ServiceFactory.getIEventServiceInstance().get("rz2390@columbia.eduSun Oct 28 17:02:50 EDT 2018"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void join() {
        userId = null;
        String eventId = null;
        try {
            TestCase.assertFalse(ServiceFactory.getIEventServiceInstance().join(userId, eventId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchByCategory() {
        String category = "Study";
        String location = "Columbia University";
        try {
            List<EventInfo> res = ServiceFactory.getIEventServiceInstance().searchByCategory(category, location);
            System.out.println(res);
            TestCase.assertNotNull(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



