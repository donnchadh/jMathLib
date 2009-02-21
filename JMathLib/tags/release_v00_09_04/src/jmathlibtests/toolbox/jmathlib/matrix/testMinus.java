package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testMinus extends TestCase {
	protected Interpreter ml;
	
    public testMinus(String name) {
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
		return new TestSuite(testMinus.class);
	}

    /*****************************************************************/
	public void testMinus01() {
        ml.executeExpression("a=minus(5,5);");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}

    public void testMinus02() {
        ml.executeExpression("b=minus(5,6)");
		assertTrue(-1 == ml.getScalarValueRe("b"));
	}

    public void testMinus03() {
        ml.executeExpression("b=minus(6,5)");
		assertTrue(1 == ml.getScalarValueRe("b"));
    }

    public void testMinus04() {
        ml.executeExpression("b=minus(0,0)");
		assertTrue(0 == ml.getScalarValueRe("b"));
	}
    
    public void testMinus05() {
        double[][] c = {{1.0, -4.0},{0.0, -4.0}};
        ml.executeExpression("a=[1,3; 2,3]");
        ml.executeExpression("b=[0,7; 2,7]");
        ml.executeExpression("z = minus(a,b)");
 		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("z")));
	}
 
}