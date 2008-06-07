package jmathlibtests.toolbox.jmathlib.internal;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testCeil extends TestCase {
	protected Interpreter ml;
	
    public testCeil(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testCeil.class);
	}

	public void testCeil01() {
        ml.executeExpression("a=ceil(3.5);");
		assertTrue(4.0 == ml.getScalarValueRe("a"));
	}
    public void testCeil02() {
        ml.executeExpression("a=ceil(-3.5);");
		assertTrue(-3.0 == ml.getScalarValueRe("a"));
	}
    public void testCeil03() {
        ml.executeExpression("a=ceil(5);");
        assertTrue(5.0 == ml.getScalarValueRe("a"));
    }

    public void testCeil04() {
        // test check for number of parameters
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("m=ceil(4,5) ");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

}
