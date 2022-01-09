package it.univaq.disim.mwt.letsjamrestapi.business;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Database {
    
    public static Connection getConnection(){
        InitialContext ic;
        try {
            ic = new InitialContext();
            Context xmlContext = (Context) ic.lookup("java:comp/env");
            DataSource myDatasource = (DataSource) xmlContext.lookup("jdbc/DB");
            return myDatasource.getConnection();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
