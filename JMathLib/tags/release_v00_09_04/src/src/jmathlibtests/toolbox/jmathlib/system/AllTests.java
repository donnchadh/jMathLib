package jmathlibtests.toolbox.jmathlib.system;

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
		TestSuite suite= new TestSuite("system functions");

		/* include subdirectories here */
		// none
        
	    /* include tests in this directory here */
		suite.addTest(jmathlibtests.toolbox.jmathlib.system.testGetDebug.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.system.testSetDebug.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.system.testUsage.suite());

	    return suite;
	}
}
