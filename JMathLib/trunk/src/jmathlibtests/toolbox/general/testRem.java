package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testRem extends TestCase {
	protected Interpreter ml;
	
    public testRem(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testRem.class);
	}

    //////////////////////////////////////////////////////////////////
	public void testRem01() {
        ml.executeExpression("a=rem(4,2);");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}

    public void testRem02() {
        ml.executeExpression("a=rem(5,2);");
        assertTrue(1 == ml.getScalarValueRe("a"));
    }

    public void testRem04() {
        ml.executeExpression("a=rem(-5,3);");
        assertTrue(-2 == ml.getScalarValueRe("a"));
    }


}
