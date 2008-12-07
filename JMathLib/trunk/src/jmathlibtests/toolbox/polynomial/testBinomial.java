package jmathlibtests.toolbox.polynomial;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testBinomial extends TestCase {
	protected Interpreter ml;
	
    public testBinomial(String name) {
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
		return new TestSuite(testBinomial.class);
	}

	public void testAbs01() {
        ml.executeExpression("a=abs(1);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}
    public void testAbs02() {
        ml.executeExpression("a=abs(-3);");
		assertTrue(3 == ml.getScalarValueRe("a"));
	}



}
