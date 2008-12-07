package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testMulDivOperatorToken extends TestCase {
	protected Interpreter ml;
	
    public testMulDivOperatorToken(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}
    protected void tearDown() {
        ml = null;
    }

	public static Test suite() {
		return new TestSuite(testMulDivOperatorToken.class);
	}

	/************* simple expressions ****************************************/
    public void test001() {
        ml.executeExpression("a=2*3");
		assertTrue(6.0 == ml.getScalarValueRe("a"));
	}

    public void test002() {
        ml.executeExpression("a=6/3");
        assertTrue(2.0 == ml.getScalarValueRe("a"));
    }

												    
}