package com.github.GroupUs.dbc;

import com.github.GroupUs.factory.ServiceFactory;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseConnectionTest {
    public String databaseUrl = "mongodb://ase2018:ase2018@ds231723.mlab.com:31723/groupus";
    public MongoClientURI uri = new MongoClientURI(databaseUrl);
    public MongoClient client = new MongoClient(uri);
    public DatabaseConnection dbc = new DatabaseConnection();
    //private MongoDatabase db = client.getDatabase(uri.getDatabase());


    @Test
    public void close() {
        this.client = null;
        try {
            dbc.close();
            TestCase.assertNull(this.client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getConnection() {

    }
}