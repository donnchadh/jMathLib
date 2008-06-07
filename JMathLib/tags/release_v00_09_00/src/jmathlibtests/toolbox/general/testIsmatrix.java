package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testIsmatrix extends TestCase {
	protected Interpreter ml;
	
    public testIsmatrix(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testIsmatrix.class);
	}

	public void test01() {
        ml.executeExpression("a=ismatrix(1)");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}

	public void test02() {
        ml.executeExpression("a=ismatrix([1])");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}

	public void test03() {
        ml.executeExpression("a=ismatrix([ 2 1])");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}

	public void test04() {
        ml.executeExpression("a=ismatrix([1,2,3;4,5,6])");
		assertTrue(1 == ml.getScalarValueRe("a"));
	}

	public void test05() {
        ml.executeExpression("a=ismatrix(\"hello\")");
		assertTrue(0 == ml.getScalarValueRe("a"));
	}




}
