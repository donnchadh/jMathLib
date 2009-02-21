package jmathlibtests.toolbox.jmathlib.internal;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testFloor extends TestCase {
	protected Interpreter ml;
	
    public testFloor(String name) {
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
		return new TestSuite(testFloor.class);
	}

	public void testFloor01() {
        ml.executeExpression("a=floor(10.5);");
		assertTrue(10.0 == ml.getScalarValueRe("a"));
	}
    
    public void testFloor02() {
        ml.executeExpression("a=floor(-33.5);");
		assertTrue(-34.0 == ml.getScalarValueRe("a"));
	}

    public void testFloor03() {
        ml.executeExpression("a=floor(5);");
        assertTrue(5.0 == ml.getScalarValueRe("a"));
    }

    public void testFloor04() {
        // test check for number of parameters
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("m=floor(4,5) ");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    public void testFloor05() {
        // test check for number of parameters
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("m=floor(45) ");
        }
        catch (Exception e)
        {
            assertTrue(false);
            return;
        }
        assertTrue(true);
    }

}
