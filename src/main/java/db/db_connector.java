package db;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.apache.log4j.Logger;

/**
 *
 * @author matthewjoslin
 */
public class db_connector {

    /**
     * @param args the command line arguments
     */
    //  private static final Logger LOGGER = Logger.getLogger(db_connector.class);

    public db_connector(String[] args) {
        // TODO code application logic here
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String url = args[0];
        String user = args[1];
        String password = args[2];
        try {
            con = DriverManager.getConnection(url,user,password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION();");
            System.out.println(rs);
            con.close();
        } catch (SQLException ex) {
            // LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
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
                // LOGGER.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}
