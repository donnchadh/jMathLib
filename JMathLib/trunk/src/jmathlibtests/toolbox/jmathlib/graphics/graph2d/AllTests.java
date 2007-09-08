package jmathlibtests.toolbox.jmathlib.graphics.graph2d;

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
		TestSuite suite= new TestSuite("2D graphics functions");

		/* include subdirectories here */
		// none
        
	    /* include tests in this directory here */
		//suite.addTest(jmathlibtests.toolbox.jmathlib.graphics.graph2d.testMeshgrid.suite());

	    return suite;
	}
}