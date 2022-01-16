package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.univaq.disim.mwt.letsjamrestapi.business.SqlDb;
import it.univaq.disim.mwt.letsjamrestapi.models.User;
import it.univaq.disim.mwt.letsjamrestapi.models.Genre;
import it.univaq.disim.mwt.letsjamrestapi.models.Instrument;
import it.univaq.disim.mwt.letsjamrestapi.models.NewUser;
import it.univaq.disim.mwt.letsjamrestapi.models.UpdateUserBody;
import it.univaq.disim.mwt.letsjamrestapi.models.User.RoleEnum;

public class UserDBService {

    public static User makeUser(ResultSet rs) {
        User u = new User();
        try {
            u.setId(new BigDecimal(rs.getLong("id")));
            u.setFirstname(rs.getString("firstname"));
            u.setLastname(rs.getString("lastname"));
            u.setUsername(rs.getString("username"));
            u.setRole(RoleEnum.fromValue(rs.getString("role")));
            u.setEmail(rs.getString("email"));
            u.setAvatar(rs.getString("avatar"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public static List<User> getAllUsers() {
        Connection c = SqlDb.getConnection();
        try {
            List<User> utenti = new ArrayList<User>();
            ResultSet rs = c.createStatement().executeQuery("SELECT * FROM utenti");
            try {
                while (rs.next()) {
                    utenti.add(makeUser(rs));
                }
            } finally {
                rs.close();
            }
            return utenti;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> getUserByUsername(String username) {
        Connection c = SqlDb.getConnection();
        try {
            List<User> utenti = new ArrayList<User>();
            PreparedStatement st = c.prepareStatement("SELECT * FROM utenti where username = ?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            try {
                while (rs.next()) {
                    utenti.add(makeUser(rs));
                }
            } finally {
                rs.close();
            }
            return utenti;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> getUserByEmail(String email) {
        Connection c = SqlDb.getConnection();
        try {
            List<User> utenti = new ArrayList<User>();
            PreparedStatement st = c.prepareStatement("SELECT * FROM utenti where email = ?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            try {
                while (rs.next()) {
                    utenti.add(makeUser(rs));
                }
            } finally {
                rs.close();
            }
            return utenti;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> getUserByRole(String role) {
        Connection c = SqlDb.getConnection();
        try {
            List<User> utenti = new ArrayList<User>();
            PreparedStatement st = c.prepareStatement("SELECT * FROM utenti where role = ?");
            st.setString(1, role);
            ResultSet rs = st.executeQuery();
            try {
                while (rs.next()) {
                    utenti.add(makeUser(rs));
                }
            } finally {
                rs.close();
            }
            return utenti;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> getUserById(BigDecimal id) {
        Connection c = SqlDb.getConnection();
        try {
            List<User> utenti = new ArrayList<User>();
            PreparedStatement st = c.prepareStatement("SELECT * FROM utenti where id = ?");
            st.setLong(1, id.longValue());
            ResultSet rs = st.executeQuery();
            try {
                while (rs.next()) {
                    utenti.add(makeUser(rs));
                }
            } finally {
                rs.close();
            }
            return utenti;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User addUser(NewUser body){
        Connection c = SqlDb.getConnection();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            PreparedStatement st = c.prepareStatement("INSERT INTO utenti (username, firstname, lastname, email, password, role) VALUES (?,?,?,?,?,?)");
            st.setString(1, body.getUsername());
            st.setString(2, body.getFirstname());
            st.setString(3, body.getLastname());
            st.setString(4, body.getEmail());
            st.setString(5, encoder.encode(body.getPassword()));
            st.setString(6, RoleEnum.UTENTE.toString());
            st.executeUpdate();
            st.close();
            return getUserByEmail(body.getEmail()).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User updateUser(BigDecimal id, UpdateUserBody body) {
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("UPDATE utenti SET firstname = ?, lastname = ?, email = ? WHERE id = ?");
            st.setString(1, body.getFirstname());
            st.setString(2, body.getLastname());
            st.setString(3, body.getEmail());
            st.setLong(4, id.longValue());
            st.executeUpdate();
            return getUserById(id).get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteUser(BigDecimal id) {
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("DELETE FROM utenti WHERE id = ?");
            st.setLong(1, id.longValue());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeUserPreferredGenre(BigDecimal userId, BigDecimal genreId){
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("DELETE FROM generi_preferiti WHERE user_id = ? AND genre_id = ?");
            st.setLong(1, userId.longValue());
            st.setLong(2, genreId.longValue());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUserPreferredGenre(BigDecimal userId, BigDecimal genreId){
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("INSERT INTO generi_preferiti (user_id, genre_id) VALUES(?,?)");
            st.setLong(1, userId.longValue());
            st.setLong(2, genreId.longValue());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Genre> getUserPreferredGenres(BigDecimal id){
        Connection c = SqlDb.getConnection();
        try {
            List<Genre> generi = new ArrayList<Genre>();
            String query = "SELECT generi.id, name, description FROM utenti JOIN generi_preferiti ON utenti.id = user_id JOIN generi ON genre_id = generi.id WHERE utenti.id = ?";
            PreparedStatement st = c.prepareStatement(query);
            st.setLong(1, id.longValue());
            ResultSet rs = st.executeQuery();
            try {
                while (rs.next()) {
                    generi.add(GenreDBService.makeGenre(rs));
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

    public static void removeUserPreferredInstrument(BigDecimal userId, BigDecimal instrumentId){
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("DELETE FROM strumenti_preferiti WHERE user_id = ? AND instrument_id = ?");
            st.setLong(1, userId.longValue());
            st.setLong(2, instrumentId.longValue());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUserPreferredInstrument(BigDecimal userId, BigDecimal instrumentId){
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("INSERT INTO strumenti_preferiti (user_id, instrument_id) VALUES(?,?)");
            st.setLong(1, userId.longValue());
            st.setLong(2, instrumentId.longValue());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Instrument> getUserPreferredInstruments(BigDecimal id){
        Connection c = SqlDb.getConnection();
        try {
            List<Instrument> strumenti = new ArrayList<Instrument>();
            String query = "SELECT strumenti.id, name, instrument_key FROM utenti JOIN strumenti_preferiti ON utenti.id = user_id JOIN strumenti ON instrument_id = strumenti.id WHERE utenti.id = ?";
            PreparedStatement st = c.prepareStatement(query);
            st.setLong(1, id.longValue());
            ResultSet rs = st.executeQuery();
            try {
                while (rs.next()) {
                    strumenti.add(InstrumentDBService.makeInstrument(rs));
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

    public static void updateUserAvatar(BigDecimal userId, String avatarPath){
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("UPDATE utenti SET avatar = ? WHERE id = ?");
            st.setString(1, avatarPath);
            st.setLong(2, userId.longValue());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addLike(BigDecimal userId, BigDecimal musicsheetId){
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("INSERT INTO spartiti_likes (music_sheet_id, user_id) VALUES(?,?)");
            st.setLong(1, musicsheetId.longValue());
            st.setLong(2, userId.longValue());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeLike(BigDecimal userId, BigDecimal musicsheetId){
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("DELETE FROM spartiti_likes WHERE user_id = ? AND music_sheet_id = ?");
            st.setLong(1, userId.longValue());
            st.setLong(2, musicsheetId.longValue());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Boolean authenticateUser(String email, String password){
        return true;
    }

    public static User getUserFromToken(String token){
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("SELECT * FROM utenti JOIN tokens ON utenti.id = user_id WHERE token = ?");
            st.setString(1, token);
            ResultSet rs = st.executeQuery();
            try {
                if (rs.next()) {
                    return makeUser(rs);
                }
            } finally {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void invalidateToken(String token){
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("DELETE FROM tokens WHERE token = ?");
            st.setString(1, token);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUserToken(BigDecimal userId, String token){
        Connection c = SqlDb.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("INSERT INTO tokens (user_id, token) VALUES (?,?)");
            st.setLong(1, userId.longValue());
            st.setString(2, token);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
