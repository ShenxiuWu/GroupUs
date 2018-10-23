package com.github.GroupUs.factory;

import com.github.GroupUs.impl.UserServiceImpl;
import com.github.GroupUs.service.IUserService;


public class ServiceFactory {
    public static IUserService getIUserServiceInstance() {
        return new UserServiceImpl();
    }
}
