package jmathlibtests.toolbox.linearalgebra;

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
		TestSuite suite= new TestSuite("linear algebra functions");

		/* include subdirectories here */
		// none
        
	    /* include tests in this directory here */
		suite.addTest(jmathlibtests.toolbox.linearalgebra.testTrace.suite());
		suite.addTest(jmathlibtests.toolbox.linearalgebra.testVec.suite());

	    return suite;
	}
}