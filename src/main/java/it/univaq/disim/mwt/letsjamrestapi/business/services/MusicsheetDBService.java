package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import it.univaq.disim.mwt.letsjamrestapi.business.MongoDb;
import it.univaq.disim.mwt.letsjamrestapi.business.SqlDb;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.Instrument;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheet;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheetData;
import it.univaq.disim.mwt.letsjamrestapi.models.UpdateMusicsheetBody;

public class MusicsheetDBService {

    public static MusicSheet makeMusicsheet(ResultSet rs) throws NotFoundException, SQLException {
        MusicSheet m = new MusicSheet();
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
        m.setUser(UserDBService.getUserById(BigDecimal.valueOf(rs.getLong("user_id"))));
        m.setSong(SongDBService.getSongById(BigDecimal.valueOf(rs.getLong("song_id"))));
        m.setInstruments(InstrumentDBService.getMusicsheetInstruments(m.getId()));
        return m;
    }

    public static MusicSheet getMusicsheetById(BigDecimal musicsheetId) throws NotFoundException, SQLException {
        Connection c = SqlDb.getConnection();
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
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        }
        throw new NotFoundException("MusicSheet not found");
    }

    public static List<MusicSheet> searchMusicSheets(
            String search, String sortBy,
            String sortDirection, List<String> genres,
            List<String> instruments, Boolean verified,
            Boolean rearranged, Boolean tablature, BigDecimal pageNumber, BigDecimal pageSize)
            throws NotFoundException, SQLException {

        Connection c = SqlDb.getConnection();
        String q = "SELECT *, (SELECT COUNT(music_sheet_id) FROM spartiti_likes WHERE music_sheet_id = spartiti.id) as likes FROM spartiti JOIN brani ON song_id = brani.id JOIN generi ON brani.genre_id = generi.id ";
        if (!instruments.isEmpty())
            q += "JOIN spartiti_strumenti ON music_sheet_id = spartiti.id JOIN strumenti ON instrument_id = strumenti.id ";
        boolean whereClause = false;

        // SEARCH STRING
        if (search != null && search.length() > 0) {
            q += "WHERE (spartiti.title LIKE CONCAT('%',?,'%') OR spartiti.author LIKE CONCAT('%',?,'%') OR brani.title LIKE CONCAT('%',?,'%') OR brani.author LIKE CONCAT('%',?,'%')) ";
            whereClause = true;
        }
        // GENERES
        if (!genres.isEmpty() && !whereClause) {
            q += "WHERE generi.name in (";
            for (int i = 0; i < genres.size(); i++) {
                q += (i == genres.size() - 1) ? "?" : "?,";
            }
            q += ") ";
            whereClause = true;
        } else if (!genres.isEmpty() && whereClause) {
            q += "AND generi.name in (";
            for (int i = 0; i < genres.size(); i++) {
                q += (i == genres.size() - 1) ? "?" : "?,";
            }
            q += ") ";
        }

        // INSTRUMENTS
        if (!instruments.isEmpty() && !whereClause) {
            q += "WHERE strumenti.name in (";
            for (int i = 0; i < instruments.size(); i++) {
                q += (i == instruments.size() - 1) ? "?" : "?,";
            }
            q += ") ";
            whereClause = true;
        } else if (!instruments.isEmpty() && whereClause) {
            q += "AND strumenti.name in (";
            for (int i = 0; i < instruments.size(); i++) {
                q += (i == instruments.size() - 1) ? "?" : "?,";
            }
            q += ") ";
        }

        // VERIFIED
        if (verified != null && !whereClause) {
            q += "WHERE verified = ? ";
            whereClause = true;
        } else if (verified != null && whereClause) {
            q += "AND verified = ? ";
        }
        // REARRANGED
        if (rearranged != null && !whereClause) {
            q += "WHERE rearranged = ? ";
            whereClause = true;
        } else if (rearranged != null && whereClause) {
            q += "AND rearranged = ? ";
        }
        // HAS TABLATURE
        if (tablature != null && !whereClause) {
            q += "WHERE has_tablature = ? ";
            whereClause = true;
        } else if (tablature != null && whereClause) {
            q += "AND has_tablature = ? ";
        }
        // ORDER BY
        if (sortBy != null) {
            q += "ORDER BY " + sortBy;
        }
        // SORT DIRECTION
        if (sortDirection != null) {
            q += " " + sortDirection + " ";
        }
        // PAGESIZE PAGENUMBER
        if (pageSize != null && pageNumber != null) {
            q += "LIMIT ? OFFSET ? ";
        }

        PreparedStatement stmt = c.prepareStatement(q);
        int indexCounter = 0;
        if (search != null && search.length() > 0) {
            for (int i = 0; i < 4; i++)
                stmt.setString(++indexCounter, search);
        }
        if (!genres.isEmpty()) {
            for (int i = 0; i < genres.size(); i++)
                stmt.setString(++indexCounter, genres.get(i));
        }
        if (!instruments.isEmpty()) {
            for (int i = 0; i < instruments.size(); i++)
                stmt.setString(++indexCounter, instruments.get(i));
        }
        if (verified != null && verified)
            stmt.setBoolean(++indexCounter, verified);
        if (rearranged != null && rearranged)
            stmt.setBoolean(++indexCounter, rearranged);
        if (tablature != null && tablature)
            stmt.setBoolean(++indexCounter, tablature);
        if (pageSize != null && pageNumber != null) {
            stmt.setLong(++indexCounter, pageSize.longValue());
            stmt.setLong(++indexCounter, pageSize.longValue() * pageNumber.longValue());
        }

        List<MusicSheet> spartiti = new ArrayList<MusicSheet>();
        ResultSet rs = stmt.executeQuery();
        try {
            while (rs.next()) {
                spartiti.add(makeMusicsheet(rs));
            }
            return spartiti;
        } finally {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (c != null)
                c.close();
        }
    }

    public static MusicSheetData getMusicsheetData(BigDecimal musicsheetId) throws ApiException {
        MongoClient conn = MongoDb.getConnection();
        MongoCollection<Document> collection = conn.getDatabase(MongoDb.DBNAME).getCollection("spartiti");
        BasicDBObject query = new BasicDBObject();
        query.put("_id", musicsheetId.toString());
        Document d = collection.find(query).first();
        MusicSheetData data = MusicSheetDataMapper.deserialize(d);
        conn.close();
        return data;
    }

    public static void addMusicSheetData(MusicSheetData data, BigDecimal musicsheetId) {
        MongoClient conn = MongoDb.getConnection();
        MongoCollection<Document> collection = conn.getDatabase(MongoDb.DBNAME).getCollection("spartiti");
        collection.insertOne(MusicSheetDataMapper.serialize(data, musicsheetId));
        conn.close();
    }

    public static BigDecimal addMusicSheet(MusicSheet m) throws SQLException, NotFoundException {
        Connection c = SqlDb.getConnection();
        String insertMusicSheetQuery = "INSERT INTO spartiti (create_date_time, title, author, user_id, song_id, rearranged, has_tablature, visibility, verified) ";
        insertMusicSheetQuery += "VALUES(CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?)";
        PreparedStatement st = c.prepareStatement(insertMusicSheetQuery, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, m.getTitle());
        st.setString(2, m.getAuthor());
        st.setLong(3, m.getUser().getId().longValue());
        st.setLong(4, m.getSong().getId().longValue());
        st.setBoolean(5, m.isRearranged());
        st.setBoolean(6, m.isHasTablature());
        st.setBoolean(7, m.isVisibility());
        st.setBoolean(8, m.isVerified());
        try {
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            BigDecimal id = (rs.next()) ? BigDecimal.valueOf(rs.getLong(1)) : null;
            rs.close();
            m.setId(id);
            insertMusicSheetInstruments(m);
            return id;
        } finally {
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        }
    }

    public static void insertMusicSheetInstruments(MusicSheet m) throws SQLException, NotFoundException {
        Connection c = SqlDb.getConnection();
        String instrumentsQuery = "INSERT INTO spartiti_strumenti (music_sheet_id, instrument_id) VALUES (?,?)";
        PreparedStatement st = c.prepareStatement(instrumentsQuery);
        List<Instrument> strumenti = m.getInstruments();
        try {
            for (int i = 0; i < strumenti.size(); i++) {
                Instrument listInstrument = strumenti.get(i);
                Instrument dbInstrument = InstrumentDBService.getInstrumentByName(listInstrument.getName());
                if (dbInstrument == null) {
                    listInstrument.setId(InstrumentDBService.addInstrument(listInstrument.getName()));
                } else {
                    listInstrument = dbInstrument;
                }
                st.setLong(1, m.getId().longValue());
                st.setLong(2, listInstrument.getId().longValue());
                st.executeUpdate();
            }
        } finally {
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        }
    }

    public static void deleteMusicSheet(BigDecimal musicsheetId) throws SQLException {
        Connection c = SqlDb.getConnection();
        PreparedStatement st = c.prepareStatement("DELETE FROM spartiti WHERE id = ?");
        st.setLong(1, musicsheetId.longValue());
        st.executeUpdate();
        st = c.prepareStatement("DELETE FROM spartiti_strumenti WHERE music_sheet_id = ?");
        st.setLong(1, musicsheetId.longValue());
        st.executeUpdate();
        st = c.prepareStatement("DELETE FROM spartiti_likes WHERE music_sheet_id = ?");
        st.setLong(1, musicsheetId.longValue());
        try {
            st.executeUpdate();
        } finally {
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        }
        MongoClient conn = MongoDb.getConnection();
        MongoCollection<Document> collection = conn.getDatabase(MongoDb.DBNAME).getCollection("spartiti");
        BasicDBObject query = new BasicDBObject();
        query.append("_id", musicsheetId.toString());
        collection.deleteOne(query);
        conn.close();
    }

    public static void updateMusicSheet(UpdateMusicsheetBody body, BigDecimal musicsheetId) throws SQLException {
        Connection c = SqlDb.getConnection();
        PreparedStatement st = c.prepareStatement("UPDATE spartiti SET title=?, author=?, visibility=? WHERE id = ?");
        st.setString(1, body.getTitle());
        st.setString(2, body.getAuthor());
        st.setBoolean(3, body.isVisibility());
        st.setLong(4, musicsheetId.longValue());
        try {
            st.executeUpdate();
        } finally {
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        }
        
        MongoClient conn = MongoDb.getConnection();
        MongoCollection<Document> collection = conn.getDatabase(MongoDb.DBNAME).getCollection("spartiti");
        BasicDBObject updateField = new BasicDBObject();
        updateField.append("content", body.getContent());
        BasicDBObject update = new BasicDBObject();
        update.append("$set", updateField);
        BasicDBObject query = new BasicDBObject();
        query.append("_id", musicsheetId.toString());
        collection.findOneAndUpdate(query, update);
        conn.close();
    }
}
