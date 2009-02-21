package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testAbs extends JMathLibTestCase {
	
    public testAbs(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}

	public static Test suite() {
		return new TestSuite(testAbs.class);
	}

	public void testAbs01() {
        ml.executeExpression("a=abs(5);");
		assertTrue(5 == ml.getScalarValueRe("a"));
	}
    public void testAbs02() {
        ml.executeExpression("a=abs(0);");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }
    public void testAbs03() {
        ml.executeExpression("a=abs(-55);");
        assertTrue(55 == ml.getScalarValueRe("a"));
    }
    public void testAbs04() {
        ml.executeExpression("a=abs(7i);");
        assertTrue(7 == ml.getScalarValueRe("a"));
    }
    public void testAbs05() {
        ml.executeExpression("a=abs(-555i);");
        assertTrue(555 == ml.getScalarValueRe("a"));
    }
    public void testAbs06() {
        ml.executeExpression("a=abs(333);");
        assertTrue(333 == ml.getScalarValueRe("a"));
    }


}