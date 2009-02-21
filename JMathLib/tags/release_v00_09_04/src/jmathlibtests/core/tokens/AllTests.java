package jmathlibtests.core.tokens;

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
		TestSuite suite= new TestSuite("tokens");

        /* include subdirectories here */
        suite.addTest(jmathlibtests.core.tokens.numbertokens.AllTests.suite());

	    /* include tests in this directory here */
        suite.addTest(jmathlibtests.core.tokens.testAddSubOperatorToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testAssignmentOperatorToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testBinaryOperatorToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testCaseToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testCharToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testColonOperatorToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testCommandToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testConditionToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testDataToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testDelimiterToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testDotOperatorToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testExpression.suite());
        suite.addTest(jmathlibtests.core.tokens.testForOperatorToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testFunctionToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testIfThenOperatorToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testImaginaryNumberToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testMatrixToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testMulDivOperatorToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testNumberToken.suite());
		suite.addTest(jmathlibtests.core.tokens.testNumberTokenComplex.suite());
        suite.addTest(jmathlibtests.core.tokens.testOperandToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testOperatorToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testPowerOperatorToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testRelationOperatorToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testScalarToken.suite());
		suite.addTest(jmathlibtests.core.tokens.testSwitchOperatorToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testUnaryOperatorToken.suite());
        suite.addTest(jmathlibtests.core.tokens.testVariableToken.suite());
		suite.addTest(jmathlibtests.core.tokens.testWhileOperatorToken.suite());

	    return suite;
	}
}
