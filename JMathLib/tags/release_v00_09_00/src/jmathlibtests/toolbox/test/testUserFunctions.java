package jmathlibtests.toolbox.test;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testUserFunctions extends TestCase
{
	protected Interpreter ml;

    public testUserFunctions(String name)
    {
        super(name);
    }
    
    public static void main(String[] args)
    {
		jmathlib.tools.junit.textui.TestRunner.run (suite());        
    }

    public static Test suite()
    {
        return new TestSuite(testUserFunctions.class);
    }
    
    public void setUp()
    {
        ml = new Interpreter(true);
    }    
    
    public void testUserFunction001()
    {
        ml.executeExpression(" if(2>3){a=1;}else{a=2;}endif");
		assertTrue(2 == ml.getScalarValueRe("a"));
    }

    public void testUserFunction002()
    {
        ml.executeExpression(" a=1;");
        ml.executeExpression(" b=testFunction001(4);");
        ml.executeExpression(" a=2;");
		assertTrue(2.0 == ml.getScalarValueRe("a"));
		assertTrue(6.0 == ml.getScalarValueRe("b"));
    }

    public void testUserFunction003()
    {
        ml.executeExpression("b=testFunction003()");
		assertTrue(33.0 == ml.getScalarValueRe("b"));
    }

    public void testUserFunction004()
    {
        ml.executeExpression("b=testFunction004(3,6)");
		assertTrue(15.0 == ml.getScalarValueRe("b"));
    }
    
    public void testUserFunction005()
    {
        ml.executeExpression("[x,y]=testFunction005(2,3)");
		assertTrue(8.0 == ml.getScalarValueRe("x"));
		assertTrue(7.0 == ml.getScalarValueRe("y"));
    }
    
    public void testUserFunction006_1()
    {
        ml.executeExpression("[x,y]=testFunction006(4)");
		assertTrue(6.0 == ml.getScalarValueRe("x"));
		assertTrue(12.0 == ml.getScalarValueRe("y"));
    }

    public void testUserFunction006_2()
    {
        ml.executeExpression("[x,y]=testFunction006(3)");
		assertTrue(5.0 == ml.getScalarValueRe("x"));
		assertTrue(9.0 == ml.getScalarValueRe("y"));
    }

    public void testUserFunctionWhile001_1()
    {
        ml.executeExpression("x=testFunctionWhile001(4)");
        assertTrue(10.0 == ml.getScalarValueRe("x"));
    }

    public void testUserFunctionWhile001_2()
    {
        ml.executeExpression("x=testFunctionWhile001(10)");
        assertTrue(55.0 == ml.getScalarValueRe("x"));
    }

    public void testUserFunctionWhile001_3()
    {
        ml.executeExpression("x=testFunctionWhile001(5)");
        assertTrue(15.0 == ml.getScalarValueRe("x"));
    }

    
}
