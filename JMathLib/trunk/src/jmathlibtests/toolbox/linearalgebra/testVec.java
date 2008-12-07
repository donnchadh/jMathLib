package jmathlibtests.toolbox.linearalgebra;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;


public class testVec extends TestCase {
	protected Interpreter ml;
	
    public testVec(String name) {
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
		return new TestSuite(testVec.class);
	}

	public void testVec01() {
        ml.executeExpression("a=vec(3)");
        assertTrue(3 == ml.getScalarValueRe("a"));
	}
	public void testVec02() {
        double[][] a = { {4.0} ,{5.0}};
        ml.executeExpression("a=vec([4,5])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
	}
	public void testVec03() {
        double[][] a = { {6.0} ,{7.0}};
        ml.executeExpression("a=vec([6;7])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
	}
	public void testVec04() {
        double[][] a = { {0.0} ,{4.0} ,{7.0}};
        ml.executeExpression("a = vec([0,4,7])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
	}
	public void testVec05() {
        double[][] a = {{ 3.0},{1.0},{7.0},{5},{0},{6}};
        ml.executeExpression("a = vec([3,7,0;1,5,6])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
	}
 
}