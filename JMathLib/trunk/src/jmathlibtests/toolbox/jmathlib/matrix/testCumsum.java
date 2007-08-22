package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testCumsum extends TestCase {
	protected Interpreter ml;
	
    public testCumsum(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testCumsum.class);
	}

	public void testCumsum01() {
        ml.executeExpression("a=cumsum(5);");
		assertTrue(5 == ml.getScalarValueRe("a"));
	}

    public void testCumsum02() {
        double[][] a = {{1.0, 2.0, 3.0},{5.0, 7.0, 9.0},{12.0, 15.0, 18.0}};
        ml.executeExpression("z = cumsum([1,2,3;4,5,6;7,8,9])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
    }
 
}