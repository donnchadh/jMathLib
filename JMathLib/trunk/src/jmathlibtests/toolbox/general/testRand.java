package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testRand extends TestCase {
	protected Interpreter ml;
	
    public testRand(String name) {
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
		return new TestSuite(testRand.class);
	}

	public void testRand01() {
        ml.executeExpression("a=rand(2,0)");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}

    public void testRand01b() {
        ml.executeExpression("a=rand(0,0)");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }

    public void testRand01c() {
        ml.executeExpression("a=rand(0,2)");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }
    
    public void testRand02() {
        ml.executeExpression("a=rand(3,4);");
        ml.executeExpression("b=size(a)");
        ml.executeExpression("b1=b(1)");
        ml.executeExpression("b2=b(2)");
		assertTrue(3 == ml.getScalarValueRe("b1"));
        assertTrue(4 == ml.getScalarValueRe("b2"));
	}



}
