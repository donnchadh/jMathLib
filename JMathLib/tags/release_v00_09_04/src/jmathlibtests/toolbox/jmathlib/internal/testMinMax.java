package jmathlibtests.toolbox.jmathlib.internal;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testMinMax extends TestCase {
	protected Interpreter ml;
	
    public testMinMax(String name) {
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
		return new TestSuite(testMinMax.class);
	}

    /****** min() ************************************************************/
	public void testAdd01() {
        ml.executeExpression("a=1+1;");
		assertTrue(2 == ml.getScalarValueRe("a"));
	}
    public void testAdd02() {
        ml.executeExpression("a=3.45+2.234;");
		assertTrue(5.684 == ml.getScalarValueRe("a"));
	}

    /****** min() ************************************************************/
    public void testMin01() {
    	ml.executeExpression("clear;");
        ml.executeExpression("b=min(3,2);");
		assertTrue(2 == ml.getScalarValueRe("b"));
	}
    public void testMin02() {
        ml.executeExpression("b=min(2,3);");
		assertTrue(2 == ml.getScalarValueRe("b"));
	}
    public void testMin03() {
        ml.executeExpression("b=min(min(2,3),4);");
		assertTrue(2 == ml.getScalarValueRe("b"));
	}
    public void testMin04() {
        ml.executeExpression("b=min(4,min(2,3));");
		assertTrue(2 == ml.getScalarValueRe("b"));
	}

    /****** max() *************************************************************/
    public void testMax01() {
        ml.executeExpression("b=max(3,2);");
		assertTrue(3 == ml.getScalarValueRe("b"));
	}
    public void testMax02() {
        ml.executeExpression("b=max(2,3);");
		assertTrue(3 == ml.getScalarValueRe("b"));
	}
    public void testMax03() {
        ml.executeExpression("b=max(max(2,3),4);");
		assertTrue(4 == ml.getScalarValueRe("b"));
	}
    public void testMax04() {
        ml.executeExpression("b=max(4,max(2,3));");
		assertTrue(4 == ml.getScalarValueRe("b"));
	}
    public void testMax05() {
        ml.executeExpression("b=max(4,max(-1.5,3));");
		assertTrue(4 == ml.getScalarValueRe("b"));
	}
    public void testMax06() {
        ml.executeExpression("b=max(-2.4,max(-2.3,-3.4));");
		assertTrue(-2.3 == ml.getScalarValueRe("b"));
	}
    public void testMax07() {
        ml.executeExpression("b=max(2.4,max(-2.3,-3.4));");
		assertTrue(2.4 == ml.getScalarValueRe("b"));
	}
    public void testMax08() {
        ml.executeExpression("b=max(-2.4,max(2.3,-3.4));");
		assertTrue(2.3 == ml.getScalarValueRe("b"));
	}
    public void testMax09() {
        ml.executeExpression("b=max(-2.4,max(-2.3,4.4));");
		assertTrue(4.4 == ml.getScalarValueRe("b"));
	}
    public void testMax10() {
        ml.executeExpression("b=max(-2.4,max(10,10));");
		assertTrue(10 == ml.getScalarValueRe("b"));
	}

    /****** min() and max() **************************************************/
    public void testMinMax01() {
        ml.executeExpression("b=min(1,max(3,2));");
		assertTrue(1 == ml.getScalarValueRe("b"));
	}
    public void testMinMax02() {
        ml.executeExpression("b=min(max(3,2),1);");
		assertTrue(1 == ml.getScalarValueRe("b"));
	}
    public void testMinMax03() {
        ml.executeExpression("b=max(min(3,2),1);");
		assertTrue(2 == ml.getScalarValueRe("b"));
	}
    public void testMinMax04() {
        ml.executeExpression("b=max(1,min(3,2))+4");
		assertTrue(6 == ml.getScalarValueRe("b"));
	}



}
