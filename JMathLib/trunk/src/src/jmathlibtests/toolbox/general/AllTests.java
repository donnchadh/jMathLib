package jmathlibtests.toolbox.general;

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
		TestSuite suite= new TestSuite("general functions");

		/* include subdirectories here */
		// none
        
	    /* include tests in this directory here */
		suite.addTest(jmathlibtests.toolbox.general.testBeep.suite());
		suite.addTest(jmathlibtests.toolbox.general.testBitAnd.suite());
		suite.addTest(jmathlibtests.toolbox.general.testBitOr.suite());
		suite.addTest(jmathlibtests.toolbox.general.testBitShift.suite());
		suite.addTest(jmathlibtests.toolbox.general.testBitXOr.suite());
        suite.addTest(jmathlibtests.toolbox.general.testClass.suite());
		suite.addTest(jmathlibtests.toolbox.general.testClear.suite());
		suite.addTest(jmathlibtests.toolbox.general.testClock.suite());
		suite.addTest(jmathlibtests.toolbox.general.testCombinations.suite());
		suite.addTest(jmathlibtests.toolbox.general.testComplex.suite());
		suite.addTest(jmathlibtests.toolbox.general.testDate.suite());
		suite.addTest(jmathlibtests.toolbox.general.testFactor.suite());
        suite.addTest(jmathlibtests.toolbox.general.testFft.suite());
		suite.addTest(jmathlibtests.toolbox.general.testFibonacci.suite());
		suite.addTest(jmathlibtests.toolbox.general.testFix.suite());
		suite.addTest(jmathlibtests.toolbox.general.testGlobal.suite());
		suite.addTest(jmathlibtests.toolbox.general.testHarmonic.suite());
        suite.addTest(jmathlibtests.toolbox.general.testImag.suite());
        suite.addTest(jmathlibtests.toolbox.general.testIscell.suite());
        suite.addTest(jmathlibtests.toolbox.general.testIschar.suite());
		suite.addTest(jmathlibtests.toolbox.general.testIsmatrix.suite());
		suite.addTest(jmathlibtests.toolbox.general.testIsnumeric.suite());
		suite.addTest(jmathlibtests.toolbox.general.testIsPrime.suite());
		suite.addTest(jmathlibtests.toolbox.general.testIssquare.suite());
		suite.addTest(jmathlibtests.toolbox.general.testIsstruct.suite());
		suite.addTest(jmathlibtests.toolbox.general.testLength.suite());
		suite.addTest(jmathlibtests.toolbox.general.testLinspace.suite());
        suite.addTest(jmathlibtests.toolbox.general.testMod.suite());
		suite.addTest(jmathlibtests.toolbox.general.testPerformFunction.suite());
		suite.addTest(jmathlibtests.toolbox.general.testPermutations.suite());
		suite.addTest(jmathlibtests.toolbox.general.testPrimes.suite());
		suite.addTest(jmathlibtests.toolbox.general.testRand.suite());
        suite.addTest(jmathlibtests.toolbox.general.testReal.suite());
        suite.addTest(jmathlibtests.toolbox.general.testRem.suite());
		suite.addTest(jmathlibtests.toolbox.general.testSetPFileCaching.suite());
		suite.addTest(jmathlibtests.toolbox.general.testSign.suite());
		suite.addTest(jmathlibtests.toolbox.general.testSize.suite());
		suite.addTest(jmathlibtests.toolbox.general.testStruct.suite());
		suite.addTest(jmathlibtests.toolbox.general.testTemplate.suite());
		suite.addTest(jmathlibtests.toolbox.general.testWho.suite());
        suite.addTest(jmathlibtests.toolbox.general.testWhos.suite());

	    return suite;
	}
}
