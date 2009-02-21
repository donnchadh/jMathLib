package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testMax extends TestCase {
	protected Interpreter ml;
	
    public testMax(String name) {
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
		return new TestSuite(testMax.class);
	}

	public void testMax01() {
        ml.executeExpression("a=max(3,5)");
        assertTrue(5 == ml.getScalarValueRe("a"));
	}
    public void testMax02() {
        ml.executeExpression("a=max(5,3)");
        assertTrue(5 == ml.getScalarValueRe("a"));
    }
    public void testMax03() {
        ml.executeExpression("a=max(-2,6)");
        assertTrue(6 == ml.getScalarValueRe("a"));
    }
    public void testMax04() {
        ml.executeExpression("a=max(555,333)");
        assertTrue(555 == ml.getScalarValueRe("a"));
    }
    public void testMax05() {
        ml.executeExpression("a=max(456,456)");
        assertTrue(456 == ml.getScalarValueRe("a"));
    }
    public void testMax06() {
        ml.executeExpression("a=max([6,7,8,9])");
        assertTrue(9 == ml.getScalarValueRe("a"));
    }
    public void testMax07() {
        ml.executeExpression("a=max([-3;-4;-5;-88;-9])");
        assertTrue(-3 == ml.getScalarValueRe("a"));
    }

    
    
	public void testMax10() {
        double[][] a = { {5.0 , 6.0  , 7.0}};
        ml.executeExpression("a = max([2,3,4;5,6,7])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
	}
    public void testMax11() {
        double[][] a = { {50.0 , 60.0 , 40.0}};
        ml.executeExpression("a = max([20,3,40;50,60,7])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testMax12a() {
        double[][] a = {{2.5  , 3.0  , 4.0}};
        ml.executeExpression("a = max([2,3,4],2.5)");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testMax12b() {
        double[][] a = { {3.5} ,{3.5} ,{4.0}};
        ml.executeExpression("a = max([2,3,4]',3.5)");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testMax13() {
        double[][] a = { {4.0, 4.0, 4.0} ,{5.0, 4.0, 7.0}};
        ml.executeExpression("a = max(4, [2,3,4; 5,1,7])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testMax14() {
        double[][] a = { {6.0, 5.0, 5.0} ,{5.0, 5.0, 7.0}};
        ml.executeExpression("a = max([6,3,4; 5,1,7],5)");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
    public void testMax15() {
        double[][] a = { {2.0, 3.0, 4.0} ,{5.0, 2.0, 9.0}};
        ml.executeExpression("a = max([2,3,1; 5,2,8],[2,1,4;-5,1,9])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }
 
}