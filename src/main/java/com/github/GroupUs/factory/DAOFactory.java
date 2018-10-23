package com.github.GroupUs.factory;

import com.github.GroupUs.dao.IUserDAO;
import com.github.GroupUs.impl.UserImpl;
import com.mongodb.client.MongoDatabase;

/*Use this Factory to return the User Interface instance
 * */
public class DAOFactory {
    public static IUserDAO getIUserDAOInstance(MongoDatabase db) {
        return new UserImpl(db);
    }
}
