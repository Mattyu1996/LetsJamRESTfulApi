package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.mwt.letsjamrestapi.business.SqlDb;
import it.univaq.disim.mwt.letsjamrestapi.models.Instrument;

public class InstrumentDBService {
    
    public static Instrument makeInstrument(ResultSet rs) {
        Instrument i = new Instrument();
        try {
            i.setId(new BigDecimal(rs.getLong("id")));
            i.setName(rs.getString("name"));
            i.setDescription(rs.getString("instrument_key"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static List<Instrument> getAllInstruments(){
        Connection c = SqlDb.getConnection();
        try {
            List<Instrument> strumenti = new ArrayList<Instrument>();
            String query = "SELECT * FROM strumenti";
            PreparedStatement st = c.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            try {
                while (rs.next()) {
                    strumenti.add(makeInstrument(rs));
                }
                return strumenti;
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Instrument> getMusicsheetInstruments(BigDecimal musicsheetId){
        Connection c = SqlDb.getConnection();
        try {
            List<Instrument> strumenti = new ArrayList<Instrument>();
            String query = "SELECT * FROM strumenti JOIN spartiti_strumenti ON instrument_id = id WHERE music_sheet_id = ?";
            PreparedStatement st = c.prepareStatement(query);
            st.setLong(1, musicsheetId.longValue());
            ResultSet rs = st.executeQuery();
            try {
                while (rs.next()) {
                    strumenti.add(makeInstrument(rs));
                }
                return strumenti;
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BigDecimal addInstrument(String name){
        Connection c = SqlDb.getConnection();
        String query = "INSERT INTO strumenti (name) VALUES (?)";
        try {
            PreparedStatement st = c.prepareStatement(query);
            st.setString(1, name);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            BigDecimal id = (rs.next()) ? BigDecimal.valueOf(rs.getLong(1)) : null;
            rs.close();
            return id;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Instrument getInstrumentByName(String name){
        Connection c = SqlDb.getConnection();
        try {
            String query = "SELECT * FROM strumenti WHERE name = ?";
            PreparedStatement st = c.prepareStatement(query);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            try {
                if (rs.next()) {
                    return makeInstrument(rs);
                }
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
