package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testWhos extends TestCase {
	protected Interpreter ml;
	
    public testWhos(String name) {
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
		return new TestSuite(testWhos.class);
	}

	public void testAbs01() {
        ml.executeExpression("a=abs(1);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}
    public void testWhos01() {
        ml.executeExpression("a=3");
        ml.executeExpression("adsasdf=45645763");
        ml.executeExpression("add='asdf3'");
        ml.executeExpression("addasdf=rand(5,30)");
        ml.executeExpression("whos");
        assertTrue(3 == ml.getScalarValueRe("a"));
    }
    public void testWhos02() {
        ml.executeExpression("a=443");
        ml.executeExpression("addasdf=rand(5,30)");
        ml.executeExpression("whos()");
        assertTrue(443 == ml.getScalarValueRe("a"));
    }



}
