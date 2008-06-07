package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testIfThenOperatorToken extends TestCase
{
	protected Interpreter ml;

    public testIfThenOperatorToken(String name)
    {
        super(name);
    }
    
    public static void main(String[] args)
    {
		jmathlib.tools.junit.textui.TestRunner.run (suite());        
    }

    public static Test suite()
    {
        return new TestSuite(testIfThenOperatorToken.class);
    }
    
    public void setUp()
    {
        ml = new Interpreter(true);
    }    
    
    public void testIfThen01()
    {
        ml.executeExpression(" if(2>3){a=1;}else{a=2;}endif");
		assertTrue(2 == ml.getScalarValueRe("a"));
    }

    public void testIfThen02()
    {
        ml.executeExpression(" if(2<3){a=11;}else{a=22;}endif");
		assertTrue(11 == ml.getScalarValueRe("a"));
    }

    public void testIfThen03()
    {
        ml.executeExpression(" a=2; if(2<3) a=44; endif");
		assertTrue(44 == ml.getScalarValueRe("a"));
    }

    public void testIfThen04()
    {
        ml.executeExpression(" a=2; if(4<3) a=44; endif");
		assertTrue(2 == ml.getScalarValueRe("a"));
    }

    public void testIfThen05a()
    {
        ml.executeExpression(" if(2<3) a=11; else a=22; endif");
		assertTrue(11 == ml.getScalarValueRe("a"));
    }

    public void testIfThen05b()
    {
        ml.executeExpression(" if(2<3) a=11; else a=22; end");
		assertTrue(11 == ml.getScalarValueRe("a"));
    }

    public void testIfThen06()
    {
        ml.executeExpression(" if(2<3) a=11; elseif (4<3) a=22; else a=33; endif");
		assertTrue(11 == ml.getScalarValueRe("a"));
    }

    public void testIfThen07()
    {
        ml.executeExpression(" if(2>3) a=11; elseif (4<3) a=22; else a=33; endif");
		assertTrue(33 == ml.getScalarValueRe("a"));
    }
    
    public void testIfThen08()
    {
        ml.executeExpression(" if(2>3) a=11; elseif (2<3) a=22; else a=33; endif");
		assertTrue(22 == ml.getScalarValueRe("a"));
    }
    
    public void testIfThen09()
    {
        ml.executeExpression(" if(1<3) a=11; elseif (2<3) a=22; else a=33; endif");
		assertTrue(11 == ml.getScalarValueRe("a"));
    }

    public void testIfThen10()
    {
        ml.executeExpression(" if(2<3){ a=22; b=33} else a=44; endif");
		assertTrue(ml.getScalarValueRe("a") == 22);
		assertTrue(ml.getScalarValueRe("b") == 33);
    }

    public void testIfThen11()
    {
        ml.executeExpression("b=5; if(5<3){ a=22; b=33} else a=44; endif");
		assertTrue(ml.getScalarValueRe("a") == 44);
		assertTrue(ml.getScalarValueRe("b") == 5);
    }

    public void testIfThen12()
    {
        ml.executeExpression("if(1<3) a=11; elseif (2<3) { a=22;} else {a=33;} endif");
		assertTrue(ml.getScalarValueRe("a") == 11.0 );
    }

    public void testIfThen13()
    {
        ml.executeExpression("if(4<3) a=11; elseif (2<3) { a=22;} else {a=33;} endif");
		assertTrue(ml.getScalarValueRe("a") == 22.0 );
    }

    public void testIfThen14()
    {
        ml.executeExpression("if(4<3) a=11; elseif (3<3) { a=22;} else {a=33;} endif");
		assertTrue(ml.getScalarValueRe("a") == 33.0 );
    }

    
    
    public void testIfThen100()
    {
        ml.executeExpression(" if(5<3){ a=22; b=33} else b=11; a=44; endif");
		assertTrue(ml.getScalarValueRe("a") == 44.0);
		assertTrue(ml.getScalarValueRe("b") == 11.0);
    }

    public void testIfThen101()
    {
        ml.executeExpression(" if(5<3){ a=22; b=33} else {b=12; a=13;} endif");
		assertTrue(ml.getScalarValueRe("a") == 13.0);
		assertTrue(ml.getScalarValueRe("b") == 12.0);
    }

    public void testIfThen102()
    {
        ml.executeExpression(" if(1<3){ a=22; b=33} else b=11; a=44; endif");
		assertTrue(ml.getScalarValueRe("a") == 22.0);
		assertTrue(ml.getScalarValueRe("b") == 33.0);
    }

    public void testIfThen103()
    {
        ml.executeExpression(" if(1<3){ a=23; b=34} else {b=12; a=13;} endif");
		assertTrue(ml.getScalarValueRe("a") == 23.0);
		assertTrue(ml.getScalarValueRe("b") == 34.0);
    }

    public void testIfThen104()
    {
        ml.executeExpression(" if(1<3){ a=22; b=33} elseif (5>3)  a=14; b=15; c=16; else b=11; a=44; endif");
		assertTrue(ml.getScalarValueRe("a") == 22.0);
		assertTrue(ml.getScalarValueRe("b") == 33.0);
    }

    public void testIfThen105()
    {
        ml.executeExpression(" if(4<3){ a=23; b=33} elseif (5>3)  a=14; b=15; c=16; else b=11; a=44; endif");
		assertTrue(ml.getScalarValueRe("a") == 14.0);
		assertTrue(ml.getScalarValueRe("b") == 15.0);
    }

    public void testIfThen106()
    {
        ml.executeExpression(" if(4<3){ a=23; b=33} elseif (5>3)  {a=14; b=16; c=16;} else b=11; a=44; endif");
		assertTrue(ml.getScalarValueRe("a") == 14.0);
		assertTrue(ml.getScalarValueRe("b") == 16.0);
    }
    
    public void testIfThen107()
    {
        ml.executeExpression(" if(4<3) a=23; b=33 elseif (5>3)  {a=17; b=16; c=16;} else b=11; a=44; endif");
		assertTrue(ml.getScalarValueRe("a") == 17.0);
		assertTrue(ml.getScalarValueRe("b") == 16.0);
    }

    public void testIfThen108()
    {
        ml.executeExpression(" if(4<3) a=23; b=33 elseif (5>8)  {a=17; b=16; c=16;} else b=11; a=44; endif");
		assertTrue(ml.getScalarValueRe("a") == 44.0);
		assertTrue(ml.getScalarValueRe("b") == 11.0);
    }

    public void testIfThen109()
    {
        ml.executeExpression(" if(4<3) a=23; b=33 elseif (5>8)  a=17; b=16; c=16; else {b=13; a=44; } endif");
		assertTrue(ml.getScalarValueRe("a") == 44.0);
		assertTrue(ml.getScalarValueRe("b") == 13.0);
    }

    public void testIfThen110()
    {
        ml.executeExpression(" if(4<3) a=23; b=33 elseif (5>8)  a=17; b=16; c=16; else a=45;  end");
		assertTrue(ml.getScalarValueRe("a") == 45.0);
    }

}
