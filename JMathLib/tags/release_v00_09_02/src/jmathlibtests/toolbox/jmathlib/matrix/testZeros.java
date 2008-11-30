package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testZeros extends TestCase {
	protected Interpreter ml;
	
    public testZeros(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testZeros.class);
	}

    /****** zeros() **********************************************************/
	public void testZeros01() {
        ml.executeExpression("a=zeros(1);");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}
    
    public void testZeros02() {
        ml.executeExpression("b=zeros(1,1)");
		assertTrue(0 == ml.getScalarValueRe("b"));
	}
    public void testZeros03() {
        double[][] a = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.executeExpression("z = zeros(3)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueIm("z")));
	}
    public void testZeros04() {
        double[][] a = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.executeExpression("zz = zeros(3,3)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("zz")));
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueIm("zz")));
	}
    public void testZeros05() {
        double[][] a = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
        ml.executeExpression("z = zeros(2,3)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueIm("z")));
	}
    public void testZeros06() {
        double[][] a = {{0.0, 0.0},{0.0, 0.0},{0.0, 0.0}};
        ml.executeExpression("z = zeros(3,2)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueIm("z")));
	}
    public void testZeros07() {
        double[][] a = {{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}};
        ml.executeExpression("z = zeros(1,10)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueIm("z")));
	}
    public void testZeros08() {
        double[][] a = {{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0},{0.0}};
        ml.executeExpression("z = zeros(10,1)");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueIm("z")));
	}
 
}