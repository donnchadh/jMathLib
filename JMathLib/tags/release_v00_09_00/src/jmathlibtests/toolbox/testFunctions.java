package jmathlibtests.toolbox;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testFunctions extends TestCase {
	protected Interpreter ml;
	
    public testFunctions(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testFunctions.class);
	}

    /****** functions() **************************************************/
	public void testFunctions001a() {
        ml.executeExpression("a=testFunction001(134);");
		assertTrue(136.0 == ml.getScalarValueRe("a"));
	}
	public void testFunctions001b() {
        ml.executeExpression("a=testFunction001(-10);");
		assertTrue(-8.0 == ml.getScalarValueRe("a"));
	}
	public void testFunctions001c() {
        ml.executeExpression("abc=testFunction001(4);");
		assertTrue(6.0 == ml.getScalarValueRe("abc"));
	}
    
   public void testPower01() {
        double[][] z = {{1.0, 4.0 ,  27.0 ,  256.0 ,  3125.0 ,  46656.0 ,  823543.0 ,  16777216.0 ,  387420489.0 ,  10000000000.0}};
        ml.executeExpression("x=[1:10]");
        ml.executeExpression("y=[1:10]");
        ml.executeExpression("z = x.^y");
        assertTrue(Compare.ArrayEquals(z, ml.getArrayValueRe("z"), 0.1));
    }

   public void testPower02() {
       double[][] z = {{1.0, 4.0, 9.0, 16.0, 25.0, 36.0, 49.0, 64.0, 81.0, 100}};
       ml.executeExpression("x=[1:10]");
       ml.executeExpression("z = x.^2");
       assertTrue(Compare.ArrayEquals(z, ml.getArrayValueRe("z"), 0.1));
   }

}