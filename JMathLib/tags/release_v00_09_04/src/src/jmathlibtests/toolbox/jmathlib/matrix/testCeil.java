package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testCeil extends JMathLibTestCase {
	
    public testCeil(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}

	public static Test suite() {
		return new TestSuite(testCeil.class);
	}

	public void testCeil1() {
        ml.executeExpression("a=ceil(5);");
		assertTrue(5 == ml.getScalarValueRe("a"));
	}
    public void testCeil2() {
        ml.executeExpression("a=ceil(5.2);");
        assertTrue(6 == ml.getScalarValueRe("a"));
    }
    public void testCeil3() {
        ml.executeExpression("a=ceil(6.5);");
        assertTrue(7 == ml.getScalarValueRe("a"));
    }
    public void testCeil4() {
        ml.executeExpression("a=ceil(8.7);");
        assertTrue(9 == ml.getScalarValueRe("a"));
    }
    public void testCeil5() {
        ml.executeExpression("a=ceil(-5);");
        assertTrue(-5 == ml.getScalarValueRe("a"));
    }
    public void testCeil6() {
        ml.executeExpression("a=ceil(-5.4);");
        assertTrue(-5 == ml.getScalarValueRe("a"));
    }
    public void testCeil7() {
        ml.executeExpression("a=ceil(-0.3);");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }
    public void testCeil8() {
        ml.executeExpression("a=ceil(-5.9);");
        assertTrue(-5 == ml.getScalarValueRe("a"));
    }


}