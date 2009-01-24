/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:  Stefan Mueller (stefan@held-mueller.de) 
 * (c) 2007, 2008, 2009   
 */

package jmathlibtests;

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
		TestSuite suite= new TestSuite("JMathLib Tests");

		/* include subdirectories here */
        suite.addTest(jmathlibtests.scripts.AllTests.suite());
        suite.addTest(jmathlibtests.core.AllTests.suite());
        suite.addTest(jmathlibtests.plugins.AllTests.suite());
        suite.addTest(jmathlibtests.resourcebundles.AllTests.suite());
		suite.addTest(jmathlibtests.toolbox.AllTests.suite());
        suite.addTest(jmathlibtests.ui.AllTests.suite());

		/* include tests in this directory here */
		//suite.addTest(MathLib.Tools.TestSuite.Interpreter.testParser.suite());

	    return suite;
	}
}

