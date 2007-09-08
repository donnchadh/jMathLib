package jmathlibtests.toolbox.jmathlib;

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
		TestSuite suite= new TestSuite("jmathlib functions");

		/* include subdirectories here */
        suite.addTest(jmathlibtests.toolbox.jmathlib.graphics.AllTests.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.internal.AllTests.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.AllTests.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.system.AllTests.suite());

	    /* include tests in this directory here */

	    return suite;
	}
}