package it.univaq.disim.mwt.letsjamrestapi.business;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import it.univaq.disim.mwt.letsjamrestapi.business.services.ConfigGateway;

public class MongoDb {

    public static final String DBNAME = "letsjam";

    public static MongoClient getConnection() {
        try {
            MongoClient client = new MongoClient(new MongoClientURI(ConfigGateway.getMongoUri()));
            return client;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
