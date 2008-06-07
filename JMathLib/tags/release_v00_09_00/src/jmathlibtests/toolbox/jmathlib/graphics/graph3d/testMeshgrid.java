package jmathlibtests.toolbox.jmathlib.graphics.graph3d;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testMeshgrid extends TestCase {
	protected Interpreter ml;
	
    public testMeshgrid(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testMeshgrid.class);
	}

    public void test001() {
        double[][] x = {{1.0, 2.0},{1.0, 2.0},{1.0, 2.0}};
        double[][] y = {{3.0, 3.0},{4.0, 4.0},{5.0, 5.0}};
        ml.executeExpression("[x,y]=meshgrid([1,2],[3,4,5])");
        assertTrue(Compare.ArrayEquals(x, ml.getArrayValueRe("x")));
        assertTrue(Compare.ArrayEquals(y, ml.getArrayValueRe("y")));
    }
    
    // test case for bug: 859689  meshgrid number of output arguments!=2
    // solution: the evaluation of expressions did not work correctly for matrices on the left handside
    public void test002() {
        ml.executeExpression("[x,y]=meshgrid(3,4)");
        assertTrue(3 == ml.getScalarValueRe("x"));
        assertTrue(4 == ml.getScalarValueRe("y"));
    }

												    
}