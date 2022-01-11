package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.univaq.disim.mwt.letsjamrestapi.business.Database;
import it.univaq.disim.mwt.letsjamrestapi.models.User;
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
        Connection c = Database.getConnection();
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
        Connection c = Database.getConnection();
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
        Connection c = Database.getConnection();
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
        Connection c = Database.getConnection();
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
        Connection c = Database.getConnection();
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
}
