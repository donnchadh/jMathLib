package jmathlibtests.toolbox.control.system;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testIs_abcd extends TestCase {
	protected Interpreter ml;
	
    public testIs_abcd(String name) {
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
		return new TestSuite(testIs_abcd.class);
	}

	public void testIs_abcd01() {
        ml.executeExpression("a=is_abcd(1,2,3,4)");
        assertTrue(ml.getScalarValueRe("a") == 1);
	}
	public void testIs_abcd02() {
        ml.executeExpression("a=is_abcd([1 2],2,3,4)");
        assertTrue(ml.getScalarValueRe("a") == 0);
	}
	public void testIs_abcd03() {
        ml.executeExpression("a=is_abcd([1 2;4 5],2,3,4)");
        assertTrue(ml.getScalarValueRe("a") == 0);
	}
	public void testIs_abcd04() {
        ml.executeExpression("a=is_abcd(1,[2,8],3,4)");
        assertTrue(ml.getScalarValueRe("a") == 0);
	}
	public void testIs_abcd05() {
        ml.executeExpression("a=is_abcd(1,2,[3,6],4)");
        assertTrue(ml.getScalarValueRe("a") == 0);
	}
	public void testIs_abcd06() {
        ml.executeExpression("a=is_abcd(1,2,3,[4,8])");
        assertTrue(ml.getScalarValueRe("a") == 0);
	}
	public void testIs_abcd07() {
        ml.executeExpression("a=is_abcd(1,[2,8],3,[4,8])");
        assertTrue(ml.getScalarValueRe("a") == 1);
	}
	public void testIs_abcd08() {
        ml.executeExpression("a=is_abcd([1,2,3;4,5,6;7,8,9],[2,8,5]',[3,4,5],6)");
        assertTrue(ml.getScalarValueRe("a") == 1);
	}
	public void testIs_abcd09() {
        ml.executeExpression("a=is_abcd([1,2,3;4,5,6;7,8,9],[2,3;8,9;5,6],[3,4,5;3,4,5],[6,7;8,9])");
        assertTrue(ml.getScalarValueRe("a") == 1);
	}

 
}