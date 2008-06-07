package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testDet extends TestCase {
	protected Interpreter ml;
	
    public testDet(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testDet.class);
	}

	public void testDet01() {
        ml.executeExpression("a=det(5);");
		assertTrue(5 == ml.getScalarValueRe("a"));
	}

    public void testDet02() {
        ml.executeExpression("z = det([1,2,3;4,5,12;1,1,1])");
		assertTrue(6 == ml.getScalarValueRe("z"));
    }

    public void testDet03() {
        ml.executeExpression("z = det([1,2;3,4])");
		assertTrue(-2 == ml.getScalarValueRe("z"));
    }

    public void testDet04() {
        ml.executeExpression("z = determinant([2,3;3,4])");
		assertTrue(-1 == ml.getScalarValueRe("z"));
    }

}