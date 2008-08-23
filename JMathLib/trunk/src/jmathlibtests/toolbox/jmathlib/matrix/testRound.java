package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testRound extends TestCase {
	protected Interpreter ml;
	
    public testRound(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run(suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testRound.class);
	}

	public void testRound01() {
        ml.executeExpression("a=round(3.2)");
        assertTrue(3.0 == ml.getScalarValueRe("a"));
	}
    public void testRound02() {
        ml.executeExpression("a=round(3.5)");
        assertTrue(4.0 == ml.getScalarValueRe("a"));
    }
    public void testRound03() {
        ml.executeExpression("a=round(0.4)");
        assertTrue(0.0 == ml.getScalarValueRe("a"));
    }
    public void testRound04() {
        ml.executeExpression("a=round(0.5)");
        assertTrue(1.0 == ml.getScalarValueRe("a"));
    }
    public void testRound05() {
        ml.executeExpression("a=round(0.7)");
        assertTrue(1.0 == ml.getScalarValueRe("a"));
    }
    public void testRound06() {
        ml.executeExpression("a=round(1.7)");
        assertTrue(2.0 == ml.getScalarValueRe("a"));
    }
    public void testRound07() {
        ml.executeExpression("a=round(1.4)");
        assertTrue(1.0 == ml.getScalarValueRe("a"));
    }
    public void testRound08() {
        ml.executeExpression("a=round(-0.3)");
        assertTrue(0.0 == ml.getScalarValueRe("a"));
    }
    public void testRound09() {
        ml.executeExpression("a=round(-0.5)");
        assertTrue(-1.0 == ml.getScalarValueRe("a"));
    }
    public void testRound10() {
        ml.executeExpression("a=round(-0.6)");
        assertTrue(-1.0 == ml.getScalarValueRe("a"));
    }
    public void testRound11() {
        ml.executeExpression("a=round(-1.3)");
        assertTrue(-1.0 == ml.getScalarValueRe("a"));
    }
    public void testRound12() {
        ml.executeExpression("a=round(-1.6)");
        assertTrue(-2.0 == ml.getScalarValueRe("a"));
    }

    
    public void testRound13() {
        ml.executeExpression("a=round(-1.6i)");
        assertTrue(-2.0 == ml.getScalarValueIm("a"));
    }
    public void testRound14() {
        ml.executeExpression("a=round(-0.6i)");
        assertTrue(-1.0 == ml.getScalarValueIm("a"));
    }
    public void testRound15() {
        ml.executeExpression("a=round(-0.4i)");
        assertTrue(0.0 == ml.getScalarValueIm("a"));
    }
    public void testRound16() {
        ml.executeExpression("a=round(1.6i)");
        assertTrue(2.0 == ml.getScalarValueIm("a"));
    }

	

}