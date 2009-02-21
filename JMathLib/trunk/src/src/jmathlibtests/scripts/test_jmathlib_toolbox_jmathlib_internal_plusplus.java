// test_jmathlib_toolbox_jmathlib_internal_plusplus
package jmathlibtests.scripts; 
import jmathlib.core.interpreter.Interpreter;   
import jmathlib.tools.junit.framework.*;        
import jmathlibtests.Compare;                   

public class test_jmathlib_toolbox_jmathlib_internal_plusplus extends JMathLibTestCase {     
     
    public test_jmathlib_toolbox_jmathlib_internal_plusplus(String name) {           
        super(name);                            
    }                                           
    public static void main (String[] args) {   
        jmathlib.tools.junit.textui.TestRunner.run (suite()); 
    }                                           
 
    public static Test suite() {                
        return new TestSuite(test_jmathlib_toolbox_jmathlib_internal_plusplus.class); 
    } 

    public void test_jmathlib_toolbox_jmathlib_internal_plusplus0()           
    { 
           ml.executeExpression("a=2;");
           ml.executeExpression("a++;");
           assertTrue(3 == ml.getScalarValueRe("a"));
           assertTrue(0 == ml.getScalarValueIm("a"));
         
    }
}
