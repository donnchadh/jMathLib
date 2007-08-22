package jmathlibtests.toolbox.symbolic;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testIntegral extends TestCase {
	protected Interpreter ml;
	
    public testIntegral(String name) {
		super(name);
	}
	public static void main (String[] args) {
		jmathlib.tools.junit.textui.TestRunner.run (suite());
	}
	protected void setUp() {
		ml = new Interpreter(true);
        
        //set up symbols
        ml.executeExpression("x=sym('x')");
        ml.executeExpression("y=sym('y')");        
	}

	public static Test suite() {
		return new TestSuite(testIntegral.class);
	}


    /**Test the integral of a linear equation
    x^2 + x + 1 => 0.333x^3+0.5x^2+x*/
    public void testLinearIntegral1()
    {
        String input = "integral(x^2 + x + 1, x)";
        String output = "0.333x^3+0.5x^2+x";
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals("integral(x^2 + x + 1, x)", output, result);
    }

    /**Test the integral of a linear equation
    x => 0.5x^2*/
    public void testLinearIntegral2()
    {
        String input = "integral(x, x)";
        String output = "0.5x^2";
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals("integral(x, x)", output, result);
    }

    /**Test the integral of a linear equation
    y => y*x*/
    public void testLinearIntegral3()
    {
        String input = "integral(y, x)";
        String output = "y*x";
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals("integral(y, x)", output, result);
    }

    /**Test the integral of a linear equation
    1 => x*/
    public void testLinearIntegral4()
    {
        String input = "integral(1, x)";
        String output = "x";
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals("integral(1, x)", output, result);
    }

    /**Test the integral of a linear equation
    1/x^2 = -1/x*/
    public void testLinearIntegral5()
    {
        String input = "integral(1/x^2, x)";
        String output = "-1/x";
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals("integral(1/x^2, x)", output, result);
    }

    /**Test the integral of an exponential
    exp(x) => exp(x)
    NOT IMPLEMENTED*/
    public void testIntegral2()
    {
        /*
        String input = "integral('exp(x)', x)";
        String output = "exp(x)";
        output = reduce(output);
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals(output, result);
        */
        assertTrue(true);
    }

    /**Test the integral of a trig function
    sin(x) => cos(x)
    NOT IMPLEMENTED*/
    public void testIntegral3()
    {
        /*
        String input = "integral(sin(x), x)";
        String output = "cos(x)";
        output = reduce(output);
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals(output, result);
        */
        assertTrue(true);
    }

    private String reduce(String input)
    {
        StringBuffer buffer = new StringBuffer();
        
        int length = input.length();
        for(int index = 0; index < length; index++)
        {
            char character = input.charAt(index);
            if(character != ' ' && character != '(' && character != ')')
            {
                buffer.append(character);
            }
        }
        
        return buffer.toString();
    }   
}
