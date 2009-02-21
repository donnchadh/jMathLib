package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testReal extends TestCase {
	protected Interpreter ml;
	
    public testReal(String name) {
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
		return new TestSuite(testReal.class);
	}

    /////////////////////////////////////////////////////////////////
	public void testReal01() {
        ml.executeExpression("a=5+6i");
        ml.executeExpression("b=real(a)");
		assertTrue(5 == ml.getScalarValueRe("b"));
	}

    public void testReal02() {
        ml.executeExpression("a=55");
        ml.executeExpression("b=real(a)");
        assertTrue(55 == ml.getScalarValueRe("b"));
    }

    public void testReal03() {
        ml.executeExpression("a=56i");
        ml.executeExpression("b=real(a)");
        assertTrue(0 == ml.getScalarValueRe("b"));
    }



}
