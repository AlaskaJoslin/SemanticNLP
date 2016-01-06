package com.alaskajoslin.app.db;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.*;

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
    	db_connector connector = setup();
	Assert.assertTrue(connector.connect());
	Assert.assertTrue(connector.runQuery("SELECT * FROM entries LIMIT 5;"));
	Assert.assertTrue(connector.closeConnection());
    }

    public db_connector setup()
    {
    	db_connector connector = new db_connector();
	return connector;
    }

}
