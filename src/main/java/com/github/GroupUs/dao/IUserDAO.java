package com.github.GroupUs.dao;

import com.github.GroupUs.vo.UserInfo;

public interface IUserDAO {
    // add email and password into the database
    public boolean doCreate(UserInfo vo) throws Exception;
    // modify user's information
    public boolean doUpdate(UserInfo vo) throws Exception;
    // find all the information according to email
    public UserInfo findByEmail(String email) throws Exception;
}
