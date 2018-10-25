package com.github.GroupUs.factory;

import com.github.GroupUs.impl.UserServiceImpl;
import com.github.GroupUs.service.IUserService;
import com.github.GroupUs.impl.EventServiceImpl;
import com.github.GroupUs.service.IEventService;


public class ServiceFactory {
    public static IUserService getIUserServiceInstance() {
        return new UserServiceImpl();
    }
    public static IEventService getIEventServiceInstance() {
        return new EventServiceImpl();
    }
}
