    package jmathlibtests.scripts;                      
                                                        
    import jmathlib.tools.junit.framework.*;            
                                                        
    public class AllTests {                             
                                                        
        public static void main (String[] args) {       
           jmathlib.tools.junit.textui.TestRunner.run (suite()); 
        }                                               
       public static Test suite ( ) {                   
            TestSuite suite= new TestSuite("script functions"); 
                                                        
            /* include subdirectories here */           
            // none                                     
                                                        
            /* include tests in this directory here */  
            suite.addTest(jmathlibtests.toolbox.net.testUrlread.suite());
            suite.addTest(jmathlibtests.scripts.test_src_jmathlib_toolbox_general_sign6.suite());
            suite.addTest(jmathlibtests.scripts.test_src_jmathlib_toolbox_general_sign5.suite());
            suite.addTest(jmathlibtests.scripts.test_src_jmathlib_toolbox_general_sign4.suite());
            suite.addTest(jmathlibtests.scripts.test_src_jmathlib_toolbox_general_sign3.suite());
            suite.addTest(jmathlibtests.scripts.test_src_jmathlib_toolbox_general_sign2.suite());
            suite.addTest(jmathlibtests.scripts.test_src_jmathlib_toolbox_general_sign1.suite());
            suite.addTest(jmathlibtests.scripts.test_src_jmathlib_toolbox_general_bitor2.suite());
            suite.addTest(jmathlibtests.scripts.test_src_jmathlib_toolbox_general_bitor1.suite());
                                                        
            return suite;                               
        }                                               
    }                                                   
