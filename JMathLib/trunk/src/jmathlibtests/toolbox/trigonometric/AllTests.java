package jmathlibtests.toolbox.trigonometric;

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
		TestSuite suite= new TestSuite("trigonometric functions");

		/* include subdirectories here */
		// none
        
	    /* include tests in this directory here */
        suite.addTest(jmathlibtests.toolbox.trigonometric.testAcos.suite());
        suite.addTest(jmathlibtests.toolbox.trigonometric.testAcosh.suite());
        suite.addTest(jmathlibtests.toolbox.trigonometric.testAsin.suite());
        suite.addTest(jmathlibtests.toolbox.trigonometric.testAsinh.suite());
        suite.addTest(jmathlibtests.toolbox.trigonometric.testAtan.suite());
        suite.addTest(jmathlibtests.toolbox.trigonometric.testAtan2.suite());
        suite.addTest(jmathlibtests.toolbox.trigonometric.testAtanh.suite());
        suite.addTest(jmathlibtests.toolbox.trigonometric.testCos.suite());
        suite.addTest(jmathlibtests.toolbox.trigonometric.testCosh.suite());
        suite.addTest(jmathlibtests.toolbox.trigonometric.testDegToGrad.suite());
        suite.addTest(jmathlibtests.toolbox.trigonometric.testSin.suite());
        suite.addTest(jmathlibtests.toolbox.trigonometric.testSinh.suite());
        suite.addTest(jmathlibtests.toolbox.trigonometric.testTan.suite());
        suite.addTest(jmathlibtests.toolbox.trigonometric.testTanh.suite());

	    return suite;
	}
}
