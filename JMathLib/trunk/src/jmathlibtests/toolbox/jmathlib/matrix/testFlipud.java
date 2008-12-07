package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testFlipud extends TestCase {
	protected Interpreter ml;
	
    public testFlipud(String name) {
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
		return new TestSuite(testFlipud.class);
	}

    /****** Flipud() ***********************************************************/
    public void testFlipud01() {
        double[][] b = {{7.0, 8.0, 9.0},{4.0, 5.0, 6.0},{1.0, 2.0, 3.0}};
        ml.executeExpression("a = [1, 2, 3; 4, 5, 6; 7, 8, 9];");
        ml.executeExpression("b = flipud(a)");
 		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueRe("b")));
	}
 
}