package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testWhileOperatorToken extends TestCase
{
	protected Interpreter ml;

    public testWhileOperatorToken(String name)
    {
        super(name);
    }
    
    public static void main(String[] args)
    {
		jmathlib.tools.junit.textui.TestRunner.run (suite());        
    }

    public static Test suite()
    {
        return new TestSuite(testWhileOperatorToken.class);
    }
    
    public void setUp()
    {
        ml = new Interpreter(true);
    }    
    
    protected void tearDown() {
        ml = null;
    }

    public void testWhile01()
    {
        ml.executeExpression(" x=0;while(x<5){x=x+1;disp('x= ' + x);}end");
		assertTrue(5 == ml.getScalarValueRe("x"));
        
    }

    public void testWhile02a()
    {
        ml.executeExpression(" x=5;while(x>0){x=x-1;disp('x= ' + x);}endwhile");
		assertTrue(Math.abs(0 - ml.getScalarValueRe("x")) < 0.01 );
        
    }

    public void testWhile02b()
    {
        ml.executeExpression(" x=6;while(x>1) x=x-1;disp(x); endwhile");
		assertTrue(Math.abs(0 - ml.getScalarValueRe("x")) < 1.01 );
        
    }

    public void testWhile03a()
    {
        ml.executeExpression("x=8;y=-1;while(x>y){x=x+1;y=y+3;disp(x);disp(y)}end");
		assertTrue(Math.abs(13 - ml.getScalarValueRe("x")) < 0.01 );
		assertTrue(Math.abs(14 - ml.getScalarValueRe("y")) < 0.01 );
    }

    public void testWhile03b()
    {
        ml.executeExpression("x=8;y=-1;while(x>y) x=x+1;y=y+3;disp(x);disp(y); end");
		assertTrue(Math.abs(13 - ml.getScalarValueRe("x")) < 0.01 );
		assertTrue(Math.abs(14 - ml.getScalarValueRe("y")) < 0.01 );
    }

    public void testWhile03c()
    {
        ml.executeExpression("x=8;y=-1;while(x>y) x=x+1;y=y+3;disp(x);disp(y); endwhile");
		assertTrue(Math.abs(13 - ml.getScalarValueRe("x")) < 0.01 );
		assertTrue(Math.abs(14 - ml.getScalarValueRe("y")) < 0.01 );
    }

    
}
