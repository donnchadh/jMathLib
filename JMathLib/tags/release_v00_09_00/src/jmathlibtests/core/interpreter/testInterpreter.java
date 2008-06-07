package jmathlibtests.core.interpreter;

import jmathlib.tools.junit.framework.*;
import jmathlib.core.interpreter.*;
import jmathlibtests.*;

public class testInterpreter extends TestCase {
	protected Interpreter ml;
	
    public testInterpreter(String name) {
		super(name);
	}
	public static void main (String[] args) {
	    jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testInterpreter.class);
	}

	/************* access methods ********************************************/
    public void testAccessMethods01() {
        ml.executeExpression("a=1+1;");
		assertTrue(2 == ml.getScalarValueRe("a"));
	}
    public void testAccessMethods02() {
        ml.executeExpression("a=3.45;");
		assertTrue(3.45 == ml.getScalarValueRe("a"));
	}
    public void testAccessMethods03() {
        ml.setScalar("abc", 11.22, 33.44);
		assertTrue(11.22 == ml.getScalarValueRe("abc"));
		assertTrue(33.44 == ml.getScalarValueIm("abc"));
	}
    public void testAccessMethods04() {
        ml.setScalar("abc", 11.22, 0);
		assertTrue(11.22 == ml.getScalarValueRe("abc"));
		assertTrue(0.0   == ml.getScalarValueIm("abc"));
	}
    public void testAccessMethods05() {
        ml.setScalar("abc05", 0, 11.22);
		assertTrue(0.0   == ml.getScalarValueRe("abc05"));
		assertTrue(11.22 == ml.getScalarValueIm("abc05"));
	}
    public void testAccessMethods06() {
        double[][] a = {{1.0, 2.0, 3.0},{5.5, 6.7, 8.8}};
        double[][] b = {{1.0, 2.0, 3.0},{5.5, 6.7, 9}}; 
        ml.setArray("abc06", a, b);
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("abc06")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("abc06")));
	}
    public void testAccessMethods07() {
        double[][] a = {{1.0, 2.0, 3.0},{5.5, 6.7, 8.8}};
        double[][] b = {{1.0, 2.0, 3.0},{5.5, 6.7, 9}}; 
        ml.setArray("abc06", a, b);
 		assertTrue(!Compare.ArrayEquals(b, ml.getArrayValueRe("abc06")));
 		assertTrue(!Compare.ArrayEquals(a, ml.getArrayValueIm("abc06")));
	}
    public void testAccessMethods08() {
        double[][] dr = {{1.0, 1.0, 1.0},{1.0, 1.0, 1.0}};
        double[][] di = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
		ml.executeExpression("d=ones(2,3);");
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d")));
 		assertTrue(Compare.ArrayEquals(di, ml.getArrayValueIm("d")));
	}
    public void testAccessMethods09() {
        double[][] dr = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
        double[][] di = {{1.0, 1.0, 1.0},{1.0, 1.0, 1.0}};
		ml.executeExpression("d=ones(2,3)*i;");
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d")));
 		assertTrue(Compare.ArrayEquals(di, ml.getArrayValueIm("d")));
	}
    public void testAccessMethods10() {
        double[][] dr = {{1.0, 1.0, 1.0},{1.0, 1.0, 1.0}};
		ml.executeExpression("d=ones(2,3)+ones(2,3)*i;");
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d")));
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueIm("d")));
	}
    public void testAccessMethods11() {
        double[][] dr = {{1.0, 2.0, 1.0},{3.0, 4.0, 5.0}};
		ml.executeExpression("d=[1,2,1\n3,4,5]");
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d")));
	}



}