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
    protected void tearDown() {
        ml = null;
    }

	public static Test suite() {
		return new TestSuite(testChol.class);
	}

    /****** not() ************************************************************/
	public void testNot01() {
        ml.executeExpression("aa=not(1);");
		assertTrue(!ml.getScalarValueBoolean("aa"));
	}
    public void testNot02() {
        ml.executeExpression("aaa=not(0)");
		assertTrue(ml.getScalarValueBoolean("aaa"));
	}
    public void testNot03() {
        double[][] a = {{1.0, 2.0, 3.0},{-1.0, 0.0, 0.0},{11.23, -1.5, 0.0}};
        boolean[][] b = {{false, false, false},{ false, true, true},{ false,false, true}};
        ml.setArray("a", a, null);
        ml.executeExpression("setdebug(1)");
        ml.executeExpression("who, z = not(a)");
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueBoolean("z")));
	}

}