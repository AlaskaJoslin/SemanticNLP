package com.alaskajoslin.app;

import com.alaskajoslin.mysql.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
	TestSuite suite = new TestSuite();
	suite.addTest(MySQLConnectionTest.newInstance());
	//suite.addTest(new AppTest("testApp"));
        suite.run();
	return suite;
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
