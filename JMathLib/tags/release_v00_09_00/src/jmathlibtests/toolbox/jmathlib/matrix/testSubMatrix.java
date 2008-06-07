package jmathlibtests.toolbox.jmathlib.matrix;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlibtests.Compare;

public class testSubMatrix extends TestCase {
	protected Interpreter ml;
	
    public testSubMatrix(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	public static Test suite() {
		return new TestSuite(testSubMatrix.class);
	}

	protected void setUp() {
		ml = new Interpreter(true);
        double[][] aRe = {{1.0, 2.0, 3.0, 4.0},{5.0, 6.0, 7.0, 8.0}};
        double[][] aIm = {{0.0, 0.0, 0.0, 0.0},{0.0, 0.0, 0.0, 0.0}};
        ml.setArray("a", aRe, aIm);
	}


    /****** submatrix() ******************************************************/
	public void testSubMatrix01() {
        ml.executeExpression("b=submatrix([4],1)");
		assertTrue(4 == ml.getScalarValueRe("b"));
	}
	public void testSubMatrix02() {
        ml.executeExpression("b=submatrix([2,3,4],1)");
		assertTrue(2 == ml.getScalarValueRe("b"));
	}
   	public void testSubMatrix03() {
        ml.executeExpression("b=submatrix([2,3,4],2)");
		assertTrue(3 == ml.getScalarValueRe("b"));
	}
   	public void testSubMatrix04() {
        ml.executeExpression("b=submatrix([2,3,4;5,6,7],2)");
		assertTrue(5 == ml.getScalarValueRe("b"));
	}
	public void testSubMatrix05() {
        ml.executeExpression("b=submatrix([2,3,4,5,6],2:3)");
        double[][] b = {{3, 4}};
		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueRe("b")));
    }

    // there has been a bug in FunctionToken: treatment of
    // submatrix([2,3,4,5,6],2:3) and
    // a=[2,3,4,5,6]; b=a(2:3) has been different
    public void testSubMatrix05b() {
        ml.executeExpression("a=[2,3,4,5,6]; b=a(2:4)");
        double[][] b = {{3, 4, 5}};
        assertTrue(Compare.ArrayEquals(b, ml.getArrayValueRe("b")));
    }
    public void testSubMatrix06() {
        ml.executeExpression("c=submatrix(a,1)");
		assertTrue(1 == ml.getScalarValueRe("c"));
    }
    public void testSubMatrix07() {
        ml.executeExpression("c=submatrix(a,7)");
		assertTrue(4 == ml.getScalarValueRe("c"));
    }
    public void testSubMatrix08() {
        ml.executeExpression("c=submatrix(a,1:2)");
        double[][] c = {{1, 5}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix09() {
        ml.executeExpression("c=submatrix(a,4:7)");
        double[][] c = {{6, 3, 7, 4}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix10() {
        ml.executeExpression("c=a(2:7)");
        double[][] c = {{5, 2, 6, 3, 7, 4}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
	public void testSubMatrix11() {
        ml.executeExpression("b=submatrix([4],1,1)");
		assertTrue(4 == ml.getScalarValueRe("b"));
	}
	public void testSubMatrix12() {
        ml.executeExpression("b=submatrix([2,3,4],1,2)");
		assertTrue(3 == ml.getScalarValueRe("b"));
	}
   	public void testSubMatrix13() {
        ml.executeExpression("b=submatrix([2,3,4],1,3)");
		assertTrue(4 == ml.getScalarValueRe("b"));
	}
   	public void testSubMatrix14() {
        ml.executeExpression("b=submatrix([2,3,4;5,6,7],2,2)");
		assertTrue(6 == ml.getScalarValueRe("b"));
	}
	public void testSubMatrix15() {
        ml.executeExpression("b=submatrix([2,3,4,5,6],1,2:3)");
        double[][] b = {{3, 4}};
		assertTrue(Compare.ArrayEquals(b, ml.getArrayValueRe("b")));
    }
    public void testSubMatrix16() {
        ml.executeExpression("c=submatrix(a,2,4)");
		assertTrue(8 == ml.getScalarValueRe("c"));
    }
    public void testSubMatrix17() {
        ml.executeExpression("c=submatrix(a,1:2,1:2)");
        double[][] c = {{1, 2},{5, 6}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix18() {
        ml.executeExpression("c=submatrix(a,1:2,3:4)");
        double[][] c = {{3, 4},{ 7, 8}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix19() {
        ml.executeExpression("c=a(1:2,2:3)");
        double[][] c = {{2, 3},{6, 7}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix20() {
        ml.executeExpression("c=a(:,2:3)");
        double[][] c = {{2, 3},{6, 7}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix21() {
        ml.executeExpression("c=a(1,:)");
        double[][] c = {{1, 2, 3, 4}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix22() {
        ml.executeExpression("x=[3,4,5;6,7,8]");
        ml.executeExpression("c=x(1,:)");
        double[][] c = {{3, 4, 5}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix23() {
        ml.executeExpression("x=[3,4,5;6,7,8]");
        ml.executeExpression("c=x(2,:)");
        double[][] c = {{6, 7, 8}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix24() {
        ml.executeExpression("x=[3,4,5;6,7,8]");
        ml.executeExpression("c=x(:)");
        double[][] c = {{3},{6},{4},{7},{5},{8}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix25() {
        ml.executeExpression("x=[3,4,5;6,7,8]");
        ml.executeExpression("c=x(:,1)");
        double[][] c = {{3},{6}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix26() {
        ml.executeExpression("x=[3,4,5;6,7,8]");
        ml.executeExpression("c=x(:,3)");
        double[][] c = {{5},{8}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix27() {
        ml.executeExpression("x=[3,4,5;6,7,8]");
        ml.executeExpression("c=x(1,:)");
        double[][] c = {{3,4,5}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix28() {
        ml.executeExpression("x=[3,4,5;6,7,8]");
        ml.executeExpression("c=x(2,:)");
        double[][] c = {{6,7,8}};
		assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }

    public void testSubMatrix30() {
        ml.executeExpression("x=[3,4,5;6,7,8;2,3,4]");
        ml.executeExpression("c=x([1,1,3],[1,3])");
        double[][] c = {{3,5},{3,5},{2,4}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix31() {
        ml.executeExpression("x=[3,4,5,6;7,8,9,0;3,3,3,3]");
        ml.executeExpression("c=x(1,:)");
        double[][] c = {{3,4,5,6}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix32() {
        ml.executeExpression("x=[3,4,5,6;7,8,9,0;3,3,3,3]");
        ml.executeExpression("c=x(2:3,:)");
        double[][] c = {{7,8,9,0},{3,3,3,3}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix33() {
        ml.executeExpression("x=[3,4,5,6;7,8,9,0;3,3,3,3]");
        ml.executeExpression("c=x(2:end,:)");
        double[][] c = {{7,8,9,0},{3,3,3,3}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix34() {
        ml.executeExpression("x=[3,4,5,6;7,8,9,0;3,3,3,3]");
        ml.executeExpression("c=x(2:end,2:3)");
        double[][] c = {{8,9},{3,3}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix35() {
        ml.executeExpression("x=[3,4,5,6;7,8,9,0;3,3,3,3]");
        ml.executeExpression("c=x(2:end,2:end)");
        double[][] c = {{8,9,0},{3,3,3}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix36() {
        ml.executeExpression("x=[3,4;7,8;3,3]");
        ml.executeExpression("c=x(:)");
        double[][] c = {{3},{7},{3},{4},{8},{3}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }

    public void testSubMatrix40() {
        ml.executeExpression("x=[3,4,5,6; 7,8,9,0; 11,22,33,44]");
        ml.executeExpression("c=x(2,2:end)");
        double[][] c = {{8,9,0}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix41() {
        ml.executeExpression("x=[3,4,5,6; 7,8,9,0; 11,22,33,44]");
        ml.executeExpression("c=x(2:end,2:end)");
        double[][] c = {{8,9,0},{22,33,44}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix42() {
        ml.executeExpression("x=[3,4,5,6; 7,8,9,0; 11,22,33,44]");
        ml.executeExpression("c=x([1,3],2:end)");
        double[][] c = {{4,5,6},{22,33,44}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix43() {
        ml.executeExpression("x=[3,4,5,6; 7,8,9,0; 11,22,33,44]");
        ml.executeExpression("c=x([1,3;2,3],2:end)");
        double[][] c = {{4,5,6},{8,9,0},{22,33,44},{22,33,44}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix44() {
        ml.executeExpression("x=[3,4,5,6; 7,8,9,0; 11,22,33,44]");
        ml.executeExpression("c=x([1,3;2,3],[1,3,4])");
        double[][] c = {{3,5,6},{7,9,0},{11,33,44},{11,33,44}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix45() {
        ml.executeExpression("x=[3,4,5,6; 7,8,9,0; 11,22,33,44]");
        ml.executeExpression("c=x([1,3;2,3],[1,3,4]')");
        double[][] c = {{3,5,6},{7,9,0},{11,33,44},{11,33,44}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }

    public void testSubMatrix50() {
        ml.executeExpression("x=[3,4,5,6; 7,8,9,0; 11,22,33,44]");
        ml.executeExpression("c=x(2:2:10)");
        double[][] c = {{7, 4, 22, 9, 6}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }

    public void testSubMatrix51() {
        ml.executeExpression("x=[3,4,5,6; 7,8,9,0; 11,22,33,44]");
        ml.executeExpression("c=x(1:2:3,2)");
        double[][] c = {{4},{22}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }
    public void testSubMatrix52() {
        ml.executeExpression("x=[3,4,5,6; 7,8,9,0; 11,22,33,44]");
        ml.executeExpression("c=x(1:2:3,1:3:4)");
        double[][] c = {{3,6},{11,44}};
        assertTrue(Compare.ArrayEquals(c, ml.getArrayValueRe("c")));
    }

 
}