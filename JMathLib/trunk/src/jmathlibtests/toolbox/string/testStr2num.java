package jmathlibtests.toolbox.string;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testStr2num extends TestCase {
	protected Interpreter ml;
	
    public testStr2num(String name) {
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
		return new TestSuite(testStr2num.class);
	}

	public void testStr2num01() {
        ml.executeExpression(" a=str2num(\"1\"); ");
		assertTrue(ml.getScalarValueRe("a") == 49);
	}
    public void testStr2Num02() {
        double[][] b = {{97, 100, 103, 57}};
        ml.executeExpression(" a=str2num(\"adg9\"); ");
        assertTrue(Compare.ArrayEquals(b, ml.getArrayValueRe("a")));
    }

}
