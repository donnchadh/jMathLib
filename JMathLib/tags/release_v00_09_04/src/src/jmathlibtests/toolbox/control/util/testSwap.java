package jmathlibtests.toolbox.control.util;

import jmathlib.tools.junit.framework.*;

public class testSwap extends JMathLibTestCase {
	
    public testSwap(String name) {
		super(name);
	}
	public static void main (String[] args) {
	    jmathlib.tools.junit.textui.TestRunner.run (suite());
	}

	public static Test suite() {
		return new TestSuite(testSwap.class);
	}

	public void testSwap01() {
       ml.executeExpression("[a,b]=swap(3,4)");
       assertTrue(ml.getScalarValueRe("a") == 4);
       assertTrue(ml.getScalarValueRe("b") == 3);
	}

   public void testSwap02() {
       ml.executeExpression("a=88;");
       ml.executeExpression("b=77;");
       ml.executeExpression("[a,b]=swap(a,b)");
       assertTrue(ml.getScalarValueRe("a") == 77);
       assertTrue(ml.getScalarValueRe("b") == 88);
    }

 
}