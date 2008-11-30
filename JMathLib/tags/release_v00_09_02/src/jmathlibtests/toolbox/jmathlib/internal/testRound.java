package jmathlibtests.toolbox.jmathlib.internal;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testRound extends TestCase {
	protected Interpreter ml;
	
    public testRound(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testRound.class);
	}

	public void testRound01() {
        ml.executeExpression("a=round(3.4);");
		assertTrue(3.0 == ml.getScalarValueRe("a"));
	}

    public void testRound02() {
        ml.executeExpression("a=round(3.5);");
        assertTrue(4.0 == ml.getScalarValueRe("a"));
    }
    
    public void testRound03() {
        ml.executeExpression("a=round(3.8);");
        assertTrue(4.0 == ml.getScalarValueRe("a"));
    }

    public void testRound04() {
        ml.executeExpression("a=round(-3.4);");
        assertTrue(-3.0 == ml.getScalarValueRe("a"));
    }

    public void testRound05() {
        ml.executeExpression("a=round(-3.5);");
        assertTrue(-4.0 == ml.getScalarValueRe("a"));
    }
    
    public void testRound06() {
        ml.executeExpression("a=round(-3.8);");
        assertTrue(-4.0 == ml.getScalarValueRe("a"));
    }

    public void testRound10() {
        // test check for number of parameters
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("m=round(4,5) ");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

}
