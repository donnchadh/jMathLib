package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testNot extends TestCase {
	protected Interpreter ml;
	
    public testNot(String name) {
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
		return new TestSuite(testNot.class);
	}

    /****** not() ************************************************************/
	public void testNot01() {
        ml.executeExpression("ab=not(1);");
		assertTrue(false == ml.getScalarValueBoolean("ab"));
	}
    public void testNot02() {
        ml.executeExpression("abb=not(0)");
		assertTrue(true == ml.getScalarValueBoolean("abb"));
	}
    public void testNot03() {
        double[][] a = {{1.0, 2.0, 3.0},{-1.0, 0.0, 0.0},{11.23, -1.5, 0.0}};
        boolean[][] b = {{false, false, false},{false, true, true},{false, false, true}};
        double[][] c = {{0.0, 0.0, 0.0},{ 0.0, 0.0, 0.0},{ 0.0,   0.0, 0.0}};
        ml.setArray("a", a, c);
        ml.executeExpression("z = not(a)");
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueBoolean("z")));
	}

}