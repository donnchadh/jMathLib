package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.*;

public class testDotOperatorToken extends TestCase {
	protected Interpreter ml;
	
    public testDotOperatorToken(String name) {
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
		return new TestSuite(testDotOperatorToken.class);
	}

	/************* simple expressions ****************************************/
    public void testSimple01() {
        ml.executeExpression("a=struct('b',2,'c',3)");
        ml.executeExpression("r=a.b");
		assertTrue(2 == ml.getScalarValueRe("r"));
	}

    public void testSimple02() {
        ml.executeExpression("a=struct('b',2,'c',3)");
        ml.executeExpression("r=a.c");
        assertTrue(3 == ml.getScalarValueRe("r"));
	}

    public void testSimple03() {
        ml.executeExpression("a=struct('b',4,'c',5);  a.b=11");
        ml.executeExpression("r=a.b");
        assertTrue(11 == ml.getScalarValueRe("r"));
	}

    public void testSimple04() {
        ml.executeExpression("a=struct('b',6,'c',7);");
        ml.executeExpression("r=7+a.b");
        assertTrue(ml.getScalarValueRe("r") == 13);
	}

    public void testSimple05() {
        ml.executeExpression("a=struct('b',6,'c',7);");
        ml.executeExpression("r=7+a.b+2");
        assertTrue(ml.getScalarValueRe("r") == 15);
	}

    public void testSimple06() {
        ml.executeExpression("a=struct('b',6,'c',7);");
        ml.executeExpression("r=a.b+2");
        assertTrue(ml.getScalarValueRe("r") == 8);
	}

    /******** functions ********/
    public void testFunctions01() {
        ml.executeExpression("a=struct('b',0,'c',1);");
        ml.executeExpression("r=a.b.cos()");
        assertTrue(ml.getScalarValueRe("r") == 1);
	}

    public void testFunctions02() {
        ml.executeExpression("a=struct('b',1,'c',0);");
        ml.executeExpression("r=a.c.sin()");
        assertTrue(ml.getScalarValueRe("r") == 0);
	}

    public void testFunctions03() {
        ml.executeExpression("c=1");
        ml.executeExpression("r=c.sin().asin()");
        assertTrue(ml.getScalarValueRe("r") == 1);
	}

    public void testFunctions04() {
        ml.executeExpression("c=0");
        ml.executeExpression("r=c.cos()+2");
        assertTrue(ml.getScalarValueRe("r") == 3);
	}

    public void testFunctions05() {
        ml.executeExpression("c=0");
        ml.executeExpression("r=3+c.sin()+4");
        assertTrue(ml.getScalarValueRe("r") == 7);
	}

    public void testFunctions06() {
        ml.executeExpression("c=0");
        ml.executeExpression("r=4+c.cos()+5");
        assertTrue(ml.getScalarValueRe("r") == 10);
	}

    public void testFunctions07() {
        ml.executeExpression("c=0");
        ml.executeExpression("r=min(c.cos(), 2)+5");
        assertTrue(ml.getScalarValueRe("r") == 6);
	}

    public void testFunctions08() {
        ml.executeExpression("c=0");
        ml.executeExpression("r=min(c.cos(), 0.5)+5");
        assertTrue(ml.getScalarValueRe("r") == 5.5);
	}

    /*********** test matrices *************/
    public void testMatrix01() {
        double[][] dr = {{1.0, 2.0}};
        ml.executeExpression("a=struct('b',1,'c',-4);");
        ml.executeExpression("r=[a.b,2]");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("r")));
    }

    public void testMatrix02() {
        double[][] dr = {{1.0, 2.0}};
        ml.executeExpression("a=struct('b',1,'c',-4);");
        ml.executeExpression("r=[a.b 2]");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("r")));
    }

    public void testMatrix03() {
        double[][] dr = {{3.0, -4.0}};
        ml.executeExpression("a=struct('b',1,'c',-4);");
        ml.executeExpression("r=[3,a.c]");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("r")));
    }

    public void testMatrix04() {
        double[][] dr = {{3.0, -4.0}};
        ml.executeExpression("a=struct('b',1,'c',-4);");
        ml.executeExpression("r=[3 a.c]");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("r")));
    }

    public void testMatrix05() {
        double[][] dr = {{1.0, -4.0}};
        ml.executeExpression("a=struct('b',0,'c',-4);");
        ml.executeExpression("r=[a.b.cos() a.c]");  // bug missing ,
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("r"),0.01));
    }

    public void testMatrix06() {
        double[][] dr = {{1.0, -4.0}};
        ml.executeExpression("a=struct('b',0,'c',-4);");
        ml.executeExpression("r=[a.b.cos(), a.c]");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("r"), 0.01));
    }

}