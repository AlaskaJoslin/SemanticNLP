package db;

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

    /**
     * @param args the command line arguments
     */
    private static final Logger LOGGER = LogManager.getLogger();
    // mysql> SHOW COLUMNS FROM entries;
    // +------------+-------------+------+-----+---------+-------+
    // | Field      | Type        | Null | Key | Default | Extra |
    // +------------+-------------+------+-----+---------+-------+
    // | word       | varchar(25) | NO   |     | NULL    |       |
    // | wordtype   | varchar(20) | NO   |     | NULL    |       |
    // | definition | text        | NO   |     | NULL    |       |
    // +------------+-------------+------+-----+---------+-------+
    

    public db_connector(String[] args) {
        // TODO code application logic here
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
	    BufferedReader reader = new BufferedReader(new FileReader("/home/matthew/db_test_params.txt"));
            String url = reader.readLine();
            String query = reader.readLine();
            String user = reader.readLine();
            String password = reader.readLine();

            Class.forName("com.mysql.jdbc.Driver").newInstance();
	    System.out.println(url);
            System.out.println(user);
            System.out.println(password);
            con = DriverManager.getConnection(url,user,password);
            st = con.createStatement();
            rs = st.executeQuery(query);
            System.out.println("Executed query");
            System.out.println(rs);
            con.close();
            System.out.println("Connection closed");
	} catch (Exception ex) {
	    System.out.println(ex);
            LOGGER.log(Level.ERROR, ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                LOGGER.log(Level.WARN, ex.getMessage(), ex);
            }
        }
    }
}
