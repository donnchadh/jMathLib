package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testAddSubOperatorToken extends TestCase {
	protected Interpreter ml;
	
    public testAddSubOperatorToken(String name) {
		super(name);
	}
	public static void main (String[] args) {
	    jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testAddSubOperatorToken.class);
	}

	/************* simple expressions ****************************************/
    public void testAdd001() {
        ml.executeExpression("a=2+3");
		assertTrue(5 == ml.getScalarValueRe("a"));
	}


												    
}