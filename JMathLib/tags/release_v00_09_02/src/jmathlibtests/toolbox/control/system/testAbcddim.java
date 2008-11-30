package jmathlibtests.toolbox.control.system;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testAbcddim extends TestCase {
	protected Interpreter ml;
	
    public testAbcddim(String name) {
		super(name);
	}
	public static void main (String[] args) {
	    jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testAbcddim.class);
	}

	public void testAbcddim01() {
        ml.executeExpression("[a,b,c]=abcddim(1,2,3,4)");
        assertTrue(ml.getScalarValueRe("a") == 1);
        assertTrue(ml.getScalarValueRe("b") == 1);
        assertTrue(ml.getScalarValueRe("c") == 1);
	}

	public void testAbcddim02() {
        ml.executeExpression("[a,b,c]=abcddim(1,[2,5],3,[5,4])");
        assertTrue(ml.getScalarValueRe("a") == 1);
        assertTrue(ml.getScalarValueRe("b") == 2);
        assertTrue(ml.getScalarValueRe("c") == 1);
	}

 
}