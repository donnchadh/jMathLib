package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testEq extends TestCase {
	protected Interpreter ml;
	
    public testEq(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testEq.class);
	}

    /*****************************************************************/
	public void testEq01() {
        ml.executeExpression("a=eq(5,5);");
		assertTrue(true == ml.getScalarValueBoolean("a"));
	}

    public void testEq02() {
        ml.executeExpression("b=eq(5,6)");
		assertTrue(false == ml.getScalarValueBoolean("b"));
	}

    public void testEq03() {
        ml.executeExpression("b=eq(6,5)");
		assertTrue(false == ml.getScalarValueBoolean("b"));
    }

    public void testEq04() {
        ml.executeExpression("b=eq(0,0)");
		assertTrue(true == ml.getScalarValueBoolean("b"));
	}
    
    public void testEq05() {
        boolean[][] c = {{false, false},{true, false}};
        ml.executeExpression("a=[1,3; 2,3]");
        ml.executeExpression("b=[0,7; 2,7]");
        ml.executeExpression("z = eq(a,b)");
 		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueBoolean("z")));
	}
 
}