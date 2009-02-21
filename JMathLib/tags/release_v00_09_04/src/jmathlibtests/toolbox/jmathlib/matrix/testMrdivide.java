package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testMrdivide extends TestCase {
	protected Interpreter ml;
	
    public testMrdivide(String name) {
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
		return new TestSuite(testMrdivide.class);
	}

    /*****************************************************************/
	public void testMrdivide01() {
        ml.executeExpression("a=mrdivide(5,5);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}

    public void testMrdivide02() {
        ml.executeExpression("b=mrdivide(12,6)");
		assertTrue(2 == ml.getScalarValueRe("b"));
	}

    public void testMrdivide03() {
        ml.executeExpression("b=mrdivide(6,12)");
		assertTrue(0.5 == ml.getScalarValueRe("b"));
    }

    public void testMrdivide04() {
        ml.executeExpression("b=mrdivide(0,5)");
		assertTrue(0 == ml.getScalarValueRe("b"));
	}
    
 
}