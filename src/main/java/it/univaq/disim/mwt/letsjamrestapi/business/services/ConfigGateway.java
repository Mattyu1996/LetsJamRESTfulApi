package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigGateway {
    
    public static String readConfig(String key){
        try {
            String filepath = new File(
                (new File((new File(".")).getCanonicalPath(), "..\\webapps\\letsjamrestapi\\WEB-INF\\")
                        .getCanonicalPath()),
                "config.properties").getCanonicalPath();
            Properties props = new Properties();
            FileInputStream f = new FileInputStream(new File(filepath));
            props.load(f);
            String value = props.getProperty(key);
            f.close();
            return value;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public static String getMongoUri(){
        return readConfig("mongo_uri");  
    }
    

    public static String getSpotifyClientId() {
        return readConfig("spotify_client_id");  
    }

    public static String getSpotifyClientSecret(){
        return readConfig("spotify_client_secret");     
    }

    public static String getGeniusClientId(){
        return readConfig("genius_client_id");
    }
    
    public static String getGeniusAccessToken(){
        return readConfig("genius_access_token");
    }

}
