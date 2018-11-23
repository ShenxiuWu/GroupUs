package com.github.GroupUs.impl;

import com.github.GroupUs.factory.ServiceFactory;
import com.github.GroupUs.vo.EventInfo;
import com.github.GroupUs.vo.UserInfo;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.concurrent.TimeUnit;


import java.util.ArrayList;
import java.util.List;

import static com.github.GroupUs.Main.userId;
import static com.github.GroupUs.Main.databaseUrl;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EventServiceImplTest {
    EventInfo vo = new EventInfo();


    @Test
    public void insert() {
        databaseUrl = "mongodb://ase2018:ase2018@ds039027.mlab.com:39027/groupustest";
        try {
            userId = "test@columbia.edu";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSubjectExceptionMessage() {
        databaseUrl = "mongodb://ase2018:ase2018@ds039027.mlab.com:39027/groupustest";
        EventInfo vo = new EventInfo();
        vo.setSubject("@@@");
        try {
            ServiceFactory.getIEventServiceInstance().insert(vo);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (Exception anJavaLongException) {
            assertThat(anJavaLongException.getMessage(), is("The subject format cannot be special characters or too long, please check your input again!"));
        }
    }

    @Test
    public void testLocationExceptionMessage() {
        databaseUrl = "mongodb://ase2018:ase2018@ds039027.mlab.com:39027/groupustest";
        EventInfo vo = new EventInfo();
        vo.setSubject("Group Study for ASE");
        vo.setLocation("@@@");
        try {
            ServiceFactory.getIEventServiceInstance().insert(vo);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (Exception anJavaLongException) {
            assertThat(anJavaLongException.getMessage(), is("The location format cannot be special characters or too long, please check your input again!"));
        }
    }
    @Test
    public void testInvalidLocationExceptionMessage() {
        databaseUrl = "mongodb://ase2018:ase2018@ds039027.mlab.com:39027/groupustest";
        EventInfo vo = new EventInfo();
        vo.setSubject("Group Study for ASE");
        vo.setLocation("ttt");
        try {
            ServiceFactory.getIEventServiceInstance().insert(vo);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (Exception anJavaLongException) {
            assertThat(anJavaLongException.getMessage(), is("The location should be valid, please check your input again!"));
        }
    }

    @Test
    public void testMemoExceptionMessage() {
        databaseUrl = "mongodb://ase2018:ase2018@ds039027.mlab.com:39027/groupustest";
        EventInfo vo = new EventInfo();
        vo.setSubject("Group Study for ASE");
        vo.setLocation("Columbia University");
        vo.setMemo("@@@");
        try {
            ServiceFactory.getIEventServiceInstance().insert(vo);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (Exception anJavaLongException) {
            assertThat(anJavaLongException.getMessage(), is("The memo format cannot be special characters or too long, please check your input again!"));
        }
    }

    @Test
    public void testDescriptionExceptionMessage() {
        databaseUrl = "mongodb://ase2018:ase2018@ds039027.mlab.com:39027/groupustest";
        EventInfo vo = new EventInfo();
        vo.setSubject("Group Study for ASE");
        vo.setLocation("Columbia University");
        vo.setMemo("NWC 1st floor");
        vo.setDescription("@@@");
        try {
            ServiceFactory.getIEventServiceInstance().insert(vo);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (Exception anJavaLongException) {
            assertThat(anJavaLongException.getMessage(), is("The description format cannot be special characters or too long, please check your input again!"));
        }
    }

//    @Test
//    public void get() {
//        try {
//            TestCase.assertNull(ServiceFactory.getIEventServiceInstance().get("rz2390@columbia.eduSun Oct 28 17:02:50 EDT 2018"));
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void join() {
//        userId = null;
//        String eventId = null;
//        try {
//            TestCase.assertFalse(ServiceFactory.getIEventServiceInstance().join(userId, eventId));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * @parameter location
     *   @feature length
     *     @ equivalence partition：length smaller than 100 / length larger than 100
     *       @ boundary condition: length = 99, length = 100, length = 101
     *   @feature consist of 26 letters or numbers
     *     @ equivalence partition: string only contains 26 letters or numbers / string contains other special characters
     *       @ boundary condition: string with $, string with space.
     *   @feature valid/invalid locations(locations could/couldn't be found in matrixDistance class)
     *     @ equivalence partition: string enables method distanceCheck return true/false
     *       @ boundary condition: string = null, string = test...
     */
    @Test
    public void searchByCategory() {
        String category = "Study";
        String location = "Columbia University";
        try {
            List<EventInfo> res = ServiceFactory.getIEventServiceInstance().searchByCategory(category, location);
            TestCase.assertNotNull(res);
            location = "Columbia University $";
            res = ServiceFactory.getIEventServiceInstance().searchByCategory(category, location);
            TestCase.assertNull(res);
            location = "Time square Long Term Building Office Apartment 200"; //length = 51
            res = ServiceFactory.getIEventServiceInstance().searchByCategory(category, location);
            TestCase.assertNull(res);
            location = "Time square Long Term Building Office Apartment 20"; //length = 50
            res = ServiceFactory.getIEventServiceInstance().searchByCategory(category, location);
            TestCase.assertNotNull(res);
            location = "Time square Long Term Building Office Apartment 2"; //length = 49
            res = ServiceFactory.getIEventServiceInstance().searchByCategory(category, location);
            TestCase.assertNotNull(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



