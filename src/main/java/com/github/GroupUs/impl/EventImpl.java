package com.github.GroupUs.impl;

import com.github.GroupUs.vo.*;
import com.github.GroupUs.dao.*;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventImpl implements IEventDAO{
    private MongoCollection col;
    public EventImpl(MongoDatabase db) {
        this.col = db.getCollection("EventInfo");
    }
    /*
     * */
    @Override
    public boolean doCreate(EventInfo vo) throws Exception {
        Document doc = new Document();
        doc.append("eventId", vo.getEventId());
        doc.append("creator",vo.getCreator());
        doc.append("category", vo.getCategory());
        doc.append("subject", vo.getSubject());
        doc.append("description", vo.getDescription());
        doc.append("start", vo.getStart());
        doc.append("end", vo.getEnd());
        doc.append("location", vo.getLocation());
        doc.append("memo", vo.getMemo());
        doc.append("createdAt", vo.getCreatedAt());
        doc.append("modifiedAt", vo.getModifiedAt());
        this.col.insertOne(doc);
        return true;
    }

    @Override
    public EventInfo findByEventId(String eventId) throws Exception {
        EventInfo vo = null;
        BasicDBObject cond = new BasicDBObject();
        cond.put("eventId", eventId);
        MongoCursor<Document> cursor = this.col.find(cond).iterator();
        while (cursor.hasNext()) {
            vo = new EventInfo();
            Document doc = cursor.next();
            vo.setEventId(doc.getString("eventId"));
            vo.setCategory(doc.getString("category"));
            vo.setCreatedAt((Date)doc.get("createdAt"));
            vo.setCreator(doc.getString("creator"));
            vo.setModifiedAt((Date)doc.get("modifiedAt"));
            vo.setDescription(doc.getString("description"));
            vo.setStart((Date)doc.get("start"));
            vo.setEnd((Date)doc.get("end"));
            vo.setLocation(doc.getString("location"));
            vo.setSubject(doc.getString("subject"));
            vo.setMemo(doc.getString("memo"));
        }
        return vo;
    }

    @Override
    public List<EventInfo> findByCategory(String category, String currentLocation) throws Exception {
        BasicDBObject condA = new BasicDBObject("category", category);
        BasicDBObject condB = new BasicDBObject("$set", new BasicDBObject("usrCurLocation", currentLocation));
        this.col.updateMany(condA, condB);
        EventInfo vo = null;
        BasicDBObject cond = new BasicDBObject();
        List<Document> docs = new ArrayList<Document>();
        List<EventInfo> events = new ArrayList<EventInfo>();
        cond.put("category", category);
        MongoCursor<Document> cursor = this.col.find(cond).iterator();
        while (cursor.hasNext()) {
            docs.add(cursor.next());
        }
        for (int i = 0; i < docs.size(); i ++) {
            vo = new EventInfo();
            vo.setEventId(docs.get(i).getString("eventId"));
            vo.setCategory(docs.get(i).getString("category"));
            vo.setCreatedAt((Date)docs.get(i).get("createdAt"));
            vo.setCreator(docs.get(i).getString("creator"));
            vo.setModifiedAt((Date)docs.get(i).get("modifiedAt"));
            vo.setStart((Date)docs.get(i).get("start"));
            vo.setEnd((Date)docs.get(i).get("end"));
            vo.setLocation(docs.get(i).getString("location"));
            vo.setUsrCurLocation(docs.get(i).getString("usrCurLocation"));
            vo.setSubject(docs.get(i).getString("subject"));
            vo.setMemo(docs.get(i).getString("memo"));
            events.add(vo);
        }
        return events;
    }
}
