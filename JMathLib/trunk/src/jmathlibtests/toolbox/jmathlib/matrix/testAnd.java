package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testAnd extends TestCase {
	protected Interpreter ml;
	
    public testAnd(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testAnd.class);
	}

    /****** And() ***********************************************************/
	public void testAnd01() {
        ml.executeExpression("a=and(1,1);");
		assertTrue(1 == ml.getScalarValueRe("a"));
        assertTrue(0 == ml.getScalarValueIm("a"));
	}
    public void testAnd02() {
        ml.executeExpression("b=and(1,0)");
		assertTrue(0 == ml.getScalarValueRe("b"));
        assertTrue(0 == ml.getScalarValueIm("b"));
	}
    public void testAnd03() {
        ml.executeExpression("b=and(0,1)");
		assertTrue(0 == ml.getScalarValueRe("b"));
        assertTrue(0 == ml.getScalarValueIm("b"));
    }
    public void testAnd04() {
        ml.executeExpression("b=and(0,0)");
		assertTrue(0 == ml.getScalarValueRe("b"));
        assertTrue(0 == ml.getScalarValueIm("b"));
	}
    public void testAnd05() {
        double[][] a = {{-1.0, 3.0, 0.0},{0.0, 10.0, 0.5},{0.0, 0.0, 0.0}};
        double[][] b = {{ 0.0, 7.0, 0.0},{1.0,  4.0, 0.0},{6.0, 0.0, 7.0}};
        double[][] c = {{ 0.0, 1.0, 0.0},{0.0,  1.0, 0.0},{0.0, 0.0, 0.0}};
        double[][] d = {{ 0.0, 0.0, 0.0},{0.0,  0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.setArray("a", a, d);
        ml.setArray("b", b, d);
        ml.executeExpression("z = and(a,b)");
 		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(d, ml.getArrayValueIm("z")));
	}
 
}