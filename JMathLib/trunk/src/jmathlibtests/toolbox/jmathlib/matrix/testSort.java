package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testSort extends TestCase {
	protected Interpreter ml;
	
    public testSort(String name) {
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
		return new TestSuite(testSort.class);
	}

    
	public void testSort01() {
        double[][] a = { {1.0, 2.0, 6.0, 8.0, 9.0}};
        ml.executeExpression("a = sort([8,2,6,1,9])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
	}
    public void testSort02() {
        double[][] a = { {1.0, 1.0, 1.0, 8.0}};
        ml.executeExpression("a = sort([8,1,1,1])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }

    public void testSort10() {
        double[][] a = { {2.0, 1.0, 1.0},
                         {2.0, 3.0, 3.0},
                         {3.0, 5.0, 4.0},
                         {8.0, 9.0, 4.0} };
        ml.executeExpression("a = sort([8,1,1; 3,5,3; 2,9,4; 2,3,4])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }

 
}