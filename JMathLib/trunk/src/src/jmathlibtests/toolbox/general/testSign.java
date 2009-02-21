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
    protected void tearDown() {
        ml = null;
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

    public void testSign06() {
        ml.executeExpression("a=sign(5i);");
        assertTrue(0.0 == ml.getScalarValueRe("a"));
        assertTrue(1.0 == ml.getScalarValueIm("a"));
    }

    public void testSign07() {
        ml.executeExpression("a=sign(-55i);");
        assertTrue(0.0  == ml.getScalarValueRe("a"));
        assertTrue(-1.0 == ml.getScalarValueIm("a"));
    }

    public void testSign08() {
        ml.executeExpression("a=sign(3+4i);");
        assertEquals(0.6, ml.getScalarValueRe("a"), 0.01);
        assertEquals(0.8, ml.getScalarValueIm("a"), 0.01);
    }


}
