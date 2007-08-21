package jmathlibtests.toolbox.symbolic;

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
		TestSuite suite= new TestSuite("symbolic functions");

		/* include subdirectories here */
		// none
        
	    /* include tests in this directory here */
		suite.addTest(jmathlibtests.toolbox.symbolic.testExpand.suite());
		suite.addTest(jmathlibtests.toolbox.symbolic.testSimplify.suite());
		suite.addTest(jmathlibtests.toolbox.symbolic.testFactorize.suite());
		suite.addTest(jmathlibtests.toolbox.symbolic.testDerivative.suite());
		suite.addTest(jmathlibtests.toolbox.symbolic.testIntegral.suite());

	    return suite;
	}
}
