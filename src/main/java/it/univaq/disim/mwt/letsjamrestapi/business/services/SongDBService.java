package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.mwt.letsjamrestapi.business.SqlDb;
import it.univaq.disim.mwt.letsjamrestapi.models.Song;

public class SongDBService {

    public static Song makeSong(ResultSet rs) {
        Song s = new Song();
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static Song getSongById(BigDecimal songId){
        Connection c = SqlDb.getConnection();
        try {
            List<Song> brani = new ArrayList<Song>();
            String query = "SELECT * FROM brani WHERE id = ?";
            PreparedStatement st = c.prepareStatement(query);
            st.setLong(1, songId.longValue());
            ResultSet rs = st.executeQuery();
            try {
                while (rs.next()) {
                    brani.add(makeSong(rs));
                }
                return brani.get(0);
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Song getAllSongs(){
        Connection c = SqlDb.getConnection();
        try {
            List<Song> brani = new ArrayList<Song>();
            String query = "SELECT * FROM brani";
            PreparedStatement st = c.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            try {
                while (rs.next()) {
                    brani.add(makeSong(rs));
                }
                return brani.get(0);
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
