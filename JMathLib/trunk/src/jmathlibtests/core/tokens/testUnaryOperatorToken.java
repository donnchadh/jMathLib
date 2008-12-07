package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.*;

public class testUnaryOperatorToken extends TestCase {
	protected Interpreter ml;
	
    public testUnaryOperatorToken(String name) {
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
		return new TestSuite(testUnaryOperatorToken.class);
	}

	/************* ! factorial ************/
    public void test001a() {
        ml.executeExpression("a=3!");
		assertTrue(6.0 == ml.getScalarValueRe("a"));
	}

    public void test001b() {
        ml.executeExpression("a=4!");
        assertTrue(24.0 == ml.getScalarValueRe("a"));
    }

    
    /************* ' transpose ************/
    public void test002a() {
        ml.executeExpression("a=[3,2,1;  4,5,6]");
        double[][] r =         {{3,2,1},{4,5,6}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueRe("a"), 0.001));
    }  
    public void test002b() {
        ml.executeExpression("a=[3,2,1;  4,5,6]'");
        double[][] r =         {{3,4},{2,5},{1,6}};
        assertTrue(Compare.ArrayEquals(r, ml.getArrayValueRe("a"), 0.001));
    }  
    
    // NOT
    public void test300() {
        ml.executeExpression("!4");
        assertTrue(!ml.getScalarValueBoolean("ans"));
    }
    public void test301() {
        ml.executeExpression("!0");
        assertTrue(ml.getScalarValueBoolean("ans"));
    }
    public void test302() {
        ml.executeExpression("~4");
        assertTrue(!ml.getScalarValueBoolean("ans"));
    }
    public void test303() {
        ml.executeExpression("~0");
        assertTrue(ml.getScalarValueBoolean("ans"));
    }
								
    public void test310() {
        ml.executeExpression("!(2<3)");
        assertTrue( !ml.getScalarValueBoolean("ans"));
    }
    public void test311() {
        ml.executeExpression("!(2>3)");
        assertTrue(ml.getScalarValueBoolean("ans"));
    }
    public void test312() {
        ml.executeExpression("~(2<3)");
        assertTrue( !ml.getScalarValueBoolean("ans"));
    }
    public void test313() {
        ml.executeExpression("~(2>3)");
        assertTrue( ml.getScalarValueBoolean("ans"));
    }

    // ++
    public void testUnaryToken400() {
        ml.executeExpression("clear(1)");
        ml.executeExpression("a=1");
        ml.executeExpression("y=[11,22,33,44,55]");
        ml.executeExpression("y(a++)=77");
        ml.executeExpression("b=y(1)");
        ml.executeExpression("c=y(a)");
        assertTrue(77.0 == ml.getScalarValueRe("b"));
        assertTrue( 2.0 == ml.getScalarValueRe("a"));
        assertTrue(22.0 == ml.getScalarValueRe("c"));
    }

    // --
    public void testUnaryToken450() {
        ml.executeExpression("clear(1)");
        ml.executeExpression("a=4");
        ml.executeExpression("y=[11,22,33,44,55]");
        ml.executeExpression("y(a--)=88");
        ml.executeExpression("b=y(4)");
        ml.executeExpression("c=y(a)");
        assertTrue(88.0 == ml.getScalarValueRe("b"));
        assertTrue( 3.0 == ml.getScalarValueRe("a"));
        assertTrue(33.0 == ml.getScalarValueRe("c"));
    }
    
}