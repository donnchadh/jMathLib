package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testUplus extends TestCase {
	protected Interpreter ml;
	
    public testUplus(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testUplus.class);
	}

    /*****************************************************************/
	public void testUplus01() {
        ml.executeExpression("a=uplus(55);");
		assertTrue(55 == ml.getScalarValueRe("a"));
	}

    public void testUplus02() {
        ml.executeExpression("b=uplus(-56)");
		assertTrue(-56 == ml.getScalarValueRe("b"));
	}

    public void testUplus05() {
        double[][] c = {{1.0, 3.0},{2.0, 3.0}};
        ml.executeExpression("a=[1,3; 2,3]");
        ml.executeExpression("z =uplus(a)");
 		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("z")));
	}
 
}