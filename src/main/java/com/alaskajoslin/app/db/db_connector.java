package com.alaskajoslin.app.db;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.sql.*;
import java.util.*;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.simple.*;
import org.apache.logging.log4j.status.*;
import org.apache.logging.log4j.util.*;
/**
 *
 * @author matthewjoslin
 */
public class db_connector {

    private static final Logger LOGGER = LogManager.getLogger();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String url;
    private String user;
    private String password;

    // mysql> SHOW COLUMNS FROM entries;
    // +------------+-------------+------+-----+---------+-------+
    // | Field      | Type        | Null | Key | Default | Extra |
    // +------------+-------------+------+-----+---------+-------+
    // | word       | varchar(25) | NO   |     | NULL    |       |
    // | wordtype   | varchar(20) | NO   |     | NULL    |       |
    // | definition | text        | NO   |     | NULL    |       |
    // +------------+-------------+------+-----+---------+-------+
    

    public db_connector() {
        // TODO code application logic here
        try {
	    BufferedReader reader = new BufferedReader(new FileReader("/home/matthew/db_test_params.txt"));
            url = reader.readLine();
            user = reader.readLine();
            password = reader.readLine();
            
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	    
	    System.out.println(url);
            System.out.println(user);
            System.out.println(password);    
	} catch (Exception ex) {
	    System.out.println(ex);
            LOGGER.log(Level.ERROR, ex.getMessage(), ex);
        }
    }

    public boolean connect()
    {
       try {
            con = DriverManager.getConnection(url,user,password);
            return true;
       } catch (Exception ex) {
            System.out.println(ex);
            LOGGER.log(Level.ERROR, ex.getMessage(), ex);
            return false; 
       }
    }
    
    public boolean runQuery(String query)
    {
       try {    
            st = con.createStatement();
            rs = st.executeQuery(query);
	    System.out.println("Executed query");
            System.out.println(rs);
            return true;
       } catch (Exception ex) {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException sqlex) {
                LOGGER.log(Level.WARN, ex.getMessage(), sqlex);
            }
	    return false;
        } 
    }
    public boolean closeConnection()
    {
       try {
            con.close();
            System.out.println("Connection closed");
            con = DriverManager.getConnection(url,user,password);
            return true;
       } catch (Exception ex) {
            System.out.println(ex);
            LOGGER.log(Level.ERROR, ex.getMessage(), ex);
            return false; 
       }
    }
}
