package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testExpression extends TestCase {
	protected Interpreter ml;
	
    public testExpression(String name) {
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
		return new TestSuite(testExpression.class);
	}

	/************* simple expressions ****************************************/
    public void test001() {
        ml.executeExpression("a=2+3");
		assertTrue(5 == ml.getScalarValueRe("a"));
	}

    // test case for bug: 859689  meshgrid number of output arguments!=2
    // solution: the evaluation of expressions did not work correctly for matrices on the left handside
    public void test002() {
        ml.executeExpression("[x,y]=meshgrid(3,4)");
        assertTrue(3 == ml.getScalarValueRe("x"));
        assertTrue(4 == ml.getScalarValueRe("y"));
    }

												    
}