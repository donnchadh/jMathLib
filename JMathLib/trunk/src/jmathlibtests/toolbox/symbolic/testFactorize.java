package jmathlibtests.toolbox.symbolic;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testFactorize extends TestCase {
	protected Interpreter ml;
	
    public testFactorize(String name) {
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
    protected void tearDown() {
        ml = null;
    }

	public static Test suite() {
		return new TestSuite(testFactorize.class);
	}

    public void testFactorize1()
    {
        String input = "factorise('x^2 + 2*x + 1', x)";
        String output = "(1+x)^2";
        output = reduce(output);
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals(output, result);
    }

    public void testFactorize2()
    {
        String input = "factorise('x^2 + 2*x', x)";
        String output = "x*(x+1)";
        output = reduce(output);
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals(output, result);
    }

    public void testFactorize3()
    {
        String input = "factorise('x^2 + 3*x + 1', x)";
        String output = "(x+1)*(x+2)";
        output = reduce(output);
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals(output, result);
    }

    public void testFactorize4()
    {
        String input = "factorise('x^3 + 3*x^2 + 3*x + 1', x)";
        String output = "(x+1)^3";
        output = reduce(output);
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals(output, result);
    }

    public void testFactorize5()
    {
        String input = "factorise('x^3 + 4*x^2 + 5*x + 2', x)";
        String output = "(x+1)^2*(x+2)";
        output = reduce(output);
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals(output, result);
    }

    public void testFactorize6()
    {
        String input = "factorise('x^3 + 6*x^2 + 11*x + 6', x)";
        String output = "(x+1)*(x+2)*(x+3)";
        output = reduce(output);
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals(output, result);
    }

    public void testFactorize7()
    {
        String input = "factorise('2*x^2 + 5*x + 2', x)";
        String output = "(2*x+1)*(x+2)";
        output = reduce(output);
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
        
        assertEquals(output, result);
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
