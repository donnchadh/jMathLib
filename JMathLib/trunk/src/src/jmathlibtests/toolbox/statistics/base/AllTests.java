package jmathlibtests.toolbox.statistics.base;

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
		TestSuite suite= new TestSuite("statistics base functions");

		/* include subdirectories here */
		// none
        
	    /* include tests in this directory here */
		suite.addTest(jmathlibtests.toolbox.statistics.base.testMean.suite());
        suite.addTest(jmathlibtests.toolbox.statistics.base.testStd.suite());

	    return suite;
	}
}
