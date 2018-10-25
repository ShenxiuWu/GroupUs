package com.github.GroupUs.impl;

import com.github.GroupUs.vo.*;
import com.github.GroupUs.dao.*;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.List;

public class UserImpl implements IUserDAO{
    private MongoCollection col;
    public UserImpl(MongoDatabase db) {
        this.col = db.getCollection("UserInfo");
    }
    /*
     * */
    @Override
    public boolean doCreate(UserInfo vo) throws Exception {
        Document doc = new Document();
        doc.append("email", vo.getEmail());
        doc.append("password",vo.getPassword());
        doc.append("joined", vo.getJoined());
        doc.append("posted", vo.getPosted());
        doc.append("name", vo.getName());
        this.col.insertOne(doc);
        return true;
    }

    @Override
    public boolean doUpdate(UserInfo vo) throws Exception {
        BasicDBObject condA = new BasicDBObject("email", vo.getEmail());
        BasicDBObject condB = new BasicDBObject("$set", new BasicDBObject("password", vo.getPassword()));
        BasicDBObject condC = new BasicDBObject("$set", new BasicDBObject("joined", vo.getJoined()));
        BasicDBObject condD = new BasicDBObject("$set", new BasicDBObject("posted", vo.getPosted()));
        BasicDBObject condE = new BasicDBObject("$set", new BasicDBObject("name", vo.getName()));
        UpdateResult resultB = this.col.updateMany(condA, condB);
        UpdateResult resultC = this.col.updateMany(condA, condC);
        UpdateResult resultD = this.col.updateMany(condA, condD);
        UpdateResult resultE = this.col.updateMany(condA, condE);
        return (resultB.getModifiedCount() == 1) || (resultC.getModifiedCount() == 1) ||
                (resultD.getModifiedCount() == 1) || (resultE.getModifiedCount() == 1);
    }

    @Override
    public UserInfo findByEmail(String email) throws Exception {
        UserInfo vo = null;
        BasicDBObject cond = new BasicDBObject();
        cond.put("email", email);
        MongoCursor<Document> cursor = this.col.find(cond).iterator();
        while (cursor.hasNext()) {
            vo = new UserInfo();
            Document doc = cursor.next();
            vo.setEmail(doc.getString("email"));
            vo.setPassword(doc.getString("password"));
            vo.setJoined((List<String>)doc.get("joined"));
            vo.setPosted((List<String>)doc.get("posted"));
            vo.setName(doc.getString("name"));
        }
        return vo;
    }
}
