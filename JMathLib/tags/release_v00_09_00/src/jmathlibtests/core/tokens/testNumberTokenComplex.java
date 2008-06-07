package jmathlibtests.core.tokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.tools.junit.framework.*;
import jmathlib.core.tokens.*;

public class testNumberTokenComplex extends TestCase
{
    private DoubleNumberToken val1;
    private DoubleNumberToken val2;
    private DoubleNumberToken val3;
    private DoubleNumberToken val4;
    protected Interpreter ml;
    
    public testNumberTokenComplex(String name)
    {
        super(name);
    }
    
    public static void main(String[] args)
    {
        jmathlib.tools.junit.textui.TestRunner.run (suite());        
    }

    public static Test suite()
    {
        return new TestSuite(testNumberTokenComplex.class);
    }
    
    public void setUp()
    {
        val1 = new DoubleNumberToken(3);
        val2 = new DoubleNumberToken(0, 2);
        val3 = new DoubleNumberToken(2, 3);
        val4 = new DoubleNumberToken(3, 4);
        ml   = new Interpreter(true);
    }
    
    public void testAdd1()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(5, 7);
        
        OperandToken actualResult = val3.add(val4);
        
        assertEquals(expectedResult, actualResult);
    }
    
    public void testAdd2()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(3, 2);
        
        OperandToken actualResult = val1.add(val2);
        
        assertEquals(expectedResult, actualResult);
    }   

    public void testSubtract1()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-1, -1);
        
        OperandToken actualResult = val3.subtract(val4);
        
        assertEquals(expectedResult, actualResult);
    }
    
    public void testSubtract2()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(3, -2);
        
        OperandToken actualResult = val1.subtract(val2);
        
        assertEquals(expectedResult, actualResult);
    }   

    public void testMultiply1()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-6, 17);
        
        OperandToken actualResult = val3.multiply(val4);
        
        assertEquals(expectedResult, actualResult);
    }
    
    public void testMultiply2()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(0, 6);
        
        OperandToken actualResult = val1.multiply(val2);
        
        assertEquals(expectedResult, actualResult);
    }   

    public void testMultiply3()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-6, 4);
        
        OperandToken actualResult = val2.multiply(val3);
        
        assertEquals(expectedResult, actualResult);
    }
    
    public void testMuliply4()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(6, 9);
        
        OperandToken actualResult = val1.multiply(val3);
        
        assertEquals(expectedResult, actualResult);
    }       
    
    public void testConjugate1()
    {
        OperandToken actualResult = val1.conjugate();
        
        assertEquals(val1, actualResult);
    }

    public void testConjugate2()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(0, -2);

        OperandToken actualResult = val2.conjugate();
        
        assertEquals(expectedResult, actualResult);
    }

    public void testConjugate3()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(2, -3);

        OperandToken actualResult = val3.conjugate();
        
        assertEquals(expectedResult, actualResult);
    }

    public void testDoubleNumberToken01() {
        ml.executeExpression("b=[]");
        //assertTrue(ml.getScalarValueRe("b")==null);
        assertTrue(null ==  ml.getArrayValueRe("b"));
    }

}
