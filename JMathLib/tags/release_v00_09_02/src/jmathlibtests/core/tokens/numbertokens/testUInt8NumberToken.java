package jmathlibtests.core.tokens.numbertokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.tools.junit.framework.*;
import jmathlib.core.tokens.*;
import jmathlibtests.*;

public class testUInt8NumberToken extends TestCase
{
    private Interpreter ml;

    public testUInt8NumberToken(String name)
    {
        super(name);
    }
    
    public static void main(String[] args)
    {
        jmathlib.tools.junit.textui.TestRunner.run (suite());        
    }

    public static Test suite()
    {
        return new TestSuite(testUInt8NumberToken.class);
    }
    
    public void setUp()
    {
		ml   = new Interpreter(true);
	}
      
    
    public void testUInt8_01() {
        ml.executeExpression("a=uint8(200)");
        ml.executeExpression("b=uint8(80)");
        ml.executeExpression("c=a+b");
        ml.executeExpression("d=double(c)");
        assertTrue(ml.getScalarValueRe("d") == 255);
    }

    public void testUInt8_02() {
        ml.executeExpression("a=uint8(-88)");
        ml.executeExpression("b=uint8(-80)");
        ml.executeExpression("c=a+b");
        ml.executeExpression("d=double(c)");
        assertTrue(ml.getScalarValueRe("d") == 0);
    }

    public void testUInt8_03() {
        ml.executeExpression("a=uint8(20)");
        ml.executeExpression("b=uint8(80)");
        ml.executeExpression("c=a+b");
        ml.executeExpression("d=double(c)");
        assertTrue(ml.getScalarValueRe("d") == 100);
    }

    public void testUInt8_04() {
        ml.executeExpression("a=uint8(89)");
        ml.executeExpression("b=uint8(80)");
        ml.executeExpression("c=a-b");
        ml.executeExpression("d=double(c)");
        assertTrue(ml.getScalarValueRe("d") == 9);
    }

    public void testUInt8_05() {
        ml.executeExpression("a=uint8(88)");
        ml.executeExpression("b=uint8(80)");
        ml.executeExpression("c=b-a");
        ml.executeExpression("d=double(c)");
        assertTrue(ml.getScalarValueRe("d") == 0);
    }

    
}
