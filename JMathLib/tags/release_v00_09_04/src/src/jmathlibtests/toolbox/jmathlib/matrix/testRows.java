package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testRows extends TestCase {
	protected Interpreter ml;
	
    public testRows(String name) {
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
		return new TestSuite(testRows.class);
	}

	public void testRows01() {
        ml.executeExpression("a=rows(3)");
        assertTrue(ml.getScalarValueRe("a") == 1);
	}
	public void testRows02() {
        ml.executeExpression("a=rows([1,2,3,4,5,6])");
        assertTrue(ml.getScalarValueRe("a") == 1);
	}
	public void testRows03() {
        ml.executeExpression("a=rows([1,2,3;4,5,6])");
        assertTrue(ml.getScalarValueRe("a") == 2);
	}
	public void testRows04() {
        ml.executeExpression("a=rows([1,2;3,4;5,6])");
        assertTrue(ml.getScalarValueRe("a") == 3);
	}
	public void testRows05() {
        ml.executeExpression("a=rows([1,2,3,4,5,6]')");
        assertTrue(ml.getScalarValueRe("a") == 6);
	}

}