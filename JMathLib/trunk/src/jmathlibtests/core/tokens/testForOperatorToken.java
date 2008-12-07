package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.tools.junit.framework.*;
import jmathlib.core.tokens.*;
import jmathlibtests.*;

public class testForOperatorToken extends TestCase
{
	protected Interpreter ml;

    public testForOperatorToken(String name)
    {
        super(name);
    }
    
    public static void main(String[] args)
    {
		jmathlib.tools.junit.textui.TestRunner.run (suite());        
    }

    public static Test suite()
    {
        return new TestSuite(testForOperatorToken.class);
    }
    
    public void setUp()
    {
        ml = new Interpreter(true);
    }    
    
    protected void tearDown() {
        ml = null;
    }

    public void testFor01()
    {
        ml.executeExpression(" x=1;y=0;for(z=0;z<5;z=z+1) { x=x+1; y=z; } end");
		assertTrue(6 == ml.getScalarValueRe("x"));
        assertTrue(4 == ml.getScalarValueRe("y"));
    }

    public void testFor02()
    {
        ml.executeExpression(" x=0;y=0;for(z=0;z<5;z=z+1)  x=x+2; endfor; ");
		assertTrue(Math.abs(10 - ml.getScalarValueRe("x")) < 0.01);
    }

    public void testFor03()
    {
        ml.executeExpression(" y=0; for x=[1,2,3,4], y=y+x; end ");
		assertTrue(Math.abs(10 - ml.getScalarValueRe("y")) < 0.01);
    }

    public void testFor04a()
    {
        ml.executeExpression(" y=0; for x=[1,2,3,4,5],{ y=y+x;} endfor");
		assertTrue(Math.abs(15 - ml.getScalarValueRe("y")) < 0.01);
    }

    public void testFor04b()
    {
        ml.executeExpression(" y=0; for x=[1,2,3,4,5], y=y+x; endfor");
		assertTrue(Math.abs(15 - ml.getScalarValueRe("y")) < 0.01);
    }

    public void testFor04c()
    {
        ml.executeExpression(" y=0; for x=[1,2,3,4,5], y=y+x; end");
		assertTrue(Math.abs(15 - ml.getScalarValueRe("y")) < 0.01);
    }

    public void testFor05()
    {
        ml.executeExpression(" yy=0; for x=1:6, yy=yy+x; end");
        assertTrue(Math.abs(21 - ml.getScalarValueRe("yy")) < 0.01);
    }
    
    public void testFor06()
    {
        ml.executeExpression(" yy=0; for x=1:2:7, yy=yy+x; end");
        assertTrue(Math.abs(16 - ml.getScalarValueRe("yy")) < 0.01);
    }

    
}
