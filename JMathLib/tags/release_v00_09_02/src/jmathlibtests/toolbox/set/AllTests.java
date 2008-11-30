package jmathlibtests.toolbox.set;

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
		TestSuite suite= new TestSuite("set functions");

		/* include subdirectories here */
		//suite.addTest(MathLib.Tools.TestSuite.Functions.Control.system.AllTests.suite());
        
	    /* include tests in this directory here */
        suite.addTest(jmathlibtests.toolbox.set.testComplement.suite());
		suite.addTest(jmathlibtests.toolbox.set.testCreate_set.suite());

	    return suite;
	}
}
