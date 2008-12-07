package jmathlibtests.core.tokens.numbertokens;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.tools.junit.framework.*;
import jmathlib.core.tokens.*;
import jmathlibtests.*;

public class testDoubleNumberToken extends TestCase
{
    private DoubleNumberToken val1;
    private DoubleNumberToken val2;
    private DoubleNumberToken val3;
    private DoubleNumberToken val4;
    private DoubleNumberToken val5;
    private DoubleNumberToken val6;
    private DoubleNumberToken val7;
    private Interpreter ml;
    
    public testDoubleNumberToken(String name)
    {
        super(name);
    }
    
    public static void main(String[] args)
    {
        jmathlib.tools.junit.textui.TestRunner.run (suite());        
    }

    public static Test suite()
    {
        return new TestSuite(testDoubleNumberToken.class);
    }
    
    public void setUp()
    {
        val1 = new DoubleNumberToken(3);
        val2 = new DoubleNumberToken(6);
        val3 = new DoubleNumberToken(3);
        val4 = new DoubleNumberToken(-3);
        val5 = new DoubleNumberToken(-6);
        val6 = new DoubleNumberToken(3.5);
        val7 = new DoubleNumberToken(-3.5);        
		ml   = new Interpreter(true);
	}
      
    protected void tearDown() {
        ml = null;
    }

    
    public void testEquals()
    {
        assertEquals(val1, val3);
    }

    public void testEquals2()
    {
        assertTrue(!val1.equals(val2));
    }
    
    public void testAdd1()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(9);
        
        OperandToken actualResult = val1.add(val2);
        
        assertEquals(expectedResult, actualResult);
    }
    
    public void testAdd2()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(9);
        
        OperandToken actualResult = val2.add(val1);
        
        assertEquals(expectedResult, actualResult);
    }
    
    public void testAdd3()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-3);
        
        OperandToken actualResult = val1.add(val5);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testAdd4()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-3);
        
        OperandToken actualResult = val5.add(val1);
        
        assertEquals(expectedResult, actualResult);
    }
    
    public void testAdd5()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-9);
        
        OperandToken actualResult = val4.add(val5);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testAdd6()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-9);
        
        OperandToken actualResult = val5.add(val4);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testSubtract1()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-3);
        
        OperandToken actualResult = val1.subtract(val2);
        
        assertEquals(expectedResult, actualResult);
    }
    
    public void testSubtract2()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(3);
        
        OperandToken actualResult = val2.subtract(val1);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testSubtract3()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(9);
        
        OperandToken actualResult = val1.subtract(val5);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testSubtract4()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-9);
        
        OperandToken actualResult = val5.subtract(val1);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testSubtract5()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(3);
        
        OperandToken actualResult = val4.subtract(val5);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testSubtract6()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-3);
        
        OperandToken actualResult = val5.subtract(val4);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testMultiply1()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(18);
        
        OperandToken actualResult = val1.multiply(val2);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testMultiply2()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(18);
        
        OperandToken actualResult = val2.multiply(val1);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testMultiply3()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-18);
        
        OperandToken actualResult = val1.multiply(val5);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testMultiply4()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-18);
        
        OperandToken actualResult = val5.multiply(val1);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testMultiply5()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(18);
        
        OperandToken actualResult = val4.multiply(val5);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testMultiply6()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(18);
        
        OperandToken actualResult = val5.multiply(val4);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testMultiply7()
    {
        double[][] expectedRe = {{2.0, 4.0, 6.0},{-4.0, -10.0, -3.0}};
        double[][] expectedIm = {{0.0, 0.0, 0.0},{0.0, 0.0, 0.0}};
	
    	DoubleNumberToken expected = new DoubleNumberToken(expectedRe, expectedIm);
    
    	DoubleNumberToken input1 = new DoubleNumberToken(new double[][] {{1,2,3},{4,5,6}});
    	DoubleNumberToken input2 = new DoubleNumberToken(new double[][] {{2,2,2},{-1,-2,-0.5}});
    	OperandToken result = input1.scalarMultiply(input2);
    
    	boolean test = expected.equals(result);
        assertEquals(expected, result);
     }


    public void testDivide1()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(0.5);
        
        OperandToken actualResult = val1.divide(val2);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testDivide2()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(2);
        
        OperandToken actualResult = val2.divide(val1);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testDivide3()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-0.5);
        
        OperandToken actualResult = val1.divide(val5);
        
        assertEquals(expectedResult, actualResult);
    }
    
    public void testDivide4()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(-2);
        
        OperandToken actualResult = val5.divide(val1);
        
        assertEquals(expectedResult, actualResult);
    }
    
    public void testDivide5()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(0.5);
        
        OperandToken actualResult = val4.divide(val5);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testDivide6()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(2);
        
        OperandToken actualResult = val5.divide(val4);
        
        assertEquals(expectedResult, actualResult);
    }

    public void testPower()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(27);
        
        OperandToken actualResult = val1.power(val3);
        
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    public void testMPower()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(27);
        
        OperandToken actualResult = val1.mPower(val3);
        
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    public void testFactorial()
    {
        DoubleNumberToken expectedResult = new DoubleNumberToken(720);
        
        OperandToken actualResult = val2.factorial();
        
        assertEquals(expectedResult, actualResult);        
    }
    
    /************* scalar multiply ****************************************/
    public void testScalarMultiply01() {
        double[][] dr = {{2.0, 4.0, 9.0, 4.0}};
		ml.executeExpression("a=[1 2 3,4];");
		ml.executeExpression("b=[2 2 3 1];");
		ml.executeExpression("d=a.*b;");
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d"), 0.001));
	}

    public void testScalarMultiply02() {
        double[][] dr = {{3.0, 6.0, 9.0, 12.0}};
        ml.executeExpression("a=[1 2 3 4].*3;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("a"), 0.001));
    }

    public void testScalarMultiply03() {
        double[][] dr = {{6.0, 12.0, 18.0, 24.0}};
        ml.executeExpression("a=2.*[1 2 3 4].*3;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("a"), 0.001));
    }

    /************* scalar divide ****************************************/
    public void testScalarDivide01() {
        double[][] dr = {{2.0, 1.0, 3.0, 4.0}};
		ml.executeExpression("a=[4,2,9,4];");
		ml.executeExpression("b=[2,2,3,1];");
		ml.executeExpression("d=a./b;");
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d"), 0.001));
	}

    public void testScalarDivide02() {
        double[][] dr = {{3.0, 1.0, 2.0}};
        ml.executeExpression("a=[9 3 6]./3;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("a"), 0.001));
    }

    public void testScalarDivide03() {
        double[][] dr = {{24.0, 12.0, 8.0, 6.0}};
        ml.executeExpression("a=12./[1 2 3 4].*2;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("a"), 0.001));
    }
    
    
    public void testScalarLeftDivide01() {
        double[][] dr = {{2.0, 1.0, 3.0, 4.0}};
        ml.executeExpression("a=[4,2,9,4];");
        ml.executeExpression("b=[2,2,3,1];");
        ml.executeExpression("d=b.\\a;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d"), 0.001));
    }
    public void testScalarLeftDivide03() {
        double[][] dr = {{6.0, 3.0, 2.0, 1.5}};
        ml.executeExpression("a=2.*[1 2 3 4].\\12;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("a"), 0.001));
    }
    
    public void testLeftDivide01() {
        double[][] dr = {{3.0}};
        ml.executeExpression("a=4\\12;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("a"), 0.001));
    }
    
    public void testDoubleNumberToken100() {
        ml.executeExpression("a=[]");
        assertTrue(ml.getArrayValueRe("a") == null );
    }
    public void testDoubleNumberToken101() {
        ml.executeExpression("aa=[]");
        assertTrue(ml.getArrayValueIm("aa") == null );
    }

    public void testAdd01() {
        double[][] dr = {{3.0, 4.0, 6.0},{ 5.0, 8.0, 9.0}};
        ml.executeExpression("a=[1 2 3;4,5,6];");
        ml.executeExpression("b=[2 2 3;1,3,3];");
        ml.executeExpression("d=a+b;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d"), 0.001));
    }

    public void testAdd02() {
        double[][] dr = {{4.0, 5.0, 6.0},{ 7.0, 8.0, 9.0}};
        ml.executeExpression("a=[1 2 3;4,5,6];");
        ml.executeExpression("d=3+a;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d"), 0.001));
    }

    public void testAdd03() {
        double[][] dr = {{6.0, 7.0, 8.0},{ 9.0, 10.0, 11.0}};
        ml.executeExpression("a=[1 2 3;4,5,6];");
        ml.executeExpression("d=3+a+2;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d"), 0.001));
    }

    public void testAdd04() {
        double[][] dr = {{2.0, 3.0, 4.0},{ 5.0, 6.0, 7.0}};
        ml.executeExpression("a=[1 2 3;4,5,6];");
        ml.executeExpression("d=a+1;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d"), 0.001));
    }

    public void testAdd10() {
        double[][] dr = {{34.0, 34.0, 99.0}};
        ml.executeExpression("d=2+'  a';");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d"), 0.001));
    }

    public void testAdd11() {
        double[][] dr = {{37.0, 37.0, 102.0}};
        ml.executeExpression("d=2+'  a'+3;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d"), 0.001));
    }

    public void testAddSub03() {
        double[][] dr = {{2.0, 3.0, 4.0},{ 5.0, 6.0, 7.0}};
        ml.executeExpression("a=[1 2 3;4,5,6];");
        ml.executeExpression("d=3+a-2;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d"), 0.001));
    }

    public void testSub01() {
        double[][] dr = {{2.0, 0.0, -1.0},{ 2.0, 3.0, 4.0}};
        ml.executeExpression("a=[4 2 1;4,5,6];");
        ml.executeExpression("d=a-2;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d"), 0.001));
    }

    public void testSub02() {
        double[][] dr = {{-1.0, 1.0, 2.0},{ -1.0, -2.0, -3.0}};
        ml.executeExpression("a=[4 2 1;4,5,6];");
        ml.executeExpression("d=3-a;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d"), 0.001));
    }

    public void testSub03() {
        double[][] dr = {{-5.0, -3.0, -2.0},{ -5.0, -6.0, -7.0}};
        ml.executeExpression("a=[4 2 1;4,5,6];");
        ml.executeExpression("d=3-a-4;");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d"), 0.001));
    }
    
    public void testSetSize01() {
        ml.executeExpression("a=[1,2;3,4]");
        ml.executeExpression("a(3:4,3:4)=[5.0, 6.0; 7.0, 8.0]");
        double[][] bRe = {{1.0, 2.0, 0.0, 0.0},
                          {3.0, 4.0, 0.0, 0.0},
                          {0.0, 0.0, 5.0, 6.0},
                          {0.0, 0.0, 7.0, 8.0} };
        assertTrue(Compare.ArrayEquals(bRe, ml.getArrayValueRe("a")));
    }

    public void testSetSize02() {
        double[][] n1 = {{1,2},{4,5}};
        DoubleNumberToken n=new DoubleNumberToken(n1);
        n.setSize(3,2);

        double[][] r1= {{1.0, 2.0},{4.0, 5.0}, {0.0, 0.0}};
        assertTrue(Compare.ArrayEquals( r1 , n.getValuesRe() ));
        
    }

    
}
