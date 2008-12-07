package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;


public class testAssignmentOperatorToken extends TestCase {
	protected Interpreter ml;
	
    public testAssignmentOperatorToken(String name) {
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
		return new TestSuite(testAssignmentOperatorToken.class);
	}

	/************* simple expressions ****************************************/
    public void testAssignmentOpereratorToken01() {
        ml.executeExpression("a=3+3");
		assertTrue(6 == ml.getScalarValueRe("a"));
	}
    public void testAssignmentOpereratorToken02() {
        ml.executeExpression("b=[2,3]");
        ml.executeExpression("c=b(1,1)");
        ml.executeExpression("d=b(2)");
        assertTrue(ml.getScalarValueRe("c") == 2);
        assertTrue(ml.getScalarValueRe("d") == 3);
    }
    public void testAssignmentOpereratorToken03() {
        ml.executeExpression("b=[2,3,4;5,6,7;8,9,0]");
        ml.executeExpression("c=b(1,3)");
        assertTrue(ml.getScalarValueRe("c") == 4);
    }
    public void testAssignmentOpereratorToken04() {
        ml.executeExpression("b=[2,3,4;5,6,7;8,9,0]");
        ml.executeExpression("b(2,3)=111");
        ml.executeExpression("c=b(2,3)");
        assertTrue(ml.getScalarValueRe("c") == 111);
    }
    public void testAssignmentOpereratorToken05() {
        ml.executeExpression("b=[2,3,4;5,6,7;8,9,0]");
        ml.executeExpression("b(2)=222");
        ml.executeExpression("c=b(2,1)");
        assertTrue(ml.getScalarValueRe("c") == 222);
    }
    
    // some bug in subassign or AssignOperatorToken
    //   due to the bug, the example below returned b(1,2)=5 instead of 999
    public void testAssignmentOpereratorToken08() {
        ml.executeExpression("b=[2,3,4;5,6,7]");
        ml.executeExpression("b(1,2)=999");
        ml.executeExpression("c=b(1,2)");
        assertTrue( ml.getScalarValueRe("c") == 999);
    }

    // some bug in subassign or AssignOperatorToken
    // check on bug: b(3)=88 did not work
    public void testAssignmentOpereratorToken09() {
        ml.executeExpression("b=[22,33,44,55,66,77]");
        ml.executeExpression("b(3)=888");
        ml.executeExpression("c=b(1,3)");
        assertTrue( ml.getScalarValueRe("c") == 888);
    }

    // some bug in subassign or AssignOperatorToken
    // check on bug: b(3)=88 did not work
    public void testAssignmentOpereratorToken10() {
        ml.executeExpression("b=[22,33,44,55,66,77]'");
        ml.executeExpression("b(4)=8888");
        ml.executeExpression("c=b(4,1)");
        assertTrue( ml.getScalarValueRe("c") == 8888);
    }

												    
}