package jmathlibtests.core.interpreter;

import jmathlib.tools.junit.framework.*;
import jmathlib.core.interpreter.*;

public class testScanner extends TestCase {
	protected Interpreter ml;
	
    public testScanner(String name) {
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
		return new TestSuite(testScanner.class);
	}

    /************* number parsing ********************************************/
    public void testNumberParsing01() {
        ml.executeExpression(" b = 2 ");
		assertEquals(2, ml.getScalarValueRe("b"), 0.0001);
	}
    public void testNumberParsing02() {
        ml.executeExpression(" b = 23 ");
		assertEquals(23, ml.getScalarValueRe("b"), 0.0001);
	}
    public void testNumberParsing03() {
        ml.executeExpression(" b = 1234567890 ");
		assertEquals(1234567890, ml.getScalarValueRe("b"), 0.0001);
	}
    public void testNumberParsing04() {
        ml.executeExpression(" b = 1.1 ");
		assertEquals(1.1, ml.getScalarValueRe("b"), 0.0001);
	}
    public void testNumberParsing05() {
        ml.executeExpression(" b = 1.1234 ");
		assertEquals(1.1234, ml.getScalarValueRe("b"), 0.0001);
	}
    public void testNumberParsing06() {
        ml.executeExpression(" b = 5432.1234 ");
		assertEquals(5432.1234, ml.getScalarValueRe("b"), 0.0001);
	}
    public void testNumberParsing07() {
        ml.executeExpression(" b = .234 ");
		assertEquals(0.234, ml.getScalarValueRe("b"), 0.0001);
	}
    public void testNumberParsing08() {
        ml.executeExpression(" b = 1e3 ");
		assertEquals(1000, ml.getScalarValueRe("b"), 0.0001);
	}
    public void testNumberParsing09() {
        ml.executeExpression(" b = 2 ");
		assertEquals(2, ml.getScalarValueRe("b"), 0.0001);
	}
    public void testNumberParsing10() {
        ml.executeExpression(" b = 2 ");
		assertEquals(2, ml.getScalarValueRe("b"), 0.0001);
	}
}

