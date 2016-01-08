package com.alaskajoslin.app.test.db;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.alaskajoslin.app.db.*;
import com.alaskajoslin.app.test.group.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;
//import junit.framework.*;

//@Category({FastRunningTests.class})
public class TestMySQLConnectionTest {

    db_connector connector;

/*    public TestMySQLConnectionTest( String testName )
    {
    }
*/

    /**
     * @return the suite of tests being tested
     
    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        //suite.addTest(new MySQLConnectionTest("testConnect"));
	return suite;
    }*/
    @Test
    @Category(FastRunningTests.class)
    public void testConnect() {
	Assert.assertTrue(connector.connect());
	Assert.assertTrue(connector.runQuery("SELECT * FROM entries LIMIT 5;"));
	Assert.assertTrue(connector.closeConnection());
    }

    @Before
    public void setup()
    {
    	connector = new db_connector();
    }

}
