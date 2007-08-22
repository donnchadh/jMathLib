package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testDiag extends TestCase {
	protected Interpreter ml;
	
    public testDiag(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testDiag.class);
	}

	public void testDiag01() {
        ml.executeExpression("a=diag(3)");
        assertTrue(3 == ml.getScalarValueRe("a"));
	}
	public void testDiag02() {
        double[][] a = {{ 4.0, 0.0}, {0.0, 5.0}};
        ml.executeExpression("a=diag([4,5])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
	}
	public void testDiag03() {
        double[][] a = {{ 5.0, 0.0}, {0.0, 6.0}};
        ml.executeExpression("a=diag([5;6])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
	}
	public void testDiag04() {
        double[][] a = { {0.0} ,{4.0} ,{7.0}};
        ml.executeExpression("a = diag([0,7,0;1,4,0;6,0,7])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
	}
	public void testDiag05() {
        double[][] a = {{ 3.0},{5.0}};
        ml.executeExpression("a = diag([3,7,0;1,5,6])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
	}

    
    public void testDiag10() {
        double[][] a = {{ 2.0}, {6.0}};
        ml.executeExpression("a = diag([1,2,3;4,5,6],1)");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testDiag11() {
        double[][] a = {{ 3.0}};
        ml.executeExpression("a = diag([1,2,3;4,5,6],2)");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testDiag12() {
        double[][] a = {{ 4.0}};
        ml.executeExpression("a = diag([1,2,3;4,5,6],-1)");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testDiag13() {
        double[][] a = {{ 2.0}, {6.0}};
        ml.executeExpression("a = diag([1,2,3;4,5,6]',-1)");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testDiag14() {
        double[][] a = {{ 3.0}, {8.0}};
        ml.executeExpression("a = diag([1,2,3,4;5,6,7,8]',-2)");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    

    public void testDiag20() {
        double[][] a = {{ 4.0, 0.0, 0.0}, 
                        { 0.0, 5.0, 0.0},
                        { 0.0, 0.0, 6.0} };
        ml.executeExpression("a = diag([4,5,6])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }


}