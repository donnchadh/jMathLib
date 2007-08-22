package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testChol extends TestCase {
	protected Interpreter ml;
	
    public testChol(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testChol.class);
	}

    /****** not() ************************************************************/
	public void testNot01() {
        ml.executeExpression("a=not(1);");
		assertTrue(!ml.getScalarValueBoolean("a"));
	}
    public void testNot02() {
        ml.executeExpression("a=not(0)");
		assertTrue(ml.getScalarValueBoolean("a"));
	}
    public void testNot03() {
        double[][] a = {{1.0, 2.0, 3.0},{-1.0, 0.0, 0.0},{11.23, -1.5, 0.0}};
        boolean[][] b = {{false, false, false},{ false, true, true},{ false,false, true}};
        ml.setArray("a", a, null);
        ml.executeExpression("z = not(a)");
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueBoolean("z")));
	}

}