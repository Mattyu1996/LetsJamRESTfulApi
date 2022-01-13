package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.mwt.letsjamrestapi.business.Database;
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
        Connection c = Database.getConnection();
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
        Connection c = Database.getConnection();
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
}
