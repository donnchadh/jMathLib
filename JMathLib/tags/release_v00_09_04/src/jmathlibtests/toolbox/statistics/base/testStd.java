package jmathlibtests.toolbox.statistics.base;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testStd extends TestCase {
	protected Interpreter ml;
	
    public testStd(String name) {
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
		return new TestSuite(testStd.class);
	}

    ////////////////////////////////////////////////////////////
	public void testStd01() {
        ml.executeExpression("a=std([2,4,6]);");
		assertTrue(2 == ml.getScalarValueRe("a"));
	}



}
