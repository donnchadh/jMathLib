package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testIschar extends TestCase {
	protected Interpreter ml;
	
    public testIschar(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testIschar.class);
	}

	public void testIschar01() {
        ml.executeExpression("b={1,3,4};");
        ml.executeExpression("a=ischar(b);");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}
    public void testIschar02() {
        ml.executeExpression("b=rand(2,3);");
        ml.executeExpression("a=ischar(b);");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }
    public void testIschar03() {
        ml.executeExpression("b=\"asdf\";");
        ml.executeExpression("a=ischar(b);");
        assertTrue(1 == ml.getScalarValueRe("a"));
    }
    public void testIschar04() {
        ml.executeExpression("b='bar foo';");
        ml.executeExpression("a=ischar(b);");
        assertTrue(1 == ml.getScalarValueRe("a"));
    }


    public void testIsstr01() {
        ml.executeExpression("b={1,3,4};");
        ml.executeExpression("a=isstr(b);");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }
    public void testIsstr02() {
        ml.executeExpression("b=rand(2,3);");
        ml.executeExpression("a=isstr(b);");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }
    public void testIsstr03() {
        ml.executeExpression("b=\"asdf\";");
        ml.executeExpression("a=isstr(b);");
        assertTrue(1 == ml.getScalarValueRe("a"));
    }
    public void testIsstr04() {
        ml.executeExpression("b='bar foo';");
        ml.executeExpression("a=isstr(b);");
        assertTrue(1 == ml.getScalarValueRe("a"));
    }


}
