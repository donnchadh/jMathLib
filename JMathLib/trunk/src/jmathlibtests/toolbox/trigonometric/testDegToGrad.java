package jmathlibtests.toolbox.trigonometric;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testDegToGrad extends TestCase {
	protected Interpreter ml;
	
    public testDegToGrad(String name) {
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
		return new TestSuite(testDegToGrad.class);
	}

	public void testDegToGrad01() {
        ml.executeExpression("a=degtograd(90);");
		assertTrue(Math.abs(100 - ml.getScalarValueRe("a")) < 0.001);
	}

    public void testDegToRad01() {
        ml.executeExpression("a=degtorad(180);");
        assertTrue(Math.abs(Math.PI - ml.getScalarValueRe("a")) < 0.001);
    }

    public void testGradToDeg01() {
        ml.executeExpression("a=gradtodeg(100);");
        assertTrue(Math.abs(90 - ml.getScalarValueRe("a")) < 0.001);
    }

    public void testGradToRad01() {
        ml.executeExpression("a=gradtorad(200);");
        assertTrue(Math.abs(Math.PI - ml.getScalarValueRe("a")) < 0.001);
    }

    public void testRadToDeg01() {
        ml.executeExpression("a=radtodeg(3.14159);");
        assertTrue(Math.abs(180 - ml.getScalarValueRe("a")) < 0.01);
    }

    public void testRadToGrad01() {
        ml.executeExpression("a=radtograd(3.14159);");
        assertTrue(Math.abs(200 - ml.getScalarValueRe("a")) < 0.01);
    }

}
