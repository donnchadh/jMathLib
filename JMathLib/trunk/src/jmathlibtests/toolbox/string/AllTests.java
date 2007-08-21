package jmathlibtests.toolbox.string;

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
		TestSuite suite= new TestSuite("string functions");

		/* include subdirectories here */
		// none
        
	    /* include tests in this directory here */
        suite.addTest(jmathlibtests.toolbox.string.testChar.suite());
        suite.addTest(jmathlibtests.toolbox.string.testDeblank.suite());
        suite.addTest(jmathlibtests.toolbox.string.testDouble.suite());
        suite.addTest(jmathlibtests.toolbox.string.testLower.suite());
		suite.addTest(jmathlibtests.toolbox.string.testNum2str.suite());
        suite.addTest(jmathlibtests.toolbox.string.testStr2num.suite());
        suite.addTest(jmathlibtests.toolbox.string.testUpper.suite());

	    return suite;
	}
}
