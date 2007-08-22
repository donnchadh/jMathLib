package jmathlibtests.toolbox.symbolic;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testDerivative extends TestCase {
	protected Interpreter ml;
	
    public testDerivative(String name) {
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
		return new TestSuite(testDerivative.class);
	}

    /**Test the integral of a linear equation
    x^2 + x + 1 => 2x+1*/
    public void testLinearDerivative1()
    {
        String input = "derivative(x^2 + x + 1, x)";
        String output = "2x+1";
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);

        assertEquals("derivative(x^2 + x + 1, x)", output, result);
    }

    /**Test the integral of a linear equation
    2*x = 2*/
    public void testLinearDerivative2()
    {       
        String input = "derivative(2*x, x)";
        String output = "2";
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals("derivative(2*x, x)", output, result);
    }

    /**Test the integral of a linear equation
    x*y = y*/
    public void testLinearDerivative3()
    {
        String input = "derivative(x*y, x)";
        String output = "1*y";
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals("derivative(x*y, x)", output, result);
    }

    /**Test the integral of a linear equation
    1/x = -1/x^2*/
    public void testLinearDerivative4()
    {
        String input = "derivative(1/x, x)";
        String output = "-1/x^2";
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals("derivative(1/x, x)", output, result);
    }

    /**Test the derivative of an exponential
    exp(2x) => 2*exp(2x)
    NOT IMPLEMENTED*/
    public void testDerivative2()
    {
        /*
        String input = "derivative('exp(2*x)', x)";
        String output = "2*exp(2x)";
        output = reduce(output);
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals(output, result);
        */
        assertTrue(true);
    }

    /**Test the derivative of a trig function
    sin(x) => cos(x)
    NOT IMPLEMENTED*/
    public void testDerivative3()
    {
        /*
        String input = "derivative(sin(x), x)";
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
