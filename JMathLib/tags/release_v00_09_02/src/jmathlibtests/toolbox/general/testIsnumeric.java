package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testIsnumeric extends TestCase {
	protected Interpreter ml;
	
    public testIsnumeric(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testIsnumeric.class);
	}

	public void testIsnumeric01() {
        ml.executeExpression("a=isnumeric(1);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}
    public void testIsnumeric02() {
        ml.executeExpression("a=isnumeric([1 2 3 4]);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}
    public void testIsnumeric03() {
        ml.executeExpression("a=isnumeric(\"barfoo\");");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}



}
