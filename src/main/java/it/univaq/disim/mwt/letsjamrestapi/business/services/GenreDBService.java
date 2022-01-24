package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.mwt.letsjamrestapi.business.SqlDb;
import it.univaq.disim.mwt.letsjamrestapi.models.Genre;

public class GenreDBService {

    public static Genre makeGenre(ResultSet rs) throws SQLException {
        Genre g = new Genre();
        g.setId(new BigDecimal(rs.getLong("id")));
        g.setName(rs.getString("name"));
        g.setDescription(rs.getString("description"));
        return g;
    }

    public static List<Genre> getAllGenres() throws SQLException {
        Connection c = SqlDb.getConnection();
        List<Genre> generi = new ArrayList<Genre>();
        String query = "SELECT * FROM generi";
        PreparedStatement st = c.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        try {
            while (rs.next()) {
                generi.add(makeGenre(rs));
            }
        } finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        }
        return generi;
    }

    public static Genre getGenreById(BigDecimal genreId) throws SQLException {
        Connection c = SqlDb.getConnection();
        List<Genre> generi = new ArrayList<Genre>();
        String query = "SELECT * FROM generi WHERE id = ?";
        PreparedStatement st = c.prepareStatement(query);
        st.setLong(1, genreId.longValue());
        ResultSet rs = st.executeQuery();
        try {
            while (rs.next()) {
                generi.add(makeGenre(rs));
            }
        } finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        }
        return generi.get(0);
    }
}
