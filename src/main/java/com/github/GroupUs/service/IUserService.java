package com.github.GroupUs.service;

import com.github.GroupUs.vo.EventInfo;
import com.github.GroupUs.vo.UserInfo;

import java.util.List;

public interface IUserService {
    /**
     * <li>call IUserDAO.findByEmail() to check if this inserted data exists</li>
     * <li>call IUserDAO.doCreate() if not exists</li>
     * <li>return false if exists</li>
     * @param vo is the object to be inserted
     * @return return true if saved successfully, return false if saved unsuccessfully
     * @throws Exception
     */
    public boolean insert(UserInfo vo) throws Exception;

    /**
     * call IUserDAO.doUpdate() to modify user information in all fields
     * @param vo is used to modify the database information
     * @return return true if update successfully or return false
     * @throws Exception
     */
    public boolean update(UserInfo vo) throws Exception;

    /**
     * <LI>call IUserDAO.findByEmail() to get user information in all fields</li>
     * @param email is the email to be searched
     * @return return vo if finds successfully or return null
     * @throws Exception
     */
    public UserInfo get(String email) throws Exception;

    public List<EventInfo> getPostedEvent(String email) throws Exception;
    public List<EventInfo> getJoinedEvent(String email) throws Exception;

}
