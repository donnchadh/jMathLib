package jmathlibtests.toolbox;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testUserFunction extends TestCase {
	protected Interpreter ml;
	
    public testUserFunction(String name) {
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
		return new TestSuite(testUserFunction.class);
	}

    /****** functions() **************************************************/
	public void testUserFunction001() {
        ml.executeExpression("[a,b]=testFunction007(1,2,3,4);");
		assertTrue(1.0 == ml.getScalarValueRe("a"));
        assertTrue(1.0 == ml.getScalarValueRe("b"));
	}

    public void testUserFunction002() {
        ml.executeExpression("[a,b]=testFunction007(1,2,3,4,5);");
        assertTrue(2.0 == ml.getScalarValueRe("a"));
        assertTrue(1.0 == ml.getScalarValueRe("b"));
    }

    public void testUserFunction003() {
        ml.executeExpression("[a,b]=testFunction007(1,2,3,4,5,6);");
        assertTrue(3.0 == ml.getScalarValueRe("a"));
        assertTrue(1.0 == ml.getScalarValueRe("b"));
    }

    public void testUserFunction004() {
        ml.executeExpression("[a,b]=testFunction007(1,2,3,4,5,'abc');");
        assertTrue(3.0 == ml.getScalarValueRe("a"));
        assertTrue(1.0 == ml.getScalarValueRe("b"));
    }

    public void testUserFunction005() {
        ml.executeExpression("[a,b]=testFunction007(1,2,3,4,5,6,7);");
        assertTrue(4.0 == ml.getScalarValueRe("a"));
        assertTrue(1.0 == ml.getScalarValueRe("b"));
    }


}