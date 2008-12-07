package jmathlibtests.toolbox.linearalgebra;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;


public class testTrace extends TestCase {
	protected Interpreter ml;
	
    public testTrace(String name) {
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
		return new TestSuite(testTrace.class);
	}

	public void testTrace01() {
        ml.executeExpression("a=trace(3)");
        assertTrue(3 == ml.getScalarValueRe("a"));
	}
	public void testTrace02() {
        ml.executeExpression("a=trace([4,5])");
		assertTrue(4 == ml.getScalarValueRe("a"));
	}
	public void testTraceg03a() {
        ml.executeExpression("a=trace([5;6;7])");
		assertTrue(5 == ml.getScalarValueRe("a"));
	}
	public void testTraceg03b() {
        ml.executeExpression("a=trace([5;6;7]')");
		assertTrue(5 == ml.getScalarValueRe("a"));
	}
	public void testTrace04() {
        ml.executeExpression("a = trace([3,7,0;1,4,0;6,0,7])");
		assertTrue(14 == ml.getScalarValueRe("a"));
	}
	public void testTrace05() {
        ml.executeExpression("a = trace([3,7,0;1,5,6])");
		assertTrue(8 == ml.getScalarValueRe("a"));
	}
 
}