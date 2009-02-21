package jmathlibtests.toolbox.statistics;

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
		TestSuite suite= new TestSuite("statistics functions");

		/* include subdirectories here */
        suite.addTest(jmathlibtests.toolbox.statistics.base.AllTests.suite());
        suite.addTest(jmathlibtests.toolbox.statistics.distributions.AllTests.suite());
        suite.addTest(jmathlibtests.toolbox.statistics.models.AllTests.suite());
        suite.addTest(jmathlibtests.toolbox.statistics.tests.AllTests.suite());
        
	    /* include tests in this directory here */
		suite.addTest(jmathlibtests.toolbox.statistics.testAverage.suite());

	    return suite;
	}
}
