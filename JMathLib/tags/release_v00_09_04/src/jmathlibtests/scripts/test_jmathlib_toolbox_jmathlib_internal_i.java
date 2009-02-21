// test_jmathlib_toolbox_jmathlib_internal_i
package jmathlibtests.scripts; 
import jmathlib.core.interpreter.Interpreter;   
import jmathlib.tools.junit.framework.*;        
import jmathlibtests.Compare;                   

public class test_jmathlib_toolbox_jmathlib_internal_i extends JMathLibTestCase {     
     
    public test_jmathlib_toolbox_jmathlib_internal_i(String name) {           
        super(name);                            
    }                                           
    public static void main (String[] args) {   
        jmathlib.tools.junit.textui.TestRunner.run (suite()); 
    }                                           
 
    public static Test suite() {                
        return new TestSuite(test_jmathlib_toolbox_jmathlib_internal_i.class); 
    } 

    public void test_jmathlib_toolbox_jmathlib_internal_i0()           
    { 
           ml.executeExpression("a=3;");
           assertTrue(3 == ml.getScalarValueRe("a"));
           assertTrue(0 == ml.getScalarValueIm("a"));
         
    }

    public void test_jmathlib_toolbox_jmathlib_internal_i1()           
    { 
           ml.executeExpression("a=2+3i;");
           assertTrue(2 == ml.getScalarValueRe("a"));
           assertTrue(3 == ml.getScalarValueIm("a"));
         
    }

    public void test_jmathlib_toolbox_jmathlib_internal_i2()           
    { 
           ml.executeExpression("a=i;");
           assertTrue(0 == ml.getScalarValueRe("a"));
           assertTrue(1 == ml.getScalarValueIm("a"));
         
    }
}
