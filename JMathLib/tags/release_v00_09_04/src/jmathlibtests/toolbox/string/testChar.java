package jmathlibtests.toolbox.string;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testChar extends TestCase {
	protected Interpreter ml;
	
    public testChar(String name) {
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
		return new TestSuite(testChar.class);
	}

	public void testChar01() {
        ml.executeExpression("a=char(49);");
		assertTrue(ml.getString("a").equals("1"));
	}
	public void testChar01b() {
        ml.executeExpression("a=_char(50);");
		assertTrue(ml.getString("a").equals("2"));
	}
    public void testChar02a() {
        ml.executeExpression("a=char([97 100 56]);");
        assertTrue(ml.getString("a").equals("ad8"));
    }
    public void testChar02b() {
        ml.executeExpression("a=_char([97 100 56]);");
        assertTrue(ml.getString("a").equals("ad8"));
    }
    public void testChar03a() {
        ml.executeExpression(" a=double(\"bbbbbbdfasdfasdf\") "); 
        ml.executeExpression(" b=char(a) ");
        assertTrue(ml.getString("b").equals("bbbbbbdfasdfasdf"));
    }
    public void testChar03b() {
        ml.executeExpression(" a=double(\"bbbbbbdfasdfasdf\") "); 
        ml.executeExpression(" b=_char(a) ");
        assertTrue(ml.getString("b").equals("bbbbbbdfasdfasdf"));
    }


}
