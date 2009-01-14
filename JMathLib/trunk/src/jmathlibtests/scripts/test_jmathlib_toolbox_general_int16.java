// test_jmathlib_toolbox_general_int16
package jmathlibtests.scripts; 
import jmathlib.core.interpreter.Interpreter;   
import jmathlib.tools.junit.framework.*;        
import jmathlibtests.Compare;                   

public class test_jmathlib_toolbox_general_int16 extends JMathLibTestCase {     
     
    public test_jmathlib_toolbox_general_int16(String name) {           
        super(name);                            
    }                                           
    public static void main (String[] args) {   
        jmathlib.tools.junit.textui.TestRunner.run (suite()); 
    }                                           
 
    public static Test suite() {                
        return new TestSuite(test_jmathlib_toolbox_general_int16.class); 
    } 

    public void test_jmathlib_toolbox_general_int160()           
    { 
           ml.executeExpression("a=int16(8);");
           ml.executeExpression("b=class(a);");
           assertEquals( "int16", ml.getString("b"));
         
    }
}
