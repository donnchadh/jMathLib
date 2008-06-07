package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testClass extends TestCase {
	protected Interpreter ml;
	
    public testClass(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testClass.class);
	}

    /////////////////////////////////////////////////////////////////
	public void testClass01() {
        ml.executeExpression("a=class(88)");
		assertTrue(ml.getString("a").equals("double"));
	}

    public void testClass02() {
        ml.executeExpression("a=class('asd88')");
        assertTrue(ml.getString("a").equals("char"));
    }

    public void testClass03() {
        ml.executeExpression("a=class([8 89 9])");
        assertTrue(ml.getString("a").equals("double"));
    }

    public void testClass04() {
        ml.executeExpression("b=cell(3)");
        ml.executeExpression("a=class(b)");
        assertTrue(ml.getString("a").equals("cell"));
    }

    public void testClass05() {
        ml.executeExpression("b=logical(3)");
        ml.executeExpression("a=class(b)");
        assertTrue(ml.getString("a").equals("logical"));
    }


}
