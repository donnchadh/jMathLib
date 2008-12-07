package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testLength extends TestCase {
	protected Interpreter ml;
	
    public testLength(String name) {
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
		return new TestSuite(testLength.class);
	}

    /****** length() **************************************************/
	public void testLength01() {
        ml.executeExpression("a=length(1);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}
	public void testLength02() {
        ml.executeExpression("a=length(2);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}
	public void testLength03() {
        ml.executeExpression("a=length([3,4]);");
		assertTrue(2 == ml.getScalarValueRe("a"));
	}
	public void testLength04() {
        ml.executeExpression("a=length([3,4]');");
		assertTrue(2 == ml.getScalarValueRe("a"));
	}
	public void testLength05() {
        ml.executeExpression("a=length([3,4;5,6]);");
		assertTrue(2 == ml.getScalarValueRe("a"));
	}
	public void testLength06() {
        ml.executeExpression("a=length([3,4,5;6,7,8]);");
		assertTrue(3 == ml.getScalarValueRe("a"));
	}
	public void testLength07() {
        ml.executeExpression("a=length(ones(10));");
		assertTrue(10 == ml.getScalarValueRe("a"));
	}
	public void testLength08() {
        ml.executeExpression("a=length(ones(2,3));");
		assertTrue(3 == ml.getScalarValueRe("a"));
	}
	public void testLength09() {
        ml.executeExpression("a=length(ones(3,2));");
		assertTrue(3 == ml.getScalarValueRe("a"));
	}
	public void testLength10() {
        ml.executeExpression("a=length(zeros(4,4));");
		assertTrue(4 == ml.getScalarValueRe("a"));
	}
    

}