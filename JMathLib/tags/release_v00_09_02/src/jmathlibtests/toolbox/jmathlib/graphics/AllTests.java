package jmathlibtests.toolbox.jmathlib.graphics;

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
		TestSuite suite= new TestSuite("graphics functions");

		/* include subdirectories here */
        suite.addTest(jmathlibtests.toolbox.jmathlib.graphics.graph2d.AllTests.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.graphics.graph3d.AllTests.suite());
        
	    /* include tests in this directory here */

	    return suite;
	}
}