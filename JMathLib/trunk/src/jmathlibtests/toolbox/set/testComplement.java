package jmathlibtests.toolbox.set;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testComplement extends TestCase {
	protected Interpreter ml;
	
    public testComplement(String name) {
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
		return new TestSuite(testComplement.class);
	}

    
	public void testComplement01() {
        double[][] a = { {3.0, 4.0, 6.0}};
        ml.executeExpression("a = complement([2,5,7],[3,4,5,6,7])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
	}

    public void testComplement02() {
        double[][] a = { {8.0}};
        ml.executeExpression("a = complement([1,2,4,6],[8])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }

    public void testComplement03() {
        double[][] a = { {2.0, 3.0, 5.0, 7.0, 8.0 }};
        ml.executeExpression("a = complement([4,6],[8,2,8,3,7,5,4,3])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }

 
}