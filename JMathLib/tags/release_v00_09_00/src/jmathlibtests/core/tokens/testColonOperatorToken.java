package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testColonOperatorToken extends TestCase {
	protected Interpreter ml;
	
    public testColonOperatorToken(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testColonOperatorToken.class);
	}

	/************* simple expressions ****************************************/
    public void test001() {
        ml.executeExpression("a=[0:1:10];b=a(2);c=a(6)");
		assertTrue(1 == ml.getScalarValueRe("b"));
        assertTrue(5 == ml.getScalarValueRe("c"));
	}


												    
}