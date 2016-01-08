package com.alaskajoslin.app.test;

import com.alaskajoslin.app.db.*;
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;
import com.alaskajoslin.app.test.group.*;
/**
 * Unit test for simple App.
 */
public class TestAppTest
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
    public TestAppTest( String testName )
    {
    }
*/
    /**
     * @return the suite of tests being tested
     
    public static Test suite()
    {
	TestSuite suite = new TestSuite();
	//suite.addTest(MySQLConnectionTest.newInstance());
	//suite.addTest(new AppTest("testApp"));
        //suite.run();
	return suite;
    }*/

    /**
     * Rigourous Test :-)
     */
    @Test
    @Category({FastRunningTests.class})
    public void testApp()
    {
        assertTrue( true );
    }
}
