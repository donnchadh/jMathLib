package jmathlibtests.toolbox;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testStandardFunctions extends TestCase {
	protected Interpreter ml;
	
    public testStandardFunctions(String name) {
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
		return new TestSuite(testStandardFunctions.class);
	}

    /****** functions() **************************************************/
	public void testStandardFunctions001() {
        ml.executeExpression("a=ceil(4.5);");
		assertTrue(5.0 == ml.getScalarValueRe("a"));
	}
    public void testStandardFunctions002() {
        ml.executeExpression("a=ceil(5.5);");
        assertTrue(6.0 == ml.getScalarValueRe("a"));
    }
    public void testStandardFunctions003() {
        double[][] a = { {5.0 , 6.0  , 8.0},{5.0, 6.0, 7.0}};
        ml.executeExpression("a = ceil([4.5,6,7.1;5,6,7])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }

    public void testStandardAbs001() {
        ml.executeExpression("a=abs(4.5);");
        assertTrue(4.5 == ml.getScalarValueRe("a"));
    }
    public void testStandardAbs002() {
        ml.executeExpression("a=abs(-7.5);");
        assertTrue(7.5 == ml.getScalarValueRe("a"));
    }

    public void testStandardSqrt001() {
        ml.executeExpression("a=sqrt(9);");
        assertTrue(3.0 == ml.getScalarValueRe("a"));
    }
    public void testStandardSqrt002() {
        ml.executeExpression("a=sqrt(16);");
        assertTrue(4.0 == ml.getScalarValueRe("a"));
    }
    

}