package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testOnes extends TestCase {
	protected Interpreter ml;
	
    public testOnes(String name) {
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
		return new TestSuite(testOnes.class);
	}

    /****** ones() ***********************************************************/
	public void testOnes01() {
        ml.executeExpression("a=ones(1);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}
    public void testOnes02() {
        ml.executeExpression("b=ones(1,1)");
		assertTrue(1 == ml.getScalarValueRe("b"));
	}
    public void testOnes03() {
        double[][] a = {{1.0, 1.0, 1.0},{1.0, 1.0, 1.0},{1.0, 1.0, 1.0}};
        double[][] b = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.executeExpression("z = ones(3)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}
    public void testOnes04() {
        double[][] a = {{1.0, 1.0, 1.0},{1.0, 1.0, 1.0},{1.0, 1.0, 1.0}};
        double[][] b = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.executeExpression("zz = ones(3,3)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("zz")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("zz")));
	}
    public void testOnes05() {
        double[][] a = {{1.0, 1.0, 1.0},{1.0, 1.0, 1.0}};
        double[][] b = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.executeExpression("z = ones(2,3)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}
    public void testOnes06() {
        double[][] a = {{1.0, 1.0},{1.0, 1.0},{1.0, 1.0}};
        double[][] b = {{0.0, 0.0},{0.0, 0.0},{0.0, 0.0}};
        ml.executeExpression("z = ones(3,2)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}
    public void testOnes07() {
        double[][] a = {{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0}};
        double[][] b = {{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}};
        ml.executeExpression("z = ones(1,10)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}
    public void testOnes08() {
        double[][] a = {{1.0},{1.0},{1.0},{1.0},{1.0},{1.0},{1.0},{1.0},{1.0},{1.0}};
        double[][] b = {{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0}};
        ml.executeExpression("z = ones(10,1)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}
 
}