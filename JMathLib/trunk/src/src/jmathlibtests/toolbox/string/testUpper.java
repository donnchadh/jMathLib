package jmathlibtests.toolbox.string;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testUpper extends TestCase {
	protected Interpreter ml;
	
    public testUpper(String name) {
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
		return new TestSuite(testUpper.class);
	}

	public void test01() {
        ml.executeExpression("a=\"hello\"");
        ml.executeExpression("b=upper(a)");
		assertTrue(ml.getString("b").equals("HELLO"));
	}

    public void test02() {
        ml.executeExpression("aa=\"HELLO\"");
        ml.executeExpression("bb=upper(aa)");
        assertTrue(ml.getString("bb").equals("HELLO"));
    }
    
    public void test03() {
        ml.executeExpression("a=\"heLlo\"");
        ml.executeExpression("b=upper(a)");
        assertTrue(ml.getString("b").equals("HELLO"));
    }
    public void test04() {
        ml.executeExpression("a=\"HellO\"");
        ml.executeExpression("b=upper(a)");
        assertTrue(ml.getString("b").equals("HELLO"));
    }
    public void test05() {
        ml.executeExpression("a=\"HeLLO\"");
        ml.executeExpression("b=upper(a)");
        assertTrue(ml.getString("b").equals("HELLO"));
    }




}
