// test_jmathlib_toolbox_general_int64
package jmathlibtests.scripts; 
import jmathlib.core.interpreter.Interpreter;   
import jmathlib.tools.junit.framework.*;        
import jmathlibtests.Compare;                   

public class test_jmathlib_toolbox_general_int64 extends JMathLibTestCase {     
     
    public test_jmathlib_toolbox_general_int64(String name) {           
        super(name);                            
    }                                           
    public static void main (String[] args) {   
        jmathlib.tools.junit.textui.TestRunner.run (suite()); 
    }                                           
 
    public static Test suite() {                
        return new TestSuite(test_jmathlib_toolbox_general_int64.class); 
    } 

    public void test_jmathlib_toolbox_general_int640()           
    { 
           ml.executeExpression("a=int64(8);");
           ml.executeExpression("b=class(a);");
           assertEquals( "int64", ml.getString("b"));
         
    }
}
