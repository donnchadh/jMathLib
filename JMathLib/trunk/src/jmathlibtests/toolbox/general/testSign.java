package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testSign extends TestCase {
	protected Interpreter ml;
	
    public testSign(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testSign.class);
	}

	public void testSign01() {
        ml.executeExpression("a=sign(1);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}

    public void testSign02() {
        ml.executeExpression("a=sign(3);");
        assertTrue(1 == ml.getScalarValueRe("a"));
    }

    public void testSign03() {
        ml.executeExpression("a=sign(-1);");
        assertTrue(-1.0 == ml.getScalarValueRe("a"));
    }

    public void testSign04() {
        ml.executeExpression("a=sign(-111);");
        assertTrue(-1.0 == ml.getScalarValueRe("a"));
    }

    public void testSign05() {
        ml.executeExpression("a=sign(0);");
        assertTrue(0.0 == ml.getScalarValueRe("a"));
    }


}
