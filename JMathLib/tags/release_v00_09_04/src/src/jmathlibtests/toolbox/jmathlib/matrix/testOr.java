package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testOr extends TestCase {
	protected Interpreter ml;
	
    public testOr(String name) {
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
		return new TestSuite(testOr.class);
	}

    /****** Or() ***********************************************************/
	public void testOr01() {
        ml.executeExpression("a=or(1,1);");
		assertTrue(1 == ml.getScalarValueRe("a"));
        assertTrue(0 == ml.getScalarValueIm("a"));
	}
    public void testOr02() {
        ml.executeExpression("b=or(1,0)");
		assertTrue(1 == ml.getScalarValueRe("b"));
        assertTrue(0 == ml.getScalarValueIm("b"));
	}
    public void testOr03() {
        ml.executeExpression("b=or(0,1)");
		assertTrue(1 == ml.getScalarValueRe("b"));
        assertTrue(0 == ml.getScalarValueIm("b"));
    }
    public void testOr04() {
        ml.executeExpression("b=or(0,0)");
		assertTrue(0 == ml.getScalarValueRe("b"));
        assertTrue(0 == ml.getScalarValueIm("b"));
	}
    public void testOr05() {
        double[][] a = {{-1.0, 3.0, 0.0},{0.0, 10.0, 0.5},{0.0, 0.0, 0.0}};
        double[][] b = {{ 0.0, 7.0, 0.0},{0.0,  0.0, 0.0},{6.0, 0.0, 7.0}};
        double[][] c = {{ 1.0, 1.0, 0.0},{0.0,  1.0, 1.0},{1.0, 0.0, 1.0}};
        double[][] d = {{ 0.0, 0.0, 0.0},{0.0,  0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.setArray("a", a, d);
        ml.setArray("b", b, d);
        ml.executeExpression("z = or(a,b)");
 		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(d, ml.getArrayValueIm("z")));
	}
 
}