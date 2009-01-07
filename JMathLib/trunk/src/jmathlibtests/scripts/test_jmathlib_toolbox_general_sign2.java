// test_src_jmathlib_toolbox_general_sign2
package jmathlibtests.scripts; 
import jmathlib.core.interpreter.Interpreter;   
import jmathlib.tools.junit.framework.*;        
import jmathlibtests.Compare;                   

public class test_src_jmathlib_toolbox_general_sign2 extends TestCase {     
    protected Interpreter ml;                   
     
    public test_src_jmathlib_toolbox_general_sign2(String name) {           
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
        return new TestSuite(test_src_jmathlib_toolbox_general_sign2.class); 
    } 
    public void test_test_src_jmathlib_toolbox_general_sign2001()           
    { 
           ml.executeExpression("a=sign(-12);");
           assertTrue(-1 == ml.getScalarValueRe("a"));
           assertTrue(0  == ml.getScalarValueIm("a"));
         
    }
}
