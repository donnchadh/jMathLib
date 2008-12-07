package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testIsstruct extends TestCase {
	protected Interpreter ml;
	
    public testIsstruct(String name) {
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
		return new TestSuite(testIsstruct.class);
	}

	public void testIsstruct01() {
        ml.executeExpression("a=isstruct(1);");
		assertTrue(ml.getScalarValueRe("a") == 0);
	}
    public void testIsstruct02() {
        ml.executeExpression("a=isstruct([1 2 3 4]);");
		assertTrue(ml.getScalarValueRe("a") == 0);
	}
    public void testIsstruct03() {
        ml.executeExpression("a=isstruct(\"barfoo\");");
		assertTrue(ml.getScalarValueRe("a") == 0);
	}
    public void testIsstruct04() {
        ml.executeExpression("bad.c=77;");
        ml.executeExpression("a=isstruct(bad);");
		assertTrue(ml.getScalarValueRe("a") == 1);
	}
    public void testIsstruct05() {
        ml.executeExpression("y=struct(\"bar\",\"foo\",\"ddd\",99);");
        ml.executeExpression("a=isstruct(y);");
		assertTrue(ml.getScalarValueRe("a") == 1);
	}



}
