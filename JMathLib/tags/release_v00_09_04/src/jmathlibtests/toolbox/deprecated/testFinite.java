package jmathlibtests.toolbox.deprecated;

import jmathlib.tools.junit.framework.*;

public class testFinite extends JMathLibTestCase {
	
    public testFinite(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}

	public static Test suite() {
		return new TestSuite(testFinite.class);
	}

    public void testFinite01() {
        ml.executeExpression("a=finite([2]);");
        assertTrue(1 == ml.getScalarValueRe("a"));
    }
    public void testFinite02() {
        ml.executeExpression("a=finite([NaN]);");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }


}
