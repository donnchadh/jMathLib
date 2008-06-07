package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testGe extends TestCase {
	protected Interpreter ml;
	
    public testGe(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testGe.class);
	}

    /*****************************************************************/
	public void testGe01() {
        ml.executeExpression("a=ge(5,5);");
		assertTrue(true == ml.getScalarValueBoolean("a"));
	}

    public void testGe02() {
        ml.executeExpression("b=ge(5,6)");
		assertTrue(false == ml.getScalarValueBoolean("b"));
	}

    public void testGe03() {
        ml.executeExpression("b=ge(6,5)");
		assertTrue(true == ml.getScalarValueBoolean("b"));
    }

    public void testGe04() {
        ml.executeExpression("b=eq(0,0)");
		assertTrue(true == ml.getScalarValueBoolean("b"));
	}
    
    public void testGe05() {
        boolean[][] c = {{true, false},{true, false}};
        ml.executeExpression("a=[1,3; 2,3]");
        ml.executeExpression("b=[0,7; 2,7]");
        ml.executeExpression("z = ge(a,b)");
 		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueBoolean("z")));
	}
 
}