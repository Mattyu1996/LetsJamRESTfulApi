package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;

public class ConfigGateway {

    private static String readConfig(String key) throws ApiException {
        try {
            String appName = "letsjamrestapi";        
            Path basePath = Paths.get((new File(".")).getCanonicalPath());
            String filePath = basePath.toString().contains("bin") 
                                ? (new File(Paths.get(basePath.toString(),"..\\","webapps", appName, "WEB-INF", "config.properties").toString())).getCanonicalPath()
                                : (new File(Paths.get(basePath.toString(),"webapps", appName, "WEB-INF", "config.properties").toString())).getCanonicalPath();

            Properties props = new Properties();
            FileInputStream f = new FileInputStream(new File(filePath));
            props.load(f);
            String value = props.getProperty(key);
            f.close();
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
    }

    public static String getMongoUri() throws ApiException {
        return readConfig("mongo_uri");
    }

    public static String getSpotifyClientId() throws ApiException {
        return readConfig("spotify_client_id");
    }

    public static String getSpotifyClientSecret() throws ApiException {
        return readConfig("spotify_client_secret");
    }

    public static String getGeniusClientId() throws ApiException {
        return readConfig("genius_client_id");
    }

    public static String getGeniusAccessToken() throws ApiException {
        return readConfig("genius_access_token");
    }

}
