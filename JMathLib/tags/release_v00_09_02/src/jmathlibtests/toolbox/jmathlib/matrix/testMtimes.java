package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testMtimes extends TestCase {
	protected Interpreter ml;
	
    public testMtimes(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testMtimes.class);
	}

    /*****************************************************************/
	public void testMtimes01() {
        ml.executeExpression("a=mtimes(5,5);");
		assertTrue(25 == ml.getScalarValueRe("a"));
	}

    public void testMtimes02() {
        ml.executeExpression("b=mtimes(5,6)");
		assertTrue(30 == ml.getScalarValueRe("b"));
	}

    public void testMtimes03() {
        ml.executeExpression("b=mtimes(6,5)");
		assertTrue(30 == ml.getScalarValueRe("b"));
    }

    public void testMtimes04() {
        ml.executeExpression("b=mtimes(0,0)");
		assertTrue(0 == ml.getScalarValueRe("b"));
	}
    
    public void testMtimes05() {
        double[][] c = {{6.0, 28.0},{6.0, 35.0}};
        ml.executeExpression("a=[1,3; 2,3]");
        ml.executeExpression("b=[0,7; 2,7]");
        ml.executeExpression("z = mtimes(a,b)");
 		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("z")));
	}

    public void testMtimes06() {
        ml.executeExpression("b=mtimes([2,3],[3;4])");
        assertTrue(18 == ml.getScalarValueRe("b"));
    }

}