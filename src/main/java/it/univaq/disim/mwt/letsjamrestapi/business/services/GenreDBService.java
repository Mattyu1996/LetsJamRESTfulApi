package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.univaq.disim.mwt.letsjamrestapi.models.Genre;

public class GenreDBService {
    
    public static Genre makeGenre(ResultSet rs) {
        Genre g = new Genre();
        try {
            g.setId(new BigDecimal(rs.getLong("id")));
            g.setName(rs.getString("name"));
            g.setDescription(rs.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }
}
