package jmathlibtests.toolbox.finance;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testFvl extends TestCase {
	protected Interpreter ml;
	
    public testFvl(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testFvl.class);
	}

 
	public void testFvl01() {
        ml.executeExpression(" a= fvl(1,2,3) "); 
		assertTrue(12.0 == ml.getScalarValueRe("a"));
	}

 
}