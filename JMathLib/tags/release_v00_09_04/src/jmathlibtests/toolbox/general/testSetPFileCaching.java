package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testSetPFileCaching extends TestCase {
	protected Interpreter ml;
	
    public testSetPFileCaching(String name) {
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
		return new TestSuite(testSetPFileCaching.class);
	}

	public void testSetPFileCaching01() {
        ml.executeExpression("setpfilecaching(1)");
        ml.executeExpression("a=getpfilecaching()");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}

	public void testSetPFileCaching02() {
        ml.executeExpression("setpfilecaching(0)");
        ml.executeExpression("a=getpfilecaching()");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}

	public void testSetPFileCaching03() {
        ml.executeExpression("setpfilecaching('on')");
        ml.executeExpression("a=getpfilecaching()");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}

	public void testSetPFileCaching04() {
        ml.executeExpression("setpfilecaching('off')");
        ml.executeExpression("a=getpfilecaching()");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}

}
