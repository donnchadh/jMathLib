package jmathlibtests.toolbox.general;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testStruct extends TestCase {
	protected Interpreter ml;
	
    public testStruct(String name) {
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
		return new TestSuite(testStruct.class);
	}

	public void testStruct01() {
        ml.executeExpression("x=struct(\"a\",111,\"b\",222);");
        ml.executeExpression("aa=x.a;");
        ml.executeExpression("bb=x.b;");
		assertTrue(ml.getScalarValueRe("aa") == 111);
		assertTrue(ml.getScalarValueRe("bb") == 222);
	}
	
	public void testStruct02() {
        ml.executeExpression("x=struct(\"a\",1111,\"b\",2222,\"c\",333);");
        ml.executeExpression("aa=x.a;");
        ml.executeExpression("bb=x.b;");
        ml.executeExpression("cc=x.c;");
		assertTrue(ml.getScalarValueRe("aa") == 1111);
		assertTrue(ml.getScalarValueRe("bb") == 2222);
		assertTrue(ml.getScalarValueRe("cc") == 333);
	}

	public void testStruct03() {
        ml.executeExpression("y=struct(\"a\",1144,\"b\",2233);");
        ml.executeExpression("y.c = 555;");
        ml.executeExpression("aa=y.a;");
        ml.executeExpression("bb=y.b;");
        ml.executeExpression("cc=y.c;");  // new element
		assertTrue(ml.getScalarValueRe("aa") == 1144);
		assertTrue(ml.getScalarValueRe("bb") == 2233);
		assertTrue(ml.getScalarValueRe("cc") == 555);
	}

	public void testStruct04() {
        ml.executeExpression("yyy.c = 2;");
        ml.executeExpression("yyy.c = 22;");
        ml.executeExpression("yyy.d = 5;");
        ml.executeExpression("cc=yyy.c;");
        ml.executeExpression("dd=yyy.d;");
		assertTrue(ml.getScalarValueRe("cc") == 22);
		assertTrue(ml.getScalarValueRe("dd") == 5);
	}

}
