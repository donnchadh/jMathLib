package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testMod extends TestCase {
	protected Interpreter ml;
	
    public testMod(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testMod.class);
	}

    //////////////////////////////////////////////////////////////////
	public void testMod01() {
        ml.executeExpression("a=mod(4,2);");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}

    public void testMod02() {
        ml.executeExpression("a=mod(5,2);");
        assertTrue(1 == ml.getScalarValueRe("a"));
    }

    public void testMod03() {
        ml.executeExpression("a=mod(8,9);");
        assertTrue(8 == ml.getScalarValueRe("a"));
    }

    public void testMod04() {
        ml.executeExpression("a=mod(-5,3);");
        assertTrue(1 == ml.getScalarValueRe("a"));
    }


}
