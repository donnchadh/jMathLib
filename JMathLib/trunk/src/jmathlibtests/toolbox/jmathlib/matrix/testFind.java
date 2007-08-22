package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testFind extends TestCase {
	protected Interpreter ml;
	
    public testFind(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testFind.class);
	}

    /*****************************************************************/
	public void testFind01() {
        ml.executeExpression("a=find([2,3,1]>2);");
		assertTrue(2 == ml.getScalarValueRe("a"));
	}

    public void testFind02() {
        ml.executeExpression("a=find([2,3,1]<2);");
        assertTrue(3 == ml.getScalarValueRe("a"));
    }

    public void testFind03() {
        ml.executeExpression("a=find([2,3,1]==3);");
        assertTrue(2 == ml.getScalarValueRe("a"));
    }

    public void testFind04() {
        double[][] a = {{1.0, 3.0}};
        ml.executeExpression("z = find([4,7,5]!=7)");
        assertTrue(Compare.ArrayEquals(a, ml.getArrayValueRe("z")));
    }

    public void testFind05() {
        ml.executeExpression("a=find(1);");
        assertTrue(1 == ml.getScalarValueRe("a"));
    }

    public void testFind06() {
        ml.executeExpression("a=find(0);");
        ml.executeExpression("b=size(a)");
        double[][] b = {{0.0, 0.0}};
        assertTrue(Compare.ArrayEquals(b, ml.getArrayValueRe("b")));
    }

    public void testFind07() {
        ml.executeExpression("a=find([1,4]>3);");
        ml.executeExpression("b=size(a)");
        double[][] b = {{1.0, 1.0}};
        assertTrue(Compare.ArrayEquals(b, ml.getArrayValueRe("b")));
    }

    public void testFind08() {
        ml.executeExpression("a=find([1,4,7]>3);");
        ml.executeExpression("b=size(a)");
        double[][] b = {{1.0, 2.0}};
        assertTrue(Compare.ArrayEquals(b, ml.getArrayValueRe("b")));
    }

 
}