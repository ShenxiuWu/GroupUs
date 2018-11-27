package com.github.GroupUs.impl;

import com.github.GroupUs.dbc.DatabaseConnection;
import com.github.GroupUs.factory.DAOFactory;
import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.EventInfo;
import com.github.GroupUs.vo.UserInfo;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.GroupUs.Main.databaseUrl;
import static com.github.GroupUs.Main.userId;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserServiceImplTest {



    @Test
    public void insert() {
        databaseUrl = "mongodb://ase2018:ase2018@ds039027.mlab.com:39027/groupustest";
        UserInfo vo = new UserInfo();
        List<String> j = new ArrayList<String>();
        j.add("xx");
        j.add("yy");
        List<String> p = new ArrayList<String>();
        p.add("zz");
        p.add("xx");
        vo.setEmail("rz2393@columbia.edu");
        vo.setName("abcd999");
        vo.setPassword("abcd999");
        vo.setPosted(j);
        vo.setJoined(p);
        //vo.setName("Group study");
        try {
            TestCase.assertTrue(ServiceFactory.getIUserServiceInstance().insert(vo));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testEmailExceptionMessage() {
        databaseUrl = "mongodb://ase2018:ase2018@ds039027.mlab.com:39027/groupustest";
        UserInfo vo = new UserInfo();
        vo.setEmail("123");
        try {
            ServiceFactory.getIUserServiceInstance().insert(vo);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (Exception anJavaLongException) {
            assertThat(anJavaLongException.getMessage(), is("The email format can only be ab1234/abc1234@columbia.edu, please check your input again!"));
        }
    }

    @Test
    public void testNameExceptionMessage() {
        databaseUrl = "mongodb://ase2018:ase2018@ds039027.mlab.com:39027/groupustest";
        UserInfo vo = new UserInfo();
        vo.setEmail("rz2390@columbia.edu");
        vo.setName("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
        try {
            ServiceFactory.getIUserServiceInstance().insert(vo);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (Exception anJavaLongException) {
            assertThat(anJavaLongException.getMessage(), is("The input name should start with character, cannot be null and no more than 20 bytes, with the following special characters _%&',;=?"));
        }
    }

    @Test
    public void testPasswordExceptionMessage() {
        databaseUrl = "mongodb://ase2018:ase2018@ds039027.mlab.com:39027/groupustest";
        UserInfo vo = new UserInfo();
        vo.setEmail("rz2390@columbia.edu");
        vo.setName("testName");
        vo.setPassword("123abcde");
        try {
            ServiceFactory.getIUserServiceInstance().insert(vo);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (Exception anJavaLongException) {
            assertThat(anJavaLongException.getMessage(), is("The password must start with letters, the length is between 6-18, and can only contain characters, numbers and underlines."));
        }
    }

    @Test
    public void testFindByEmailNullExceptionMessage() {
        databaseUrl = "mongodb://ase2018:ase2018@ds039027.mlab.com:39027/groupustest";
        DatabaseConnection dbc = new DatabaseConnection();
        UserInfo vo = new UserInfo();
        vo.setEmail("rz2390@columbia.edu");
        vo.setName("abcd999");
        vo.setPassword("abcd999");
        try {
            //DAOFactory.getIUserDAOInstance(dbc.getConnection()).findByEmail("hello123@gmail.com" );
            ServiceFactory.getIUserServiceInstance().insert(vo);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (Exception anJavaLongException) {
            assertThat(anJavaLongException.getMessage(), is("The email already have been signed up, please try to sign it in instead"));
        }

    }



    @Test
    public void update() {
        UserInfo vo = new UserInfo();
        List<String> j = new ArrayList<String>();
        j.add("xx");
        j.add("yy");
        List<String> p = new ArrayList<String>();
        p.add("zz");
        p.add("xx");
        vo.setEmail("hello123@gmail.com");
        vo.setPassword("123456");
        vo.setPosted(j);
        vo.setJoined(p);
        vo.setName("Group study in mudd");
        try {
            TestCase.assertFalse(ServiceFactory.getIUserServiceInstance().update(vo));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        UserInfo vo = new UserInfo();
        List<String> j = new ArrayList<String>();
        j.add("xx");
        j.add("yy");
        List<String> p = new ArrayList<String>();
        p.add("zz");
        p.add("xx");
        vo.setEmail("hello123@gmail.com");
        vo.setPassword("123456");
        vo.setPosted(j);
        vo.setJoined(p);
        vo.setName("Group");
        try {
            TestCase.assertNotNull(ServiceFactory.getIUserServiceInstance().get(vo.getEmail()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetJoinedEvent() {
        userId = "rz2390@columbia.edu";
        try {
            TestCase.assertNotNull(ServiceFactory.getIUserServiceInstance().getJoinedEvent(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPostedEvent() {
        userId = "rz2390@columbia.edu";
        try {
            TestCase.assertNotNull(ServiceFactory.getIUserServiceInstance().getPostedEvent(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}