package jmathlibtests.core.tokens.numbertokens;

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
		TestSuite suite= new TestSuite("number tokens");

	    /* include tests in this directory here */
        suite.addTest(jmathlibtests.core.tokens.numbertokens.testDoubleNumberToken.suite());
        suite.addTest(jmathlibtests.core.tokens.numbertokens.testInt8NumberToken.suite());
        suite.addTest(jmathlibtests.core.tokens.numbertokens.testUInt8NumberToken.suite());

	    return suite;
	}
}
