package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.mwt.letsjamrestapi.business.SqlDb;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.Instrument;

public class InstrumentDBService {
    
    public static Instrument makeInstrument(ResultSet rs) throws SQLException {
        Instrument i = new Instrument();
        i.setId(new BigDecimal(rs.getLong("id")));
        i.setName(rs.getString("name"));
        i.setDescription(rs.getString("instrument_key"));
        return i;
    }

    public static List<Instrument> getAllInstruments() throws SQLException{
        Connection c = SqlDb.getConnection();
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
    }

    public static List<Instrument> getMusicsheetInstruments(BigDecimal musicsheetId) throws SQLException{
        Connection c = SqlDb.getConnection();
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
    }

    public static BigDecimal addInstrument(String name) throws SQLException{
        Connection c = SqlDb.getConnection();
        String query = "INSERT INTO strumenti (name) VALUES (?)";
        PreparedStatement st = c.prepareStatement(query);
        st.setString(1, name);
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        BigDecimal id = (rs.next()) ? BigDecimal.valueOf(rs.getLong(1)) : null;
        rs.close();
        return id;
    }

    public static Instrument getInstrumentByName(String name) throws SQLException, NotFoundException{
        Connection c = SqlDb.getConnection();
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
        throw new NotFoundException("Instrument not found");
    }
}
