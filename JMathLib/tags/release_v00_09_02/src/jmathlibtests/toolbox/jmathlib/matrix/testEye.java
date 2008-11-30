package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testEye extends TestCase {
	protected Interpreter ml;
	
    public testEye(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testEye.class);
	}

    /****** eye() **********************************************************/
	public void testEye01() {
        ml.executeExpression("a=eye(1);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}
    
    public void testEye02() {
        ml.executeExpression("b=eye(1,1)");
		assertTrue(1 == ml.getScalarValueRe("b"));
	}
    public void testEye03() {
        double[][] a = {{1.0, 0.0, 0.0},{0.0, 1.0, 0.0},{0.0, 0.0, 1.0}};
        double[][] b = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.executeExpression("z = eye(3)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}
    public void testEye04() {
        double[][] a = {{1.0, 0.0, 0.0},{0.0, 1.0, 0.0},{0.0, 0.0, 1.0}};
        double[][] b = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.executeExpression("zz = eye(3,3)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("zz")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("zz")));
	}
    public void testEye05() {
        double[][] a = {{1.0, 0.0, 0.0},{0.0, 1.0, 0.0}};
        double[][] b = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.executeExpression("z = eye(2,3)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}
    public void testEye06() {
        double[][] a = {{1.0, 0.0},{0.0, 1.0},{0.0, 0.0}};
        double[][] b = {{0.0, 0.0},{0.0, 0.0},{0.0, 0.0}};
        ml.executeExpression("z = eye(3,2)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}
    public void testEye07() {
        double[][] a = {{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}};
        double[][] b = {{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}};
        ml.executeExpression("z = eye(1,10)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}
    public void testEye08() {
        double[][] a = {{1.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0}};
        double[][] b = {{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0}};
        ml.executeExpression("z = eye(10,1)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}
 
}