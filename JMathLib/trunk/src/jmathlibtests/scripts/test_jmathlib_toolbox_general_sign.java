// test_jmathlib_toolbox_general_sign
package jmathlibtests.scripts; 
import jmathlib.core.interpreter.Interpreter;   
import jmathlib.tools.junit.framework.*;        
import jmathlibtests.Compare;                   

public class test_jmathlib_toolbox_general_sign extends TestCase {     
    protected Interpreter ml;                   
     
    public test_jmathlib_toolbox_general_sign(String name) {           
        super(name);                            
    }                                           
    public static void main (String[] args) {   
        jmathlib.tools.junit.textui.TestRunner.run (suite()); 
    }                                           
    protected void setUp() {                    
        ml = new Interpreter(true);             
    }                                           
    protected void tearDown() {                 
        ml = null;                              
    } 
 
    public static Test suite() {                
        return new TestSuite(test_jmathlib_toolbox_general_sign.class); 
    } 

    public void test_jmathlib_toolbox_general_sign0()           
    { 
           ml.executeExpression("a=sign(0i);");
           assertTrue(0 == ml.getScalarValueRe("a"));
           assertTrue(0 == ml.getScalarValueIm("a"));
         
    }

    public void test_jmathlib_toolbox_general_sign1()           
    { 
           ml.executeExpression("a=sign(-5i);");
           assertTrue(0  == ml.getScalarValueRe("a"));
           assertTrue(-1 == ml.getScalarValueIm("a"));
         
    }

    public void test_jmathlib_toolbox_general_sign2()           
    { 
           ml.executeExpression("a=sign(8i);");
           assertTrue(0 == ml.getScalarValueRe("a"));
           assertTrue(1 == ml.getScalarValueIm("a"));
         
    }

    public void test_jmathlib_toolbox_general_sign3()           
    { 
           ml.executeExpression("a=sign(0);");
           assertTrue(0 == ml.getScalarValueRe("a"));
           assertTrue(0 == ml.getScalarValueIm("a"));
         
         
    }

    public void test_jmathlib_toolbox_general_sign4()           
    { 
           ml.executeExpression("a=sign(-12);");
           assertTrue(-1 == ml.getScalarValueRe("a"));
           assertTrue(0  == ml.getScalarValueIm("a"));
         
    }

    public void test_jmathlib_toolbox_general_sign5()           
    { 
           ml.executeExpression("a=sign(11);");
           assertTrue(1 == ml.getScalarValueRe("a"));
           assertTrue(0 == ml.getScalarValueIm("a"));
         
    }
}
