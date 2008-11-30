package jmathlibtests.core.interpreter;

import jmathlib.tools.junit.framework.*;
import jmathlib.core.interpreter.*;
import jmathlibtests.*;

public class testParser extends TestCase {
	protected Interpreter ml;
	
    public testParser(String name) {
		super(name);
	}
	public static void main (String[] args) {
	    jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
	}

	public static Test suite() {
		return new TestSuite(testParser.class);
	}

	/************* simple expressions ****************************************/
    public void testAdd01() {
        ml.executeExpression("a=1+1;");
		assertEquals(2.0, ml.getScalarValueRe("a"), 0.001);
	}
    public void testAdd02() {
        ml.executeExpression("a=3.45+2.234;");
		assertEquals(5.684, ml.getScalarValueRe("a"), 0.001);
	}
    public void testAdd03() {
        ml.executeExpression("ones(4)");
		assertTrue(true);
	}

    public void testAdd04() {
        ml.executeExpression("ones(4)");
		assertTrue(true);
	}

	/************* priorities ************************************************/
    public void testPriority01() {
        ml.executeExpression("a=2+3*4");
		assertEquals(14.0, ml.getScalarValueRe("a"), 0.001);
	}
    public void testPriority02() {
        ml.executeExpression("a=2*3+4");
		assertEquals(10.0, ml.getScalarValueRe("a"), 0.001);
	}

    public void testPriority03() {
        ml.executeExpression("a=1/2-1+9");
		assertEquals(8.5, ml.getScalarValueRe("a"), 0.001);
	}
    
    public void testPriority04() {
        ml.executeExpression("a=1+2*3+4*2");
		assertEquals(15.0, ml.getScalarValueRe("a"), 0.001);
	}

    public void testPriority05() {
        ml.executeExpression("a=1+2*3*4+5");
		assertEquals(30.0, ml.getScalarValueRe("a"), 0.001);
	}

    public void testPriority06() {
        ml.executeExpression("a=1+2*3+4*5+6");
		assertEquals(33.0, ml.getScalarValueRe("a"), 0.001);
	}

    public void testPriority07() {
        ml.executeExpression("a=ones((2)-1)");
		assertEquals(1.0, ml.getScalarValueRe("a"), 0.001);
	}

    public void testPriority08() {
        ml.executeExpression("{a=11;}b=22;");
		assertEquals(11.0, ml.getScalarValueRe("a"), 0.001);
		assertEquals(22.0, ml.getScalarValueRe("b"), 0.001);
	}

    public void testPriority09() {
        ml.executeExpression("a=33;{b=44;}c=55;");
		assertEquals(33.0, ml.getScalarValueRe("a"), 0.001);
		assertEquals(44.0, ml.getScalarValueRe("b"), 0.001);
		assertEquals(55.0, ml.getScalarValueRe("c"), 0.001);
	}

    public void testPriority10() {
        ml.executeExpression("a=66,{b=77,};c=88,");
		assertEquals(66.0, ml.getScalarValueRe("a"), 0.001);
		assertEquals(77.0, ml.getScalarValueRe("b"), 0.001);
		assertEquals(88.0, ml.getScalarValueRe("c"), 0.001);
	}

    public void testPriority11() {
        ml.executeExpression("a=2*(3+4)");
		assertEquals(14.0, ml.getScalarValueRe("a"), 0.001);
	}

    public void testPriority12() {
        ml.executeExpression("a=(2+4)*3");
		assertEquals(18.0, ml.getScalarValueRe("a"), 0.001);
	}

    public void testPriority13() {
        ml.executeExpression("a=(1+2)*(3+4)");
		assertEquals(21.0, ml.getScalarValueRe("a"), 0.001);
	}												

    public void testPriority14() {
        ml.executeExpression("a=(1+3)^2");
		assertEquals(16.0, Math.abs(ml.getScalarValueRe("a")), 0.001);
	}												
    public void testPriority14b() {
        ml.executeExpression("a=(1+3).^2");
        assertEquals(16.0, Math.abs(ml.getScalarValueRe("a")), 0.001);
    }                                               
    public void testPriority15() {
        ml.executeExpression("a=2^(1+3)");
		assertEquals(16.0, Math.abs(ml.getScalarValueRe("a")), 0.001);
	}												
    public void testPriority16() {
        ml.executeExpression("a=2-3+5");
		assertEquals(4.0, ml.getScalarValueRe("a"), 0.001);
	}												

    public void testPriority100() {
        ml.executeExpression("2+3*4");
        assertEquals(14.0, ml.getScalarValueRe("ans"), 0.001);
    }

    public void testPriority101() {
        ml.executeExpression("2<3");
        assertEquals(true, ml.getScalarValueBoolean("ans"));
    }

    public void testPriority102() {
        ml.executeExpression("2<3+4");
        assertEquals(true, ml.getScalarValueBoolean("ans"));
    }

    public void testPriority103() {
        ml.executeExpression("2>3+4");
        assertEquals(false, ml.getScalarValueBoolean("ans"));
    }

    public void testPriority104() {
        ml.executeExpression("2>3||1<7");
        assertEquals(true, ml.getScalarValueBoolean("ans"));
    }

    public void testPriority105() {
        ml.executeExpression("2>3||1>7");
        assertEquals(false, ml.getScalarValueBoolean("ans"));
    }

    public void testPriority106() {
        ml.executeExpression("2<3||1>7");
        assertEquals(true, ml.getScalarValueBoolean("ans"));
    }
    
    public void testPriority107a() {
        ml.executeExpression("3&&2==3||1>7");
        assertEquals(false, ml.getScalarValueBoolean("ans"));
    }

    public void testPriority107b() {
        ml.executeExpression("3&&2==2||1>7");
        assertEquals(true, ml.getScalarValueBoolean("ans"));
    }

    public void testPriority108() {
        ml.executeExpression("5!=5||1>7");
        assertEquals(false, ml.getScalarValueBoolean("ans"));
    }

    public void testPriority109() {
        ml.executeExpression("7!=5||1>7");
        assertEquals(true, ml.getScalarValueBoolean("ans"));
    }

    public void testPriority110() {
        ml.executeExpression("8>=7||4!=5");
        assertEquals(true, ml.getScalarValueBoolean("ans"));
    }

    public void testPriority111() {
        ml.executeExpression("5>=7||5!=5");
        assertEquals(false, ml.getScalarValueBoolean("ans"));
    }


	/************* whitespace sensitivity ************************************/
	/** are whitespaces treated differently ? */
    public void testWhitespace01() {
        ml.executeExpression(" a = 2 + 3 ");
		assertEquals(5.0, ml.getScalarValueRe("a"), 0.001);
	}
    public void testWhitespace02() {
        ml.executeExpression("a = 2 +4");
		assertEquals(6.0, ml.getScalarValueRe("a"), 0.001);
	}
    public void testWhitespace03() {
        ml.executeExpression("a = 2+ 5");
		assertEquals(7.0, ml.getScalarValueRe("a"), 0.001);
	}
    public void testWhitespace04() {
        ml.executeExpression("a =2 + 6");
		assertEquals(8.0, ml.getScalarValueRe("a"), 0.001);
	}
    public void testWhitespace05() {
        ml.executeExpression("a= 2 + 7");
		assertEquals(9.0, ml.getScalarValueRe("a"), 0.001);
	}
    public void testWhitespace06() {
        ml.executeExpression("a= 2 +3");
		assertEquals(5.0, ml.getScalarValueRe("a"), 0.001);
	}
    public void testWhitespace07() {
        ml.executeExpression("a= 3+ 3");
		assertEquals(6.0, ml.getScalarValueRe("a"), 0.001);
	}
    public void testWhitespace08() {
        ml.executeExpression("a= 4+3");
		assertEquals(7.0, ml.getScalarValueRe("a"), 0.001);
	}
    public void testWhitespace09() {
        ml.executeExpression("a=5+3");
		assertEquals(8.0, ml.getScalarValueRe("a"), 0.001);
	}
    public void testWhitespace10() {
        ml.executeExpression(" a = 6 + 3");
		assertEquals(9.0, ml.getScalarValueRe("a"), 0.001);
	}

    public void testWhitespace11() {
        ml.executeExpression(" a = min (2,3)");
		assertEquals(2.0, ml.getScalarValueRe("a"), 0.001);
	}

    public void testWhitespace12() {
        ml.executeExpression(" a = min   ( 4 , 3 ) ");
		assertEquals(3.0, ml.getScalarValueRe("a"), 0.001);
	}

    public void testWhitespace13() {
        ml.executeExpression(" a = min   ( max(-1   , 2) , 3 ) ");
		assertEquals(2.0, ml.getScalarValueRe("a"), 0.001);
	}

    public void testWhitespace14() {
        ml.executeExpression(" a = max   (min(1-2, 4) , 2 ) ");
		assertEquals(2.0, ml.getScalarValueRe("a"), 0.001);
	}

    public void testWhitespace15() {
        ml.executeExpression(" a = min   ( 4 , 3 ) + 5 ");
		assertEquals(8.0, ml.getScalarValueRe("a"), 0.001);
	}

	/************* assignments ****************************************/
    public void testAssign01() {
        ml.executeExpression("a=1;");
		assertEquals(1.0, ml.getScalarValueRe("a"), 0.001);
	}
    public void testAssign02() {
        ml.executeExpression("a=2;");
		assertEquals(2.0, ml.getScalarValueRe("a"), 0.001);
	}
    public void testAssign04() {
        ml.executeExpression("a=123;b=456;");
		assertEquals(123.0, ml.getScalarValueRe("a"), 0.001);
		assertEquals(456.0, ml.getScalarValueRe("b"), 0.001);
	}

   /************* sign ****************************************/
    public void testSign01a() {
        ml.executeExpression("a=2;");
        ml.executeExpression("b=-a;");
		assertEquals(-2.0, ml.getScalarValueRe("b"), 0.001);	
	}

    public void testSign01b() {
        ml.executeExpression("a=2;");
        ml.executeExpression("b=+a;");
        assertEquals(2.0, ml.getScalarValueRe("b"), 0.001);    
    }

    public void testSign01c() {
        ml.executeExpression("a=+2;");
        ml.executeExpression("b=-a;");
        assertEquals(-2.0, ml.getScalarValueRe("b"), 0.001);    
    }

    public void testSign01d() {
        ml.executeExpression("a=-22;");
        ml.executeExpression("b=-a;");
        assertEquals(22.0, ml.getScalarValueRe("b"), 0.001);    
    }

    public void testSign02() {
        ml.executeExpression("a=2;");
        ml.executeExpression("b=(7)-a;");
		assertEquals(5.0, ml.getScalarValueRe("b"), 0.001);	
	}
    
    public void testSign03() {
        ml.executeExpression("a=2;");
        ml.executeExpression("b=(-a)+5;");
		assertEquals(3.0, ml.getScalarValueRe("b"), 0.001);	
	}
    
    public void testSign04() {
        ml.executeExpression("a=(-6);");
        assertEquals(-6.0, ml.getScalarValueRe("a"), 0.001);	
	}
    
    public void testSign05() {
        ml.executeExpression("a=(3)-8;");
        assertEquals(-5.0, ml.getScalarValueRe("a"), 0.001);	
	}
    
    public void testSign06() {
        ml.executeExpression("a=(+2)-3;");
        assertEquals(-1.0, ml.getScalarValueRe("a"), 0.001);	
	}
    
    /************* matrix ****************************************/
    public void testMatrix01() {
        double[][] dr = {{1.0, 2.0, 1.0},{3.0, 4.0, 5.0}};
		ml.executeExpression("d=[1,2,1\n3,4,5]");
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d")));
	}

    public void testMatrix02() {
        double[][] dr = {{1.0, 2.0, 3.0},{3.0, 4.0, 5.0}};



		ml.executeExpression("d=[1 2 3\n3,4,5]");
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d")));
	}

   public void testMatrix03() {
        double[][] dr = {{1.0, 4.0, 3.0},{3.0, 4.0, 5.0}};
		ml.executeExpression("a=4");
 		ml.executeExpression("d=[1 a 3\n3,4,5]");
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d")));
	}

    public void testMatrix04() {
        double[][] dr = {{1.0, 2.0, 3.0, 4.0}};
		ml.executeExpression("d=[1 2 3 ,4]");
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d")));
	}

    public void testMatrix05() {
        double[][] dr = {{1.0, -5.0, 3.0},{3.0, 4.0, 5.0}};
		ml.executeExpression("d=[1 2-7 3\n3,4,5]");
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d")));
	}

    public void testMatrix06() {
        double[][] dr = {{2.0, -5.0, 11.0},{3.0, 4.0, 11.0}};
		ml.executeExpression("a=2;b=3;c=11;");
		ml.executeExpression("d=[a 2-7 c\n b,4,c]");
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d")));
	}

    public void testMatrix07() {
        double[][] dr = {{1.0, 5.0, 3.0, 4.0}};
		ml.executeExpression("d=[1 min(5, 6 ) 3 ,4]");
 		assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("d")));
	}

    public void testMatrix08() {
        // there has been a bug when parsing " ]" the trailing "]" has not been recognized
        double[][] dr = {{5.0, 6.0, 4.0}};
        ml.executeExpression("dd=[5,6,4 ]");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("dd")));
    }

    public void testMatrix09() {
        // there has been a bug when parsing " ]" the trailing "]" has not been recognized
        double[][] dr = {{2.0, 6.0, 8.0}};
        ml.executeExpression("[2, 6,8 ]");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("ans")));
    }

    public void testMatrix10() {
        // there has been a bug when parsing " ]" the trailing "]" has not been recognized
        double[][] dr = {{1.0, 6.0, 8.0}};
        ml.executeExpression(" [ 1,6 ,8 ]");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("ans")));
    }

    public void testMatrix11() {
        // there has been a bug when parsing " ]" the trailing "]" has not been recognized
        double[][] dr = {{5.0, 6.0, 7.0}};
        ml.executeExpression("[ 5, 6 , 7 ] ");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("ans")));
    }

    public void testMatrix12() {
        // there has been a bug when parsing " ]" the trailing "]" has not been recognized
        double[][] dr = {{5.0, 6.0, 9.0}};
        ml.executeExpression("[5,      6 9        ]");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("ans")));
    }

    // bugfix for: sin(1-1)' had been throwing an error
    public void testUnaryOperator01() {
        ml.executeExpression("a=sin(1-1)';");
        assertEquals(0.0, ml.getScalarValueRe("a"), 0.001);
    }

    public void testUnaryOperator02() {
        double[][] dr = {{5.0, 6.0, 9.0}};
        ml.executeExpression("a=[5;6;9]'");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("a")));
    }
    public void testUnaryOperator03() {
        // testcase for bug: a=[m]' threw an error
        double[][] dr = {{5.0},{ 3.0}};
        ml.executeExpression("m=3");
        ml.executeExpression("a=[5,m]'");
        assertTrue(Compare.ArrayEquals(dr, ml.getArrayValueRe("a")));
    }
    
    
    /************** funtions ***********/
    public void testFunctionParameters01() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("m=ceil(45) ");
        }
        catch (Exception e)
        {
            assertTrue(false);
            return;
        }
        assertTrue(true);
    }

    public void testFunctionParameters02() {
    	// BUG1523328:  ceil(4;5] did not throw an error, neither
    	// did          ceil(4;5)
        ml.throwErrorsB=true;
		try {
    		ml.executeExpression("m=ceil(4;5) ");
		}
		catch (Exception e)
		{
    	    assertTrue(true);
    	    return;
		}
        assertTrue(false);
    }

    public void testFunctionParameters03() {
        ml.executeExpression("a=ceil(4.5);");
        assertEquals(5.0, ml.getScalarValueRe("a"), 0.001);    
    }

    public void testOperatorParsing01() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("m=+-44 ");
        }
        catch (Exception e)
        {
            assertTrue(false);
            return;
        }
        assertEquals(-44.0, ml.getScalarValueRe("m"), 0.001);
    }

    public void testOperatorParsing02() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("m=*4 ");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    public void testOperatorParsing03() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("m=3-*4 ");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    public void testOperatorParsing04() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("m=*4 ");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }


    public void testOperatorParsing05a() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("A=4;B=/4; ");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    public void testOperatorParsing05b() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("+/4; ");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    public void testOperatorParsing05c() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("*/4; ");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    public void testOperatorParsing06a() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("m=-+-++4; ");
        }
        catch (Exception e)
        {
            assertTrue(false);
            return;
        }
        assertEquals(4.0, ml.getScalarValueRe("m"), 0.001);
    }

    public void testOperatorParsing06b() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("m=-+++46; ");
        }
        catch (Exception e)
        {
            assertTrue(false);
            return;
        }
        assertEquals(-46.0, ml.getScalarValueRe("m"), 0.001);
    }

    public void testOperatorParsing06c() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("b=99;m=---b; ");
        }
        catch (Exception e)
        {
            assertTrue(false);
            return;
        }
        assertEquals(-99.0, ml.getScalarValueRe("m"), 0.001);
    }

    // =^7 +^3 -^6 *^5 ^^5 !^7
    public void testOperatorParsing07() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("a=^7");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
    
    public void testOperatorParsing08() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("+^8");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
    
    public void testOperatorParsing09() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("-^7");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
    
    public void testOperatorParsing10() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("*^7");
            ml.executeExpression("/^7");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }

    public void testOperatorParsing11() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("^^7");
        }
        catch (Exception e)
        {
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
    
    // -(--+++-3-5)
    public void testOperatorParsing06d() {
        ml.throwErrorsB=true;
        try {
            ml.executeExpression("-(--+++-+3-5); ");
        }
        catch (Exception e)
        {
            assertTrue(false);
            return;
        }
        assertEquals(8.0, ml.getScalarValueRe("ans"), 0.001);
    }

    /*************************************************/
    
    
}




