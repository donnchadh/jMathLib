package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testSize extends TestCase {
	protected Interpreter ml;
	
    public testSize(String name) {
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
		return new TestSuite(testSize.class);
	}

	public void testSize01() {
        ml.executeExpression("a=size(1);");
        ml.executeExpression("y=a(1,1);x=a(1,2)");
		assertTrue(1.0 == ml.getScalarValueRe("x"));
		assertTrue(1.0 == ml.getScalarValueRe("y"));
	}

	public void testSize02() {
        ml.executeExpression("a=size([3 4]);");
        ml.executeExpression("y=a(1,1);x=a(1,2)");
		assertTrue(2.0 == ml.getScalarValueRe("x"));
		assertTrue(1.0 == ml.getScalarValueRe("y"));
	}

	public void testSize03() {
        ml.executeExpression("a=size([3 4 5]);");
        ml.executeExpression("y=a(1,1);x=a(1,2)");
		assertTrue(3.0 == ml.getScalarValueRe("x"));
		assertTrue(1.0 == ml.getScalarValueRe("y"));
	}

	public void testSize04() {
        ml.executeExpression("a=size([3 4 5]');");
        ml.executeExpression("y=a(1,1);x=a(1,2)");
		assertTrue(1 == ml.getScalarValueRe("x"));
		assertTrue(3 == ml.getScalarValueRe("y"));
	}

	public void testSize05() {
        ml.executeExpression("a=size([3 4 5;2 2 2]);");
        ml.executeExpression("y=a(1,1);x=a(1,2)");
		assertTrue(3 == ml.getScalarValueRe("x"));
		assertTrue(2 == ml.getScalarValueRe("y"));
	}

    public void testSize06() {
        ml.executeExpression("a=size({'sdfg','sdfg',3,4,5;7,8,'ads',4,5});");
        ml.executeExpression("y=a(1,1);x=a(1,2)");
        assertTrue(5 == ml.getScalarValueRe("x"));
        assertTrue(2 == ml.getScalarValueRe("y"));
    }

    public void testSize07() {
        ml.executeExpression("a=size('abcdef');");
        ml.executeExpression("y=a(1,1);x=a(1,2)");
        assertTrue(6 == ml.getScalarValueRe("x"));
        assertTrue(1 == ml.getScalarValueRe("y"));
    }

    public void testSize100() {
        double[][] s = {{3.0, 3.0, 5.0}};
        ml.executeExpression("z = size(rand(3,3,5))");
        assertTrue(Compare.ArrayEquals(s, ml.getArrayValueRe("z")));
    }

    public void testSize101() {
        double[][] s = {{3.0, 3.0, 5.0, 1.0, 3.0}};
        ml.executeExpression("z = size( rand(3,3,5,1,3) )");
        assertTrue(Compare.ArrayEquals(s, ml.getArrayValueRe("z")));
    }

}
