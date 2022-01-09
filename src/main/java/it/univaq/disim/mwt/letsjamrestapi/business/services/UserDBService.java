package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.mwt.letsjamrestapi.business.Database;
import it.univaq.disim.mwt.letsjamrestapi.models.User;
import it.univaq.disim.mwt.letsjamrestapi.models.User.RoleEnum;

public class UserDBService {
    
    public static List<User> getAllUsers(){
        Connection c = Database.getConnection();
        try {
            List<User> utenti = new ArrayList<User>();
            ResultSet rs = c.createStatement().executeQuery("SELECT * FROM utenti");
            try{
                while(rs.next()){
                    User u = new User();
                    u.setId(new BigDecimal(rs.getLong("id")));
                    u.setFirstname(rs.getString("firstname"));
                    u.setLastname(rs.getString("lastname"));
                    u.setUsername(rs.getString("username"));
                    u.setRole(RoleEnum.fromValue(rs.getString("role")));
                    u.setEmail(rs.getString("email"));
                    u.setAvatar(rs.getString("avatar"));
                    utenti.add(u);
                }
            } finally {
                rs.close();
            }
            return utenti; 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
