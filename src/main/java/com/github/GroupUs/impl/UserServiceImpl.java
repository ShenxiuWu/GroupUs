package com.github.GroupUs.impl;


import com.github.GroupUs.dbc.DatabaseConnection;
import com.github.GroupUs.factory.DAOFactory;
import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.service.IUserService;
import com.github.GroupUs.vo.EventInfo;
import com.github.GroupUs.vo.UserInfo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserServiceImpl implements IUserService {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean insert(UserInfo vo) throws Exception {
        Logger logger = Logger.getLogger("com.github.GroupUs.impl");
        try {
            // TODO: check username(email), password, name
            String emailPattern = "^[a-zA-Z]{2,3}\\d{4}@columbia.edu$";
            String namePattern = "^[a-zA-Z][a-zA-Z0-9_%&',;=?]{0,19}$"; //input name must start with character, cannot be null and no more than 20 bytes.
            String passwordPattern = "^[a-zA-Z]\\w{5,17}$"; //input password must start with letters, the length is between 6-18, and can only contain characters, numbers and underlines.
            if (!Pattern.matches(emailPattern, vo.getEmail())){
                throw new Exception("The email format can only be ab1234/abc1234@columbia.edu, please check your input again!");
            }
            else if (!Pattern.matches(namePattern, vo.getName())){
                throw new Exception("The input name should start with character, cannot be null and no more than 20 bytes, with the following special characters _%&',;=?");
            }
            else if (!Pattern.matches(passwordPattern, vo.getPassword())){
                throw new Exception("The password must start with letters, the length is between 6-18, and can only contain characters, numbers and underlines.");
            }

            if (DAOFactory.getIUserDAOInstance(this.dbc.getConnection()).findByEmail(vo.getEmail()) == null) {
                return DAOFactory.getIUserDAOInstance(this.dbc.getConnection()).doCreate(vo);
            }else {
                throw new Exception("The email already have been signed up, please try to sign it in instead");
            }
        } catch (Exception e) {
            logger.error("Some error happened when insert, the message is: " + e);
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
        // TODO: check username(email)

        try {
            return DAOFactory.getIUserDAOInstance(this.dbc.getConnection()).findByEmail(email);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public List<EventInfo> getJoinedEvent(String email) throws Exception {
        try {
            UserInfo user = get(email);
            List<String> userJoined = user.getJoined();
            List<EventInfo> joinedEvents = new ArrayList<>();
            for (int i = 0; i < userJoined.size(); i++) {
                String eventId = userJoined.get(i);
                EventInfo event = ServiceFactory.getIEventServiceInstance().get(eventId);
                joinedEvents.add(event);
            }
            return joinedEvents;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public List<EventInfo> getPostedEvent(String email) throws Exception {
        try {
            UserInfo user = get(email);
            List<String> userPosted = user.getPosted();
            List<EventInfo> postedEvents = new ArrayList<>();
            for (int i = 0; i < userPosted.size(); i++) {
                String eventId = userPosted.get(i);
                EventInfo event = ServiceFactory.getIEventServiceInstance().get(eventId);
                postedEvents.add(event);
            }
            return postedEvents;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
