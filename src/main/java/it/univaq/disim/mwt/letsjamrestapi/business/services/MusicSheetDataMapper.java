package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import org.bson.Document;

import de.undercouch.bson4jackson.BsonFactory;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheetData;

public class MusicSheetDataMapper {

    public static Document serialize(MusicSheetData data, BigDecimal musicsheetId){
        Document d = new Document();
        d.append("_id", musicsheetId.toString());
        d.append("content", data.getContent());
        d.append("instrumentMapping", new BasicDBObject(data.getInstrumentMapping()));
        return d;
    }

    public static MusicSheetData deserialize(Document d){
        MusicSheetData data = new MusicSheetData();
        try {
            data.setContent(d.getString("content").toString());
            Map<String, String> map = new ObjectMapper().readValue(((Document) d.get("instrumentMapping")).toJson(), HashMap.class);
            data.setInstrumentMapping(map);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }
}
