package jmathlibtests.core.graphics.axes.plotObjects;

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
		TestSuite suite= new TestSuite("core/graphics/axes/plotObjects");

		/* include subdirectories here */
		//suite.addTest(jmathlibtests.core.graphics.axes.coreObjects.AllTests.suite());
        //suite.addTest(jmathlibtests.core.graphics.axes.plotObjects.AllTests.suite());
        

		/* include tests in this directory here */
		//suite.addTest(MathLib.Tools.TestSuite.Interpreter.testParser.suite());

	    return suite;
	}
}

