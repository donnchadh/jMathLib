package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testAll extends JMathLibTestCase {
	
    public testAll(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}

	public static Test suite() {
		return new TestSuite(testAll.class);
	}

	public void testAll1() {
        ml.executeExpression("a=all(5);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}
    public void testAll2() {
        ml.executeExpression("a=all(0);");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }
    public void testAll3() {
        ml.executeExpression("a=all([1,2,3;4,5,6]);");
        assertTrue(1 == ml.getScalarValueRe("a"));
    }
    public void testAlll4() {
        ml.executeExpression("a=all([1,2,0;4,5,6]);");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }
    public void testAll5() {
        ml.executeExpression("a=all([1,2,3i;4,-5i,6]);");
        assertTrue(1 == ml.getScalarValueRe("a"));
    }
    public void testAll6() {
        ml.executeExpression("a=all([0,2,3i;4,-5i,6]);");
        assertTrue(0 == ml.getScalarValueRe("a"));
    }


}