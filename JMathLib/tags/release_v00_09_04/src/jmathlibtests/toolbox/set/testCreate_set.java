package jmathlibtests.toolbox.set;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testCreate_set extends TestCase {
	protected Interpreter ml;
	
    public testCreate_set(String name) {
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
		return new TestSuite(testCreate_set.class);
	}

    
	public void testCreate_set01() {
        double[][] a = { {2.0, 3.0, 5.0, 6.0}};
        ml.executeExpression("a = create_set([2,3;5,6])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
	}

    public void testCreate_set02() {
        double[][] a = {{1.0, 8.0}};
        ml.executeExpression("a = create_set([8,1,1,1])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }

    public void testCreate_set10() {
        double[][] a = {{1.0, 2.0, 3.0, 4.0, 5.0, 8.0, 9.0}};
        ml.executeExpression("a = create_set([8,1,1; 3,5,3; 2,9,4; 2,3,4])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }

 
}