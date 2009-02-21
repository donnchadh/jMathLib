package jmathlibtests.toolbox.string;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testDeblank extends TestCase {
	protected Interpreter ml;
	
    public testDeblank(String name) {
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
		return new TestSuite(testDeblank.class);
	}

	public void testDeblank01() {
        ml.executeExpression("a=deblank('hello')");
		assertTrue(ml.getString("a").equals("hello"));
	}

    public void testDeblank02() {
        ml.executeExpression("a=deblank('heello ')");
        assertTrue(ml.getString("a").equals("heello"));
    }

    public void testDeblank03() {
        ml.executeExpression("a=deblank('heeello     ')");
        assertTrue(ml.getString("a").equals("heeello"));
    }
    
    public void testDeblank04() {
        ml.executeExpression("a=deblank(' hellobar ')");
        assertTrue(ml.getString("a").equals(" hellobar"));
    }

    public void testDeblank05() {
        ml.executeExpression("a=deblank('  foohello')");
        assertTrue(ml.getString("a").equals("  foohello"));
    }



}
