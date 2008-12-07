package jmathlibtests.toolbox.symbolic;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.tools.junit.framework.*;

public class testExpand extends TestCase {
	protected Interpreter ml;
	
    public testExpand(String name) {
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
		return new TestSuite(testExpand.class);
	}


    public void testExpand1() {
        String input = "expand((x+1)^2)";
        String output = "x^2+2x+1";
        output = reduce(output);

        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
       
        assertEquals(output, result);
    }

    public void testExpand2() {
        String input = "expand((x+y)^2)";
        String output = "2x*y+x^2+y^2";
        
        output = reduce(output);
        
        ml.executeExpression(input);
        String result = ml.getResult();
        result = reduce(result);
       
        assertEquals(output, result);
    }

    public void testExpand3() {
        String input = "expand((x+1) * (x-1))";
        String output = "x^2+-1";
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
