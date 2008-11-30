package jmathlibtests.core.interpreter;

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
		TestSuite suite= new TestSuite("interpreter");

		/* include subdirectories here */
		/* none */
        
	    /* include tests in this directory here */
		suite.addTest(jmathlibtests.core.interpreter.testInterpreter.suite());
		suite.addTest(jmathlibtests.core.interpreter.testLexicalAnalyser.suite());
		suite.addTest(jmathlibtests.core.interpreter.testParser.suite());
		suite.addTest(jmathlibtests.core.interpreter.testScanner.suite());


	    return suite;
	}
}
