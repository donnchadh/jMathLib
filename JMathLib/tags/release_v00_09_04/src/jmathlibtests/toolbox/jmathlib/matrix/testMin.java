package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testMin extends TestCase {
	protected Interpreter ml;
	
    public testMin(String name) {
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
		return new TestSuite(testMin.class);
	}

	public void testMin01() {
        ml.executeExpression("a=min(3,5)");
        assertTrue(3 == ml.getScalarValueRe("a"));
	}
    public void testMin02() {
        ml.executeExpression("a=min(5,3)");
        assertTrue(3 == ml.getScalarValueRe("a"));
    }
    public void testMin03() {
        ml.executeExpression("a=min(-2,6)");
        assertTrue(-2 == ml.getScalarValueRe("a"));
    }
    public void testMin04() {
        ml.executeExpression("a=min(555,333)");
        assertTrue(333 == ml.getScalarValueRe("a"));
    }
    public void testMin05() {
        ml.executeExpression("a=min(456,456)");
        assertTrue(456 == ml.getScalarValueRe("a"));
    }
    public void testMin06() {
        ml.executeExpression("a=min([6,7,8,9])");
        assertTrue(6 == ml.getScalarValueRe("a"));
    }
    public void testMin07() {
        ml.executeExpression("a=min([3;4;5;-88;9])");
        assertTrue(-88 == ml.getScalarValueRe("a"));
    }

    
    
	public void testMin10() {
        double[][] a = { {2.0 , 3.0  , 4.0}};
        ml.executeExpression("a = min([2,3,4;5,6,7])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
	}
    public void testMin11() {
        double[][] a = { {20.0 , 3.0 , 7.0}};
        ml.executeExpression("a = min([20,3,40;50,60,7])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testMin12a() {
        double[][] a = {{2.0  , 3.0  , 3.5}};
        ml.executeExpression("a = min([2,3,4],3.5)");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testMin12b() {
        double[][] a = { {2.0} ,{3.0} ,{3.5}};
        ml.executeExpression("a = min([2,3,4]',3.5)");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testMin13() {
        double[][] a = { {2.0, 3.0, 4.0} ,{4.0, 1.0, 4.0}};
        ml.executeExpression("a = min(4, [2,3,4; 5,1,7])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testMin14() {
        double[][] a = { {2.0, 3.0, 4.0} ,{5.0, 1.0, 5.0}};
        ml.executeExpression("a = min([2,3,4; 5,1,7],5)");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testMin15() {
        double[][] a = { {2.0, 1.0, 1.0} ,{-5.0, 1.0, 7.0}};
        ml.executeExpression("a = min([2,3,1; 5,2,8],[2,1,4;-5,1,7])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
 
}