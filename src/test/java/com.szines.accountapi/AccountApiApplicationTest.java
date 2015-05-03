package com.szines.accountapi;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AccountApiApplicationTest
        extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AccountApiApplicationTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AccountApiApplicationTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAccountApiApplication()
    {
        assertTrue( true );
    }
}
