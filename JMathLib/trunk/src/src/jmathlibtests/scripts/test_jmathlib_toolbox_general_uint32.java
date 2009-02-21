// test_jmathlib_toolbox_general_uint32
package jmathlibtests.scripts; 
import jmathlib.core.interpreter.Interpreter;   
import jmathlib.tools.junit.framework.*;        
import jmathlibtests.Compare;                   

public class test_jmathlib_toolbox_general_uint32 extends JMathLibTestCase {     
     
    public test_jmathlib_toolbox_general_uint32(String name) {           
        super(name);                            
    }                                           
    public static void main (String[] args) {   
        jmathlib.tools.junit.textui.TestRunner.run (suite()); 
    }                                           
 
    public static Test suite() {                
        return new TestSuite(test_jmathlib_toolbox_general_uint32.class); 
    } 

    public void test_jmathlib_toolbox_general_uint320()           
    { 
           ml.executeExpression("a=uint32(88);");
           ml.executeExpression("b=class(a);");
           assertEquals( "uint32", ml.getString("b"));
         
    }
}
