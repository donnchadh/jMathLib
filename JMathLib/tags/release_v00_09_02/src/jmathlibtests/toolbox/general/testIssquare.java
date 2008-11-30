package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testIssquare extends TestCase {
	protected Interpreter ml;
	
    public testIssquare(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testIssquare.class);
	}

	public void testIssquare01() {
        ml.executeExpression("a=issquare(1)");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}
	public void testIssquare02() {
        ml.executeExpression("a=issquare([3 2])");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}
	public void testIssquare03() {
        ml.executeExpression("a=issquare([2,3;5,6])");
		assertTrue(2 == ml.getScalarValueRe("a"));
	}
	public void testIssquare04() {
        ml.executeExpression("a=issquare([3,4,5;6,7,8])");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}
	public void testIssquare05() {
        ml.executeExpression("a=issquare([2,3,4;5,6,7;8,9,0])");
		assertTrue(3 == ml.getScalarValueRe("a"));
	}
	public void testIssquare06() {
        ml.executeExpression("a=issquare([2;4;5;6])");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}




}
