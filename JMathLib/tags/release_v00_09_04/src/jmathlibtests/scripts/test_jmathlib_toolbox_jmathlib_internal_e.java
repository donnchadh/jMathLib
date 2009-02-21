// test_jmathlib_toolbox_jmathlib_internal_e
package jmathlibtests.scripts; 
import jmathlib.core.interpreter.Interpreter;   
import jmathlib.tools.junit.framework.*;        
import jmathlibtests.Compare;                   

public class test_jmathlib_toolbox_jmathlib_internal_e extends JMathLibTestCase {     
     
    public test_jmathlib_toolbox_jmathlib_internal_e(String name) {           
        super(name);                            
    }                                           
    public static void main (String[] args) {   
        jmathlib.tools.junit.textui.TestRunner.run (suite()); 
    }                                           
 
    public static Test suite() {                
        return new TestSuite(test_jmathlib_toolbox_jmathlib_internal_e.class); 
    } 

    public void test_jmathlib_toolbox_jmathlib_internal_e0()           
    { 
           ml.executeExpression("clear('all');");
           ml.executeExpression("a=e;");
           assertEquals( 2.71828182, ml.getScalarValueRe("a"), 0.0001);
           assertTrue( 0 == ml.getScalarValueIm("a"));
         
    }
}
