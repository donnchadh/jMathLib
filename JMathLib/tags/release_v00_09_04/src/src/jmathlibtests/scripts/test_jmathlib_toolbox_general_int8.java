// test_jmathlib_toolbox_general_int8
package jmathlibtests.scripts; 
import jmathlib.core.interpreter.Interpreter;   
import jmathlib.tools.junit.framework.*;        
import jmathlibtests.Compare;                   

public class test_jmathlib_toolbox_general_int8 extends JMathLibTestCase {     
     
    public test_jmathlib_toolbox_general_int8(String name) {           
        super(name);                            
    }                                           
    public static void main (String[] args) {   
        jmathlib.tools.junit.textui.TestRunner.run (suite()); 
    }                                           
 
    public static Test suite() {                
        return new TestSuite(test_jmathlib_toolbox_general_int8.class); 
    } 

    public void test_jmathlib_toolbox_general_int80()           
    { 
           ml.executeExpression("a=int8(88);");
           ml.executeExpression("b=class(a);");
           assertEquals( "int8", ml.getString("b"));
         
    }
}
