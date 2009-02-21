package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testIscell extends TestCase {
	protected Interpreter ml;
	
    public testIscell(String name) {
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
		return new TestSuite(testIscell.class);
	}

	public void testIscell01() {
        ml.executeExpression("b={1,3,4};");
        ml.executeExpression("a=iscell(b);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}
    public void testIscell02() {
        ml.executeExpression("b={1,'asdf3',4};");
        ml.executeExpression("a=iscell(b);");
        assertTrue(1 == ml.getScalarValueRe("a"));
    }
    public void testIscell03() {
        ml.executeExpression("a=iscell(3456456.8);");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }
    public void testIscell04() {
        ml.executeExpression("a=iscell('asdfasdf');");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }
    public void testIscell05() {
        ml.executeExpression("a=iscell([77,88,99;44,55,66]);");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }



}
