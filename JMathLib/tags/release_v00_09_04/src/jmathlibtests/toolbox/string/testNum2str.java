package jmathlibtests.toolbox.string;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testNum2str extends TestCase {
	protected Interpreter ml;
	
    public testNum2str(String name) {
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
		return new TestSuite(testNum2str.class);
	}

	public void testNum2Str01() {
        ml.executeExpression("a=num2str(49);");
		assertTrue(ml.getString("a").equals("49"));
        ml.executeExpression("a=num2str(49.0);");
		assertTrue(ml.getString("a").equals("49"));
        ml.executeExpression("a=num2str(49.01);");
		assertTrue(ml.getString("a").equals("49.01"));                
	}
    public void testNum2Str02() {
        ml.executeExpression("a=num2str([97 100 56]);");
        assertTrue(ml.getString("a").equals("9710056"));
    }
    
    public void testNum2Str03() {
        ml.executeExpression("a=num2str([97.1 100 56]);");
        assertTrue(ml.getString("a").equals("97.1 100 56"));
    }
    
    //public void testNum2Str03() {
    //    ml.executeExpression(" a=str2num(\"bbbbbbdfasdfasdf\") "); 
    //    ml.executeExpression(" b=num2str(a) ");
    //    assertTrue(ml.getString("b").equals("bbbbbbdfasdfasdf"));
    //}


}
