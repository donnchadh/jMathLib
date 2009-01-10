package jmathlibtests.toolbox.deprecated;

import jmathlib.tools.junit.framework.*;

/**
 * TestSuite that runs all the tests
 *
 */
public class AllTests {

	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	public static Test suite ( ) {
		TestSuite suite= new TestSuite("deprecated functions");

		/* include subdirectories here */
		// none
        
	    /* include tests in this directory here */
        suite.addTest(jmathlibtests.toolbox.deprecated.testFinite.suite());
        suite.addTest(jmathlibtests.toolbox.deprecated.testIsstr.suite());

	    return suite;
	}
}
