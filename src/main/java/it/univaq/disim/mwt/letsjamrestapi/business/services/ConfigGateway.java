package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigGateway {

    public static String getMongoUri(){
        try {
            String filepath = new File(
                    (new File((new File(".")).getCanonicalPath(), "..\\webapps\\letsjamrestapi\\WEB-INF\\")
                            .getCanonicalPath()),
                    "config.properties").getCanonicalPath();
            Properties props = new Properties();
            FileInputStream f = new FileInputStream(new File(filepath));
            props.load(f);
            String value = props.getProperty("mongo_uri");
            f.close();        
            return value;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    

    public static String getSpotifyClientId() {
        try {
            String filepath = new File(
                    (new File((new File(".")).getCanonicalPath(), "..\\webapps\\letsjamrestapi\\WEB-INF\\")
                            .getCanonicalPath()),
                    "config.properties").getCanonicalPath();
            Properties props = new Properties();
            FileInputStream f = new FileInputStream(new File(filepath));
            props.load(f);
            String value = props.getProperty("spotify_client_id");
            f.close();        
            return value;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String getSpotifyClientSecret(){
        try {
            String filepath = new File(
                    (new File((new File(".")).getCanonicalPath(), "..\\webapps\\letsjamrestapi\\WEB-INF\\")
                            .getCanonicalPath()),
                    "config.properties").getCanonicalPath();
            Properties props = new Properties();
            FileInputStream f = new FileInputStream(new File(filepath));
            props.load(f);
            String value = props.getProperty("spotify_client_secret");
            f.close();        
            return value;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
        return null;      
    }
    
}
