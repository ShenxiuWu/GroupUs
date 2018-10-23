package com.github.GroupUs.test;



import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class TestService {
    public static void main(String[] args) {
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
        try {
            ServiceFactory.getIUserServiceInstance().update(vo);
            System.out.println(ServiceFactory.getIUserServiceInstance().get("rg3105@gmail.com").getEmail());
            System.out.println(ServiceFactory.getIUserServiceInstance().get("rg3105@gmail.com").getPassword());
            System.out.println(ServiceFactory.getIUserServiceInstance().get("rg3105@gmail.com").getJoined());
            System.out.println(ServiceFactory.getIUserServiceInstance().get("rg3105@gmail.com").getPosted());
            System.out.println(ServiceFactory.getIUserServiceInstance().get("rg3105@gmail.com").getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
