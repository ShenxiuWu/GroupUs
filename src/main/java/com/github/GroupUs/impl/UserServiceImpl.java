package com.github.GroupUs.impl;


import com.github.GroupUs.dbc.DatabaseConnection;
import com.github.GroupUs.factory.DAOFactory;
import com.github.GroupUs.service.IUserService;
import com.github.GroupUs.vo.UserInfo;

public class UserServiceImpl implements IUserService {
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(UserInfo vo) throws Exception {
        try {
            if (DAOFactory.getIUserDAOInstance(this.dbc.getConnection()).findByEmail(vo.getEmail()) == null) {
                return DAOFactory.getIUserDAOInstance(this.dbc.getConnection()).doCreate(vo);
            }
            return false;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(UserInfo vo) throws Exception {
        try {
            return DAOFactory.getIUserDAOInstance(this.dbc.getConnection()).doUpdate(vo);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public UserInfo get(String email) throws Exception {
        try {
            return DAOFactory.getIUserDAOInstance(this.dbc.getConnection()).findByEmail(email);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
