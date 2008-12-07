package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testFix extends TestCase {
	protected Interpreter ml;
	
    public testFix(String name) {
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
		return new TestSuite(testFix.class);
	}

    /****** fix() **********************************************************/
	public void testFix01() {
        ml.executeExpression("a=fix(20);");
		assertTrue(20.0 == ml.getScalarValueRe("a"));
	}

	public void testFix02() {
        ml.executeExpression("a=fix(1.1);");
		assertTrue(1.0 == ml.getScalarValueRe("a"));
	}

	public void testFix03() {
        ml.executeExpression("a=fix(2.1);");
		assertTrue(2.0 == ml.getScalarValueRe("a"));
	}
	
	public void testFix04() {
        ml.executeExpression("a=fix(4.9);");
		assertTrue(4.0 == ml.getScalarValueRe("a"));
	}

	public void testFix05() {
        ml.executeExpression("a=fix(-1.2);");
		assertTrue(-1.0 == ml.getScalarValueRe("a"));
	}

	public void testFix11() {
        ml.executeExpression("a=fix(20i);");
		assertTrue(20.0 == ml.getScalarValueIm("a"));
		assertTrue(0.0  == ml.getScalarValueRe("a"));
	}

	public void testFix12() {
        ml.executeExpression("a=fix(1.1i);");
		assertTrue(1.0 == ml.getScalarValueIm("a"));
		assertTrue(0.0 == ml.getScalarValueRe("a"));
	}

	public void testFix13() {
        ml.executeExpression("a=fix(2.1i);");
		assertTrue(2.0 == ml.getScalarValueIm("a"));
		assertTrue(0.0 == ml.getScalarValueRe("a"));
	}
	
	public void testFix14() {
        ml.executeExpression("a=fix(4.9i);");
		assertTrue(4.0 == ml.getScalarValueIm("a"));
		assertTrue(0.0 == ml.getScalarValueRe("a"));
	}

	public void testFix15() {
        ml.executeExpression("a=fix(-1.2i);");
		assertTrue(-1.0 == ml.getScalarValueIm("a"));
		assertTrue( 0.0 == ml.getScalarValueRe("a"));
	}

	public void testFix16() {
        ml.executeExpression("a=fix(-6.8i);");
		assertTrue(-6.0 == ml.getScalarValueIm("a"));
		assertTrue( 0.0 == ml.getScalarValueRe("a"));
	}

    public void testFix20() {
        double[][] a = {{1.0, 2.0, 3.0},{0.0, -1.0, -5.0},{5.0, -5.0, 4.0}};
        double[][] b = {{0.0, 0.0, 0.0},{0.0,  0.0,  0.0},{0.0,  0.0, 0.0}};
        ml.executeExpression("z = fix([1, 2.3, 3.7; 0, -1, -5.6; 5, -5, 4.2])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}

    public void testFix21() {
        double[][] a = {{1.0,  0.0, 3.0},{0.0, -1.0,  0.0}};
        double[][] b = {{1.0, -2.0, 8.0},{2.0,  0.0, -5.0}};
        ml.executeExpression("z = fix([1+i, -2.3i, 3.7+8.1i; 0+2.2i, -1, -5.6i])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}

    public void testFix22() {
        double[][] a = {{33.0,  0.0}};
        double[][] b = {{33.0, -5.0}};
        ml.executeExpression("z = fix([33+33i, -5.6i])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}

    public void testFix23() {
        double[][] a = {{0.0, -7.0}};
        double[][] b = {{0.0, -7.0}};
        ml.executeExpression("z = fix([0.5-0.5i, -7.9-7.6i])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueIm("z")));
	}

}