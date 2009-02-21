package jmathlibtests.toolbox.statistics.base;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testMean extends TestCase {
	protected Interpreter ml;
	
    public testMean(String name) {
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
		return new TestSuite(testMean.class);
	}

    //////////////////////////////////////////////////////////
	public void testMean01() {
        ml.executeExpression("a=mean(55);");
		assertTrue(55 == ml.getScalarValueRe("a"));
	}
    
    public void testMean02() {
        ml.executeExpression("a=mean([2,4]);");
        assertTrue(3 == ml.getScalarValueRe("a"));
    }
    
    public void testMean03() {
        ml.executeExpression("a=mean([2,4,6]);");
        assertTrue(4 == ml.getScalarValueRe("a"));
    }

    public void testMean10() {
        double[][] a = {{4.0, 6.0}};
        ml.executeExpression("a = mean([6,8;2,4])");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("a")));
    }


}
