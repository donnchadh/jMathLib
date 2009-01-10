package jmathlib.tools.junit.framework;

import jmathlib.core.interpreter.Interpreter; 
import jmathlibtests.Compare; 


public class JMathLibTestCase extends TestCase
{
	
    protected Interpreter ml; 

    public JMathLibTestCase (String name)
    {
        super(name);
    }

    protected void setUp() { 
        ml = new Interpreter(true); 
    } 
    protected void tearDown() { 
        ml = null; 
    } 

    public void eval(String s)
    {
        ml.executeExpression(s);
    }
    
    public void assertEquals(String a, String b)
    {
        ml.executeExpression(a);
        double[][]ans1D = ml.getArrayValueRe("ans");
        double[][]ans1I = ml.getArrayValueIm("ans");
        
        ml.executeExpression(b);
        double[][]ans2D = ml.getArrayValueRe("ans");
        double[][]ans2I = ml.getArrayValueIm("ans");

        assertTrue(Compare.ArrayEquals(ans1D, ans2D));
        assertTrue(Compare.ArrayEquals(ans1I, ans2I));
        
    }
}
