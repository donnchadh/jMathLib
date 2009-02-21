package jmathlibtests.core.interpreter;

import jmathlib.tools.junit.framework.*;
import jmathlib.core.interpreter.*;
import jmathlibtests.*;

public class testContext extends TestCase {
	protected Interpreter ml1;
    protected Interpreter ml2;
	
    public testContext(String name) {
		super(name);
	}
	public static void main (String[] args) {
	    jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml1 = new Interpreter(true);
        ml2 = new Interpreter(true);
	}
	
	protected void tearDown() {
	    ml1 = null;
        ml2 = null;
    }

	public static Test suite() {
		return new TestSuite(testContext.class);
	}

	/************* access methods ********************************************/
    public void testAccessMethods01() {
        // first instance
        ml1.executeExpression("a=1+1;");
		assertTrue(2 == ml1.getScalarValueRe("a"));

        // second instance
        ml2.executeExpression("a=55;");
        assertTrue(55 == ml2.getScalarValueRe("a"));

        // first instance: check if "a" has the still the same value
        assertTrue(2 == ml1.getScalarValueRe("a"));

    }



}