package jmathlibtests.toolbox.symbolic;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testSimplify extends TestCase {
	protected Interpreter ml;
	
    public testSimplify(String name) {
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
		return new TestSuite(testSimplify.class);
	}

    public void testSimplify1()
    {
        String input = "simplify(x + 0)";
        String output = "x";
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
                
        assertEquals(output, result);
    }

    public void testSimplify2()
    {
        String input = "simplify(x * 0)";
        String output = "0";
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
                
        assertEquals(output, result);
    }

    public void testSimplify3()
    {
        String input = "simplify(x + (y * 0) + 0)";
        String output = "x";
        
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
