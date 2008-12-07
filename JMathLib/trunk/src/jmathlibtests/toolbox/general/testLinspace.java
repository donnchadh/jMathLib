package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testLinspace extends TestCase {
	protected Interpreter ml;
	
    public testLinspace(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	public static Test suite() {
		return new TestSuite(testLinspace.class);
	}

	protected void setUp() {
		ml = new Interpreter(true);
	}
    protected void tearDown() {
        ml = null;
    }

    /****** linspace() **************************************************/
	public void testLinspace01() {
        ml.executeExpression("clear;");
        ml.executeExpression("a=linspace(2,7,6);");
        ml.executeExpression("b=a(1);");
        ml.executeExpression("c=a(2);");
        ml.executeExpression("d=a(3);");
        ml.executeExpression("e=a(4);");
        ml.executeExpression("f=a(5);");
        ml.executeExpression("g=a(6);");
		assertTrue(2 == ml.getScalarValueRe("b"));
		assertTrue(Math.abs(ml.getScalarValueRe("c")-3) < 0.001);
		assertTrue(4 == ml.getScalarValueRe("d"));
		assertTrue(5 == ml.getScalarValueRe("e"));
		assertTrue(6 == ml.getScalarValueRe("f"));
		assertTrue(7 == ml.getScalarValueRe("g"));
	}

	public void testLinspace02() {
        ml.executeExpression("clear;");
        ml.executeExpression("b=linspace(4,6,3)");
	    ml.executeExpression("c=b(1);");
        ml.executeExpression("d=b(2);");
        ml.executeExpression("e=b(3);");
		assertTrue(4 == ml.getScalarValueRe("c"));
		assertTrue(5 == ml.getScalarValueRe("d"));
		assertTrue(6 == ml.getScalarValueRe("e"));
	}


}