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
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_jmathlib_internal_plusplus.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_jmathlib_internal_pi.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_jmathlib_internal_minusminus.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_jmathlib_internal_j.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_jmathlib_internal_i.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_jmathlib_internal_e.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_general_uint8.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_general_uint32.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_general_uint16.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_general_sign.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_general_int8.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_general_int64.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_general_int32.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_general_int16.suite());
            suite.addTest(jmathlibtests.scripts.test_jmathlib_toolbox_general_bitor.suite());
                                                        
            return suite;                               
        }                                               
    }                                                   
