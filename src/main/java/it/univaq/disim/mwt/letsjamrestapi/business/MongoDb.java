package it.univaq.disim.mwt.letsjamrestapi.business;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDb {

    public static final String DBNAME = "letsjam";

    public static MongoClient getConnection() {
        try {
            String filepath = new File(
                    (new File((new File(".")).getCanonicalPath(), "..\\webapps\\letsjamrestapi\\WEB-INF\\")
                            .getCanonicalPath()),
                    "mongoConfig.properties").getCanonicalPath();
            Properties props = new Properties();
            props.load(new FileInputStream(new File(filepath)));
            String uri = props.getProperty("uri");
            MongoClient client = new MongoClient(new MongoClientURI(uri));
            return client;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
