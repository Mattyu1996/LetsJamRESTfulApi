package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.mwt.letsjamrestapi.business.Database;
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

    public static List<Genre> getAllGenres(){
        Connection c = Database.getConnection();
        try {
            List<Genre> generi = new ArrayList<Genre>();
            String query = "SELECT * FROM generi";
            PreparedStatement st = c.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            try {
                while (rs.next()) {
                    generi.add(makeGenre(rs));
                }
                return generi;
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Genre getGenreById(BigDecimal genreId){
        Connection c = Database.getConnection();
        try {
            List<Genre> generi = new ArrayList<Genre>();
            String query = "SELECT * FROM generi WHERE id = ?";
            PreparedStatement st = c.prepareStatement(query);
            st.setLong(1, genreId.longValue());
            ResultSet rs = st.executeQuery();
            try {
                while (rs.next()) {
                    generi.add(makeGenre(rs));
                }
                return generi.get(0);
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
