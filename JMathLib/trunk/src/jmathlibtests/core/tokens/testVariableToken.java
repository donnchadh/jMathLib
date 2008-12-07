package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;
import jmathlib.core.tokens.*;

public class testVariableToken extends TestCase
{
    private VariableToken variable1;
    private VariableToken variable2;
    private Interpreter ml;

    public testVariableToken(String name)
    {
        super(name);
    }
    
    public static void main(String[] args)
    {
		jmathlib.tools.junit.textui.TestRunner.run (suite());        
    }

    public static Test suite()
    {
        return new TestSuite(testVariableToken.class);
    }
    
    public void setUp()
    {
        variable1 = new VariableToken("x");
        variable2 = new VariableToken("x");
        ml        = new Interpreter(true);
    }
    protected void tearDown() {
        ml = null;
    }
    
    public void testEquals()
    {
        assertEquals(variable1, variable2);
    }
    
  /*  public void testAdd1()
    {
        VariableToken expectedResult = new VariableToken("x");
        expectedResult.setFactor(2);
        
        Token actualResult = variable1.add(variable2);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testMultiply1()
    {
        VariableToken expectedResult = new VariableToken("x");
        expectedResult.setFactor(2);
        
        Token actualResult = variable1.multiply(number);
        
        assertEquals(expectedResult, actualResult);
    }    

    public void testMultiply2()
    {
        VariableToken expectedResult = new VariableToken("x");
        expectedResult.setExponent(2);
        
        Token actualResult = variable1.multiply(variable2);
        
        assertEquals(expectedResult, actualResult);
    }    

    public void testMultiply3()
    {
        VariableToken expectedResult = new VariableToken("x");
        expectedResult.setExponent(2);
        expectedResult.setFactor(2);
        
        VariableToken actualResult = ((VariableToken)variable1.multiply(variable2));
        actualResult = ((VariableToken)actualResult.multiply(number));
        
        assertEquals(expectedResult, actualResult);
    }
 */   
    
    public void testVariable01() {
        ml.executeExpression("a=5");
        ml.executeExpression("b=55");
        assertTrue(ml.getScalarValueRe("a") == 5);
        assertTrue(ml.getScalarValueRe("b") == 55);
    }

    public void testVariable02() {
        ml.executeExpression("a=5");
        ml.executeExpression("A=6");
        assertTrue(ml.getScalarValueRe("a") == 5);
        assertTrue(ml.getScalarValueRe("A") == 6);
        assertTrue(ml.getScalarValueRe("a") == 5);
    }

    public void testVariable03() {
        ml.executeExpression("aAA=5");
        ml.executeExpression("AAA=2");
        assertTrue(ml.getScalarValueRe("aAA") == 5);
        assertTrue(ml.getScalarValueRe("AAA") == 2);
    }

    public void testVariable04() {
        ml.executeExpression("abA=5");
        ml.executeExpression("aba=6");
        ml.executeExpression("abA=7");
        assertTrue(ml.getScalarValueRe("abA") == 7);
        assertTrue(ml.getScalarValueRe("aba") == 6);
    }

}
