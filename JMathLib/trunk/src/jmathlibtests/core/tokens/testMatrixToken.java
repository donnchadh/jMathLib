package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testMatrixToken extends TestCase {
	protected Interpreter ml;
	
    public testMatrixToken(String name) {
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
		return new TestSuite(testMatrixToken.class);
	}

	/************* simple expressions ****************************************/
    public void test001() {
        ml.executeExpression("a=2+3");
		assertTrue(5 == ml.getScalarValueRe("a"));
	}

    public void test002() {
        ml.executeExpression("b=['sdfg']");
        assertTrue(ml.getString("b").equals("sdfg"));
    }

    public void test003() {
        ml.executeExpression("b=['sdf' 'gggg']");
        assertTrue(ml.getString("b").equals("sdfgggg"));
    }

    public void test004() {
        ml.executeExpression("b=['sdf' 'gggg' 'l' 'oo' 'd56']");
        assertTrue(ml.getString("b").equals("sdfgggglood56"));
    }

    public void test005() {
        ml.executeExpression("b=['sdf' 65]");
        assertTrue(ml.getString("b").equals("sdfA"));
    }

    public void test006() {
        ml.executeExpression("b=['sdf' 65 66 67]");
        assertTrue(ml.getString("b").equals("sdfABC"));
    }

    public void test007() {
        ml.executeExpression("b=[68 'sdf' 65 66 67]");
        assertTrue(ml.getString("b").equals("DsdfABC"));
    }

    public void test008() {
        ml.executeExpression("b=[69 'sdf' ]");
        assertTrue(ml.getString("b").equals("Esdf"));
    }

    
}