package jmathlibtests.toolbox.control.system;

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
		TestSuite suite= new TestSuite("control system functions");

		/* include subdirectories here */
		// none
        
	    /* include tests in this directory here */
		suite.addTest(jmathlibtests.toolbox.control.system.testAbcddim.suite());
		suite.addTest(jmathlibtests.toolbox.control.system.testIs_abcd.suite());

	    return suite;
	}
}
