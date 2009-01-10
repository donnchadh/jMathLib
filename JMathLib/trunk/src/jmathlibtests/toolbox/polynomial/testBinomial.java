package jmathlibtests.toolbox.polynomial;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testBinomial extends JMathLibTestCase {
	
    public testBinomial(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}

	public static Test suite() {
		return new TestSuite(testBinomial.class);
	}

	public void testBinomial01() {
        assertEvalArrayEquals("a=binomial(2);", 
                "a", 
                new double[][] {{1, 2, 1}}, 
                0.001); 
	}
    public void testBinomial02() {
		assertEvalArrayEquals("a=binomial(3);", 
		        "a", 
		        new double[][] {{1, 3, 3, 1}}, 
		        0.001); 
	}
    public void testBinomial03() {
        assertEvalArrayEquals("a=binomial(4);", 
                "a", 
                new double[][] {{1, 4, 6, 4, 1}}, 
                0.001); 
    }
    public void testBinomial04() {
        assertEvalArrayEquals("a=binomial(5);", 
                "a", 
                new double[][] {{1, 5, 10, 10, 5, 1}}, 
                0.001); 
    }
    public void testBinomial05() {
        assertEvalArrayEquals("a=binomial(6);", 
                "a", 
                new double[][] {{1, 6, 15, 20, 15, 6, 1}}, 
                0.001); 
    }



}
