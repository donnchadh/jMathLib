package jmathlibtests.toolbox.jmathlib.internal;

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
		TestSuite suite= new TestSuite("internal functions");

		/* include subdirectories here */
		// none
        
	    /* include tests in this directory here */
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testAbs.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testAcos.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testAcosh.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testAsin.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testAsinh.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testAtan.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testAtan2.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testAtanh.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testCeil.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testCol.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testColcount.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testConj.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testCos.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testCosh.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testFloor.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testIdentity.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testImag.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testLeft.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testLn.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testLog.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testMid.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testMin.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testMinMax.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testRight.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testRound.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testRow.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testRowcount.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testSin.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testSinh.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testSqrt.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testTan.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.internal.testTanh.suite());

	    return suite;
	}
}