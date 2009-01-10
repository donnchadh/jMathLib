package jmathlibtests.toolbox.deprecated;

import jmathlib.tools.junit.framework.*;

public class testIsstr extends JMathLibTestCase {
	
    public testIsstr(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}

	public static Test suite() {
		return new TestSuite(testIsstr.class);
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
