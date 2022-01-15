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
            String query = "SELECT * FROM brani WHERE id = ?";
            PreparedStatement st = c.prepareStatement(query);
            st.setLong(1, songId.longValue());
            ResultSet rs = st.executeQuery();
            try {
                if (rs.next()) {
                    return makeSong(rs);
                }
                else {
                    try {
                        throw new NotFoundException(404, "Song not found");
                    } catch (NotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
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

    public static BigDecimal addSong(Song s){
        Connection c = SqlDb.getConnection();
        String query = "INSERT INTO brani (title, author) VALUES (?,?)";
        try {
            PreparedStatement st = c.prepareStatement(query);
            st.setString(1, s.getTitle());
            st.setString(2, s.getAuthor());
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
    
}
