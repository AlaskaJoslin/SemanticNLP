package com.alaskajoslin.mysql;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MySQLConnectionTest extends TestCase {

    public MySQLConnectionTest( String testName )
    {
        super( testName );
    }


    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTest(new MySQLConnectionTest("testConnect"));
	return suite;
    }

  public void testConnect() {
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

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (Exception e) {
	e.printStackTrace();
    }
  }
}
