package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testXor extends TestCase {
	protected Interpreter ml;
	
    public testXor(String name) {
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
		return new TestSuite(testXor.class);
	}

    /****** xor() ***********************************************************/
	public void testXor01() {
        ml.executeExpression("a=xor(1,1);");
		assertTrue(0 == ml.getScalarValueRe("a"));
        assertTrue(0 == ml.getScalarValueIm("a"));
	}
    public void testXor02() {
        ml.executeExpression("b=xor(1,0)");
		assertTrue(1 == ml.getScalarValueRe("b"));
        assertTrue(0 == ml.getScalarValueIm("b"));
	}
    public void testXor03() {
        ml.executeExpression("b=xor(0,1)");
		assertTrue(1 == ml.getScalarValueRe("b"));
        assertTrue(0 == ml.getScalarValueIm("b"));
    }
    public void testXor04() {
        ml.executeExpression("b=xor(0,0)");
		assertTrue(0 == ml.getScalarValueRe("b"));
        assertTrue(0 == ml.getScalarValueIm("b"));
	}
    public void testXor05() {
        double[][] a = {{-1.0, 3.0, 0.0},{0.0, 10.0, 0.5},{0.0, 0.0, 0.0}};
        double[][] b = {{ 0.0, 7.0, 0.0},{0.0,  0.0, 0.0},{6.0, 0.0, 7.0}};
        double[][] c = {{ 1.0, 0.0, 0.0},{0.0,  1.0, 1.0},{1.0, 0.0, 1.0}};
        double[][] d = {{ 0.0, 0.0, 0.0},{0.0,  0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.setArray("a", a, d);
        ml.setArray("b", b, d);
        ml.executeExpression("z = xor(a,b)");
 		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(d, ml.getArrayValueIm("z")));
	}
 
}