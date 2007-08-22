package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testPlus extends TestCase {
	protected Interpreter ml;
	
    public testPlus(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testPlus.class);
	}

    /*****************************************************************/
	public void testPlus01() {
        ml.executeExpression("a=plus(5,5);");
		assertTrue(10 == ml.getScalarValueRe("a"));
	}

    public void testPlus02() {
        ml.executeExpression("b=plus(5,6)");
		assertTrue(11 == ml.getScalarValueRe("b"));
	}

    public void testPlus03() {
        ml.executeExpression("b=plus(6,5)");
		assertTrue(11 == ml.getScalarValueRe("b"));
    }

    public void testPlus04() {
        ml.executeExpression("b=plus(0,0)");
		assertTrue(0 == ml.getScalarValueRe("b"));
	}
    
    public void testPlus05() {
        double[][] c = {{1.0, 10.0},{4.0, 10.0}};
        ml.executeExpression("a=[1,3; 2,3]");
        ml.executeExpression("b=[0,7; 2,7]");
        ml.executeExpression("z = plus(a,b)");
 		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("z")));
	}
 
}