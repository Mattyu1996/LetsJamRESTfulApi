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
import it.univaq.disim.mwt.letsjamrestapi.models.Song;

public class SongDBService {

    public static Song makeSong(ResultSet rs) throws SQLException {
        Song s = new Song();
        s.setId(new BigDecimal(rs.getLong("id")));
        s.setTitle(rs.getString("title"));
        s.setAuthor(rs.getString("author"));
        s.setAlbumName(rs.getString("album_name"));
        s.setAlbumType(rs.getString("album_type"));
        s.setDuration(BigDecimal.valueOf(rs.getLong("duration")));
        s.setImage(rs.getString("image_url"));
        s.setIsExplicit(rs.getBoolean("is_explicit"));
        s.setLyrics(rs.getString("lyrics"));
        s.setSpotifyId(rs.getString("spotify_id"));
        s.setGenre(GenreDBService.getGenreById(BigDecimal.valueOf(rs.getLong("genre_id"))));
        return s;
    }

    public static Song getSongById(BigDecimal songId) throws NotFoundException, SQLException {
        Connection c = SqlDb.getConnection();
        String query = "SELECT * FROM brani WHERE id = ?";
        PreparedStatement st = c.prepareStatement(query);
        st.setLong(1, songId.longValue());
        ResultSet rs = st.executeQuery();
        try {
            if (rs.next()) {
                return makeSong(rs);
            }
        } finally {
            rs.close();
        }
        throw new NotFoundException("Song not found");
    }

    public static BigDecimal addSong(Song s) throws SQLException {
        Connection c = SqlDb.getConnection();
        String query = "INSERT INTO brani (title, author) VALUES (?,?)";
        PreparedStatement st = c.prepareStatement(query);
        st.setString(1, s.getTitle());
        st.setString(2, s.getAuthor());
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        BigDecimal id = (rs.next()) ? BigDecimal.valueOf(rs.getLong(1)) : null;
        rs.close();
        return id;
    }

    public static List<Song> searchSongs(
            String search, String sortBy,
            String sortDirection, List<String> genres,
            Boolean isExplicit, Boolean hasLyrics,
            String albumtype, BigDecimal pageNumber,
            BigDecimal pageSize) throws SQLException {

        Connection c = SqlDb.getConnection();
        String q = "SELECT * FROM brani JOIN generi ON genre_id = generi.id ";
        boolean whereClause = false;
        // SEARCH STRING
        if (search != null && search.length() > 0) {
            q += "WHERE (brani.title LIKE CONCAT('%',?,'%') OR brani.author LIKE CONCAT('%',?,'%') OR brani.album_name LIKE CONCAT('%',?,'%')) ";
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
        // ALBUM TYPE
        if (albumtype != null && !whereClause) {
            q += "WHERE brani.album_type = ? ";
            whereClause = true;
        } else if (albumtype != null && whereClause) {
            q += "AND brani.album_type = ? ";
        }
        // EXPLICIT
        if (isExplicit != null && !whereClause) {
            q += "WHERE brani.is_explicit = ? ";
            whereClause = true;
        } else if (isExplicit != null && whereClause) {
            q += "AND brani.is_explicit = ? ";
        }
        // LYRICS
        if (hasLyrics != null && !whereClause) {
            q += (hasLyrics) ? "WHERE brani.lyrics IS NOT NULL " : "WHERE brani.lyrics IS NULL ";
            whereClause = true;
        } else if (hasLyrics != null && whereClause) {
            q += (hasLyrics) ? "AND brani.lyrics IS NOT NULL " : "AND brani.lyrics IS NULL ";
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

        List<Song> brani = new ArrayList<Song>();
        PreparedStatement stmt = c.prepareStatement(q);
        int indexCounter = 0;
        if (search != null && search.length() > 0) {
            for (int i = 0; i < 3; i++)
                stmt.setString(++indexCounter, search);
        }
        if (!genres.isEmpty()) {
            for (int i = 0; i < genres.size(); i++)
                stmt.setString(++indexCounter, genres.get(i));
        }
        if (albumtype != null) {
            stmt.setString(++indexCounter, albumtype);
        }
        if (isExplicit != null) {
            stmt.setBoolean(++indexCounter, isExplicit);
        }
        if (pageSize != null && pageNumber != null) {
            stmt.setLong(++indexCounter, pageSize.longValue());
            stmt.setLong(++indexCounter, pageSize.longValue() * pageNumber.longValue());
        }

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            brani.add(makeSong(rs));
        }
        rs.close();
        return brani;
    }
}
