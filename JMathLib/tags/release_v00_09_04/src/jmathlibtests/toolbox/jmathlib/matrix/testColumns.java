package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testColumns extends TestCase {
	protected Interpreter ml;
	
    public testColumns(String name) {
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
		return new TestSuite(testColumns.class);
	}

	public void testColumns01() {
        ml.executeExpression("a=columns(3)");
        assertTrue(ml.getScalarValueRe("a") == 1);
	}
	public void testColumns02() {
        ml.executeExpression("a=columns([1,2,3,4,5,6])");
        assertTrue(ml.getScalarValueRe("a") == 6);
	}
	public void testColumns03() {
        ml.executeExpression("a=columns([1,2,3;4,5,6])");
        assertTrue(ml.getScalarValueRe("a") == 3);
	}
	public void testColumns04() {
        ml.executeExpression("a=columns([1,2;3,4;5,6])");
        assertTrue(ml.getScalarValueRe("a") == 2);
	}
	public void testColumns05() {
        ml.executeExpression("a=columns([1,2,3,4,5,6]')");
        assertTrue(ml.getScalarValueRe("a") == 1);
	}

}