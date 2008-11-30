package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testIsPrime extends TestCase {
	protected Interpreter ml;
	
    public testIsPrime(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testIsPrime.class);
	}

	public void test01() {
        ml.executeExpression("a=isprime(7);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}
    public void test02() {
        ml.executeExpression("a=isprime(8);");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}
    public void test03() {
        ml.executeExpression("a=isprime(80);");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}
    public void test04() {
        ml.executeExpression("a=isprime(13);");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}
    public void test05() {
        ml.executeExpression("a=isprime(0);");  // note 0 not prime
		assertTrue(0 == ml.getScalarValueRe("a"));
	}
    public void test06() {
        ml.executeExpression("a=isprime(1);");  // note 1 not prime
		assertTrue(0 == ml.getScalarValueRe("a"));
	}
    public void test07() {
        ml.executeExpression("a=isprime(2);");   // note 2 is prime
		assertTrue(1 == ml.getScalarValueRe("a"));
	}

}
