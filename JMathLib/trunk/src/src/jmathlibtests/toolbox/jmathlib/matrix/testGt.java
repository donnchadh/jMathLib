package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testGt extends TestCase {
	protected Interpreter ml;
	
    public testGt(String name) {
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
		return new TestSuite(testGt.class);
	}

    /*****************************************************************/
	public void testGt01() {
        ml.executeExpression("a=gt(5,5);");
		assertTrue(false == ml.getScalarValueBoolean("a"));
	}

    public void testGt02() {
        ml.executeExpression("b=gt(5,6)");
		assertTrue(false == ml.getScalarValueBoolean("b"));
	}

    public void testGt03() {
        ml.executeExpression("b=gt(6,5)");
		assertTrue(true == ml.getScalarValueBoolean("b"));
    }

    public void testGt04() {
        ml.executeExpression("b=gt(0,0)");
		assertTrue(false == ml.getScalarValueBoolean("b"));
	}
    
    public void testGt05() {
        boolean[][] c = {{true, false},{false, false}};
        ml.executeExpression("a=[1,3; 2,3]");
        ml.executeExpression("b=[0,7; 2,7]");
        ml.executeExpression("z = gt(a,b)");
 		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueBoolean("z")));
	}
 
}