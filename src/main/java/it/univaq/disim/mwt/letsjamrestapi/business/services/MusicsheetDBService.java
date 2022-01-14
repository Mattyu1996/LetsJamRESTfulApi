package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import it.univaq.disim.mwt.letsjamrestapi.business.MongoDb;
import it.univaq.disim.mwt.letsjamrestapi.business.SqlDb;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheet;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheetData;

public class MusicsheetDBService {
    
    public static MusicSheet makeMusicsheet(ResultSet rs) {
        MusicSheet m = new MusicSheet();
        try {
            m.setId(new BigDecimal(rs.getLong("id")));
            m.setTitle(rs.getString("title"));
            m.setAuthor(rs.getString("author"));
            m.setCreateDateTime(rs.getDate("create_date_time"));
            m.setUpdateDateTime(rs.getDate("update_date_time"));
            m.setHasTablature(rs.getBoolean("has_tablature"));
            m.setRearranged(rs.getBoolean("rearranged"));
            m.setVerified(rs.getBoolean("verified"));
            m.setVisibility(rs.getBoolean("visibility"));
            m.setLikes(BigDecimal.valueOf(rs.getLong("likes")));
            m.setUser(UserDBService.getUserById(BigDecimal.valueOf(rs.getLong("user_id"))).get(0));
            m.setSong(SongDBService.getSongById(BigDecimal.valueOf(rs.getLong("song_id"))));
            m.setInstruments(InstrumentDBService.getMusicsheetInstruments(m.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    public static MusicSheet getMusicsheetById(BigDecimal musicsheetId){
        Connection c = SqlDb.getConnection();
        try {
            String query = "SELECT *, (SELECT COUNT(music_sheet_id) FROM spartiti_likes WHERE music_sheet_id = ?) as likes FROM spartiti WHERE id = ?";
            PreparedStatement st = c.prepareStatement(query);
            st.setLong(1, musicsheetId.longValue());
            st.setLong(2, musicsheetId.longValue());
            ResultSet rs = st.executeQuery();
            try {
                if (rs.next()) {
                    return makeMusicsheet(rs);
                }
            } finally {
                rs.close();
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<MusicSheet> searchMusicSheets(
        String search, String sortBy, 
        String sortDirection, List<String> genres, 
        List<String> instruments, Boolean verified, 
        Boolean rearranged, Boolean tablature, BigDecimal pageNumber, BigDecimal pageSize){
        
        Connection c = SqlDb.getConnection();
        String q = "SELECT *, (SELECT COUNT(music_sheet_id) FROM spartiti_likes WHERE music_sheet_id = spartiti.id) as likes FROM spartiti JOIN brani ON song_id = brani.id JOIN generi ON brani.genre_id = generi.id ";
		if(!instruments.isEmpty()) q+="JOIN spartiti_strumenti ON music_sheet_id = spartiti.id JOIN strumenti ON instrument_id = strumenti.id ";
		boolean whereClause = false;
		
		//SEARCH STRING
		if(search != null && search.length() > 0){
			q+="WHERE (spartiti.title LIKE CONCAT('%',?,'%') OR spartiti.author LIKE CONCAT('%',?,'%') OR brani.title LIKE CONCAT('%',?,'%') OR brani.author LIKE CONCAT('%',?,'%')) ";
			whereClause = true;
		}
		//GENERES
		if(!genres.isEmpty() && !whereClause){
            q += "WHERE generi.name in (";
            for(int i=0; i < genres.size(); i++){
                q+=(i==genres.size()-1) ? "?" : "?,";
            }
			q+=") ";
			whereClause = true;
		}
		else if(!genres.isEmpty() && whereClause){
			q += "AND generi.name in (";
            for(int i=0; i < genres.size(); i++){
                q+=(i==genres.size()-1) ? "?" : "?,";
            }
			q+=") ";
		}

		//INSTRUMENTS
		if(!instruments.isEmpty() && !whereClause){
            q += "WHERE strumenti.name in (";
            for(int i=0; i < instruments.size(); i++){
                q+=(i==instruments.size()-1) ? "?" : "?,";
            }
			q+=") ";
			whereClause = true;
		}else if (!instruments.isEmpty() && whereClause){
			q += "AND strumenti.name in (";
            for(int i=0; i < instruments.size(); i++){
                q+=(i==instruments.size()-1) ? "?" : "?,";
            }
			q+=") ";
		}

		//VERIFIED
		if(verified != null && verified && !whereClause){
			q += "WHERE verified = ? ";
			whereClause = true;
		}else if(verified != null && verified && whereClause){
			q += "AND verified = ? ";
		}
		//REARRANGED
		if(rearranged != null && rearranged && !whereClause){
			q += "WHERE rearranged = ? ";
			whereClause = true;
		}else if(rearranged != null && rearranged && whereClause){
			q += "AND rearranged = ? ";
		}
        //HAS TABLATURE
		if(tablature != null && tablature && !whereClause){
			q += "WHERE has_tablature = ? ";
			whereClause = true;
		}else if(tablature != null && tablature && whereClause){
			q += "AND has_tablature = ? ";
		}
		//ORDER BY
		if (sortBy != null){
			q += "ORDER BY "+sortBy;
		}
        //SORT DIRECTION
        if (sortDirection != null){
			q += " "+sortDirection+" ";
		}
        //PAGESIZE PAGENUMBER
        if(pageSize != null && pageNumber != null){
            q+="LIMIT ? OFFSET ? ";
        }
        		
		try {
            PreparedStatement stmt = c.prepareStatement(q);
            int indexCounter = 0;
            if(search != null && search.length() > 0) {
                for(int i=0; i < 4; i++) stmt.setString(++indexCounter, search);
            }
            if(!genres.isEmpty()) {
                for(int i=0; i < genres.size(); i++) stmt.setString(++indexCounter, genres.get(i));
            }
            if(!instruments.isEmpty()){
                for(int i=0; i < instruments.size(); i++) stmt.setString(++indexCounter, instruments.get(i));
            }
            if(verified != null && verified) stmt.setBoolean(++indexCounter, verified);
            if(rearranged != null && rearranged) stmt.setBoolean(++indexCounter, rearranged);
            if(tablature != null && tablature) stmt.setBoolean(++indexCounter, tablature);
            if(pageSize != null && pageNumber != null){
                stmt.setLong(++indexCounter, pageSize.longValue());
                stmt.setLong(++indexCounter, pageSize.longValue()*pageNumber.longValue());
            }

            List<MusicSheet> spartiti = new ArrayList<MusicSheet>();
            ResultSet rs = stmt.executeQuery();
            try {
                while (rs.next()) {
                    spartiti.add(makeMusicsheet(rs));
                }
                return spartiti;
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

    public static MusicSheetData getMusicsheetData(BigDecimal musicsheetId){
        MongoClient conn = MongoDb.getConnection();
        MongoCollection<Document> collection = conn.getDatabase(MongoDb.DBNAME).getCollection("spartiti");
        BasicDBObject query = new BasicDBObject();
        query.put("_id", musicsheetId.toString());
        Document rs = collection.find(query).first();
        MusicSheetData result = new MusicSheetData();
        result.setContent(rs.getString("content").toString());
        Document mapping = (Document) rs.get("instrumentMapping");
        try {
            Map<String, String> map =  new ObjectMapper().readValue(mapping.toJson(), HashMap.class);
            result.setInstrumentMapping(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        conn.close();
        return result;
    }
}
