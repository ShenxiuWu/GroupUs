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
            TestCase.assertNotNull(ServiceFactory.getIEventServiceInstance().get("rz2390@columbia.eduSun Oct 28 17:02:50 EDT 2018"));
            vo = ServiceFactory.getIEventServiceInstance().get("rz2390@columbia.eduSun Oct 28 17:02:50 EDT 2018");
            TestCase.assertEquals("testSubject", vo.getSubject());
            TestCase.assertEquals("rz2390@columbia.edu", vo.getCreator());
            TestCase.assertEquals("testMemo", vo.getMemo());
            TestCase.assertEquals("testDescription", vo.getDescription());
            TestCase.assertEquals("Study", vo.getCategory());
            TestCase.assertEquals(null, vo.getGeo());
            TestCase.assertEquals(vo.getStart(), vo.getStart());
            TestCase.assertEquals(vo.getEnd(), vo.getEnd());
            TestCase.assertEquals(vo.getCreatedAt(), vo.getCreatedAt());
            TestCase.assertEquals(vo.getModifiedAt(), vo.getModifiedAt());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void join() {
        userId = "rz2390@columbia.edu";
        String eventId = "rz2390@columbia.eduSun Oct 28 14:07:06 EDT 2018";
        try {
            TestCase.assertFalse(ServiceFactory.getIEventServiceInstance().join(userId, eventId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



