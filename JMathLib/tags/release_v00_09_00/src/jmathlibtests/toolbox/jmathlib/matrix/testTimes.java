package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testTimes extends TestCase {
	protected Interpreter ml;
	
    public testTimes(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testTimes.class);
	}

    /*****************************************************************/
	public void testTimes01() {
        ml.executeExpression("a=times(5,5);");
		assertTrue(25 == ml.getScalarValueRe("a"));
	}

    public void testTimes02() {
        ml.executeExpression("b=times(5,6)");
		assertTrue(30 == ml.getScalarValueRe("b"));
	}

    public void testTimes03() {
        ml.executeExpression("b=times(6,5)");
		assertTrue(30 == ml.getScalarValueRe("b"));
    }

    public void testTimes04() {
        ml.executeExpression("b=times(0,0)");
		assertTrue(0 == ml.getScalarValueRe("b"));
	}
    
    public void testTimes05() {
        double[][] c = {{0.0, 21.0},{4.0, 21.0}};
        ml.executeExpression("a=[1,3; 2,3]");
        ml.executeExpression("b=[0,7; 2,7]");
        ml.executeExpression("z = times(a,b)");
 		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("z")));
	}


}