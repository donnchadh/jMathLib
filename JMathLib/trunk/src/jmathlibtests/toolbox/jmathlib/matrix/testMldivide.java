package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testMldivide extends TestCase {
	protected Interpreter ml;
	
    public testMldivide(String name) {
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
		return new TestSuite(testMldivide.class);
	}

    /*****************************************************************/
	public void testMldivide01() {
        ml.executeExpression("a=mldivide(5,5);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}

    public void testMldivide02() {
        ml.executeExpression("b=mldivide(12,6)");
		assertTrue(0.5 == ml.getScalarValueRe("b"));
	}

    public void testMldivide03() {
        ml.executeExpression("b=mldivide(6,12)");
		assertTrue(2 == ml.getScalarValueRe("b"));
    }

    public void testMldivide04() {
        ml.executeExpression("b=mldivide(5,0)");
		assertTrue(0 == ml.getScalarValueRe("b"));
	}
    
 
}