package com.github.GroupUs.service;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.UserInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IUserServiceTest {

    @Test
    public void insert() {

        UserInfo vo = new UserInfo();
        List<Integer> j = new ArrayList<Integer>();
        j.add(1);
        j.add(2);
        List<Integer> p = new ArrayList<Integer>();
        p.add(3);
        p.add(4);
        vo.setEmail("rg3105@gmail.com");
        vo.setPassword("999");
        vo.setPosted(j);
        vo.setJoined(p);
        vo.setName("Group study");
        ServiceFactory.getIUserServiceInstance().insert(vo);
    }

    @Test
    public void update() {
    }

    @Test
    public void get() {
    }
}