package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
