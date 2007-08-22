package jmathlibtests.toolbox.control;

import jmathlib.tools.junit.framework.*;

/**
 * TestSuite that runs all the tests
 *
 */
public class AllTests {

	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run(suite());
	}
	public static Test suite ( ) {
		TestSuite suite= new TestSuite("control functions");

		/* include subdirectories here */
        suite.addTest(jmathlibtests.toolbox.control.base.AllTests.suite());
        suite.addTest(jmathlibtests.toolbox.control.hinf.AllTests.suite());
        suite.addTest(jmathlibtests.toolbox.control.system.AllTests.suite());
        
	    /* include tests in this directory here */
		//suite.addTest(MathLib.Tools.TestSuite.Functions.Control.testXXX.suite());

	    return suite;
	}
}
