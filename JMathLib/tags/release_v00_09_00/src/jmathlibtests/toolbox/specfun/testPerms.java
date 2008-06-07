package jmathlibtests.toolbox.specfun;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testPerms extends TestCase {
	protected Interpreter ml;
	
    public testPerms(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testPerms.class);
	}

	public void testPerms01() {
        ml.executeExpression("a=perms(7);");
		assertTrue(7 == ml.getScalarValueRe("a"));
	}
    
    public void testPerms02() {
        ml.executeExpression("a=perms(34);");
        assertTrue(34 == ml.getScalarValueRe("a"));
    }

    public void testPerms03() {
        double[][] a = {{4.0, 5.0},{5.0, 4.0}};
        ml.executeExpression("z = perms([4,5])");
 		assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
	}

    public void testPerms04() {
        double[][] a = {{4.0, 5.0, 6.0},
                        {5.0, 4.0, 6.0},
                        {4.0, 6.0, 5.0},
                        {5.0, 6.0, 4.0},
                        {6.0, 4.0, 5.0},
                        {6.0, 5.0, 4.0} };
        ml.executeExpression("z = perms([4,5,6])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
    }

}