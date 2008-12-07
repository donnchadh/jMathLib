package jmathlibtests.toolbox.jmathlib.internal;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testImag extends TestCase {
	protected Interpreter ml;
	
    public testImag(String name) {
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
		return new TestSuite(testImag.class);
	}

    /****** min() ************************************************************/
	public void testAdd01() {
        ml.executeExpression("a=1+1;");
		assertTrue(2 == ml.getScalarValueRe("a"));
	}
    public void testAdd02() {
        ml.executeExpression("a=3.45+2.234;");
		assertTrue(5.684 == ml.getScalarValueRe("a"));
	}



}
