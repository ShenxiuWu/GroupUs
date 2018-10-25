package com.github.GroupUs.impl;

import com.github.GroupUs.vo.*;
import com.github.GroupUs.dao.*;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

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
        doc.append("geo", vo.getGeo());
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
            vo.setGeo(doc.getString("geo"));
            vo.setSubject(doc.getString("subject"));
            vo.setMemo(doc.getString("memo"));
        }
        return vo;
    }
}
