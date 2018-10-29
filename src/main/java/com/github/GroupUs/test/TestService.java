package com.github.GroupUs.test;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.UserInfo;

public class TestService {


    public static void main(String[] args) {
        UserInfo vo = new UserInfo();
        vo.setEmail("ruige915@gmail.com");
        vo.setPassword("0000");
        try {
            ServiceFactory.getIUserServiceInstance().update(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
