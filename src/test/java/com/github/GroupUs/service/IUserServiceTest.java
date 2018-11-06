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

public class IUserServiceTest {

    @Test
    public void insert() {
        UserInfo vo = new UserInfo();
        List<String> j = new ArrayList<String>();
        j.add("xx");
        j.add("yy");
        List<String> p = new ArrayList<String>();
        p.add("zz");
        p.add("xx");
        vo.setEmail("hello123@gmail.com");
        vo.setPassword("999");
        vo.setPosted(j);
        vo.setJoined(p);
        vo.setName("Group study");
        try {
            TestCase.assertFalse(ServiceFactory.getIUserServiceInstance().insert(vo));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        UserInfo vo = new UserInfo();
        List<String> j = new ArrayList<String>();
        j.add("xx");
        j.add("yy");
        List<String> p = new ArrayList<String>();
        p.add("zz");
        p.add("xx");
        vo.setEmail("hello123@gmail.com");
        vo.setPassword("123456");
        vo.setPosted(j);
        vo.setJoined(p);
        vo.setName("Group study in mudd");
        try {
            TestCase.assertFalse(ServiceFactory.getIUserServiceInstance().update(vo));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        UserInfo vo = new UserInfo();
        List<String> j = new ArrayList<String>();
        j.add("xx");
        j.add("yy");
        List<String> p = new ArrayList<String>();
        p.add("zz");
        p.add("xx");
        vo.setEmail("hello123@gmail.com");
        vo.setPassword("123456");
        vo.setPosted(j);
        vo.setJoined(p);
        vo.setName("Group");
        try {
            TestCase.assertNotNull(ServiceFactory.getIUserServiceInstance().get(vo.getEmail()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetJoinedEvent() {
        userId = "rz2390@columbia.edu";
        try {
            TestCase.assertNotNull(ServiceFactory.getIUserServiceInstance().getJoinedEvent(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPostedEvent() {
        userId = "rz2390@columbia.edu";
        try {
            TestCase.assertNotNull(ServiceFactory.getIUserServiceInstance().getPostedEvent(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}