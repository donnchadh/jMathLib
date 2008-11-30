package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testCumprod extends TestCase {
	protected Interpreter ml;
	
    public testCumprod(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testCumprod.class);
	}

	public void testCumprod01() {
        ml.executeExpression("a=cumprod(5);");
		assertTrue(5 == ml.getScalarValueRe("a"));
	}

    public void testCumprod02() {
        double[][] a = {{1.0, 2.0, 3.0},{4.0, 10.0, 18.0}};
        ml.executeExpression("z = cumprod([1,2,3;4,5,6])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
    }

    public void testCumprod03() {
        double[][] a = {{1.0, 2.0, 6.0}};
        ml.executeExpression("z = cumprod([1,2,3])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
    }

}