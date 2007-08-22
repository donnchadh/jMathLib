package jmathlibtests.toolbox.jmathlib.matrix;

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
		TestSuite suite= new TestSuite("matrix functions");

		/* include subdirectories here */
		// none
        
	    /* include tests in this directory here */
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testAdjoint.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testAnd.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testAny.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testChol.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testColumns.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testCtranspose.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testCumprod.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testCumsum.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testDet.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testDiag.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testEq.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testEye.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testFliplr.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testFlipud.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testFind.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testGe.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testGt.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testIsreal.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testLe.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testLt.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testMax.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testMin.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testMinus.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testMldivide.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testMrdivide.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testMtimes.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testNe.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testNot.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testNumel.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testOnes.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testOr.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testPlus.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testRows.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testSort.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testSubAssign.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testSubMatrix.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testTimes.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testTranspose.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testUminus.suite());
        suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testUplus.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testXor.suite());
		suite.addTest(jmathlibtests.toolbox.jmathlib.matrix.testZeros.suite());

	    return suite;
	}
}