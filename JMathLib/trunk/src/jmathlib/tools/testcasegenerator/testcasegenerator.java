package jmathlib.tools.testcasegenerator;

import java.io.*;
import java.util.Stack;

import jmathlib.core.interpreter.ErrorLogger;

/**An external function for getting a directory listing  */
public class testcasegenerator 
{
    
    String  testCaseDirS   = "src/jmathlibtests/scripts";
    Stack   testStack = new Stack();

    /**
     * Main method of the test case generator
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("testcasegenerator");
        
        String baseS = "src/jmathlib";

        testcasegenerator testgen = new testcasegenerator();
        
        testgen.processDir(baseS);
        
        testgen.createAllTestsFile();
        
    }
    
    /** Go through all directories
     * 
     * @param baseS
     */
    public void processDir(String baseS)
    {
        System.out.println("directory "+baseS);
        File dir = new File(baseS, "."); 
        String[] files = dir.list();
     
        
        for (int i=0; i<files.length ; i++)
        {

            String name = files[i]; 
            File   f    = new File(baseS, name);
            if (f.isDirectory() )
            {
                //System.out.println(baseS+"/"+files[i]+"/ dir");
                if ( !files[i].endsWith(".svn") )
                processDir(baseS+"/"+files[i]);
            }
            else
            {
                //System.out.println(baseS+"/"+files[i]);
                if ( !files[i].endsWith(".gif")       &&
                     !files[i].endsWith(".p")         &&
                     !files[i].endsWith(".html")      &&
                     !files[i].endsWith(".txt")      &&
                     !files[i].endsWith(".jpg")      &&
                     !files[i].endsWith(".properties")   )
                processFile(baseS+"/"+files[i]);
            }
            
        } // end for

    } // end processDir

    /**
     * 
     * @param fileS file to process and check for testcases
     */
    public void processFile(String fileS)
    {
        boolean testCaseFoundB = false;
        int     testCaseNumber = 0; 
        Stack   testCasesStack = new Stack();


        
        //System.out.println("anaylzing "+ fileS);
        
        
        // open file and read m-file line by line
        String bufferS="";
        try 
        {          
            File   f    = new File(fileS);
            BufferedReader inReader = new BufferedReader(new FileReader(f));
            String line;
            while (true)
            {      
                // read a line from the file
                line = inReader.readLine();
                     
                if(testCaseFoundB                                && 
                   (line==null || line.startsWith("%!@testcase"))   )
                {
                    // reading in a testcase finished be reaching the
                    //   end of file or finding a new keyword %!testcase
                    testCaseFoundB = false;
                    testCasesStack.push(bufferS);
                }

                // end of file reached
                if(line==null)
                    break;
                
                
                if (line.startsWith("%!@testcase"))
                {
                    // found a new testcase
                    System.out.println("******* new testcase found");
                    testCaseNumber++;
                    testCaseFoundB = true;
                    bufferS = "";
                }
                else if (line.startsWith("%!"))
                {
                    // build up testcase
                    System.out.println("******* "+line);
                    bufferS += "         "+ line.substring(2) + "\n";
                }
                
                
            } // end while
            inReader.close();
        }
        catch (Exception e)
        {
            System.out.println(" exception "+fileS);
            e.printStackTrace();
        }          

        // in case at least one test case was found -> create test-suite
        if (!testCasesStack.empty())
            createTestCase(fileS, testCasesStack);

        
    } // end process File
    
    /**
     * 
     * @param fileS
     * @param caseNumber
     * @param testcaseS
     */
    public void createTestCase (String fileS, Stack testCaseStack)
    {
        
        fileS = fileS.replaceFirst("src/", "");  // remove src/ at beginning
        fileS = fileS.replace(".java", "");      // remove .java suffix
        fileS = fileS.replace(".m", "");         // remove .m suffix
        fileS = fileS.replace(".int", "");       // remove .int suffix
        fileS = fileS.replace('/', '_');         // change directory-sign into underline
        fileS = fileS.replace('.', '_');         // change directory-sign into underline
        fileS = "test_"+fileS;                   // add "test_" to make JUnit aware of this class
        
        // put filename on tests-stack
        testStack.push(fileS);
        
        String s = "";
        s+="// "+fileS + "\n";
        s+="package jmathlibtests.scripts; \n";
        s+="";
        s+="import jmathlib.core.interpreter.Interpreter;   \n";
        s+="import jmathlib.tools.junit.framework.*;        \n";
        s+="import jmathlibtests.Compare;                   \n";
        s+="\n";
        s+="public class "+ fileS +" extends JMathLibTestCase {     \n";
        s+="     \n";
        s+="    public "+ fileS +"(String name) {           \n";
        s+="        super(name);                            \n";
        s+="    }                                           \n";
        s+="    public static void main (String[] args) {   \n";
        s+="        jmathlib.tools.junit.textui.TestRunner.run (suite()); \n";
        s+="    }                                           \n";
        s+=" \n";
        s+="    public static Test suite() {                \n";
        s+="        return new TestSuite("+ fileS +".class); \n";
        s+="    } \n";
    
        
        int n = 0;
        while (!testCaseStack.empty())
        {

        s+="\n";
        s+="    public void "+ fileS + n + "()           \n";
        s+="    { \n";
        //s+="        ml.executeExpression(\"a=testFunction001(134);\"); ";
        //s+="        assertTrue(136.0 == ml.getScalarValueRe(\"a\")); ";
        
        s+= (String)testCaseStack.pop();

        s+="    }\n";

        n++;
        }
        
        
        s+="}\n";
        
        
        try 
        {
            File   f    = new File(testCaseDirS+"/"+ fileS +".java");
            BufferedWriter outWriter = new BufferedWriter( new FileWriter(f));

            outWriter.write(s);
            
            outWriter.close();
        }
        catch(Exception e)
        {
            System.out.println("function exception - " + e.getMessage());
        }

        
    }   // end createTestCase
    
    /**
     * 
     */
    public void createAllTestsFile()
    {
        String s="";
        
        s+="    package jmathlibtests.scripts;                          \n";
        s+="                                                            \n";
        s+="    import jmathlib.tools.junit.framework.*;                \n";
        s+="                                                            \n";
        s+="    public class AllTests {                                 \n";
        s+="                                                            \n";
        s+="        public static void main (String[] args) {           \n";
        s+="           jmathlib.tools.junit.textui.TestRunner.run (suite()); \n";
        s+="        }                                                   \n";
        s+="       public static Test suite ( ) {                       \n";
        s+="            TestSuite suite= new TestSuite(\"script functions\"); \n";
        s+="                                                            \n";
        s+="            /* include subdirectories here */               \n";
        s+="            // none                                         \n";
        s+="                                                            \n";
        s+="            /* include tests in this directory here */      \n";
        
        s+="            suite.addTest(jmathlibtests.toolbox.net.testUrlread.suite());\n";

        while (!testStack.isEmpty())
        {
            String fileS = (String)testStack.pop();
            
            s+="            suite.addTest(jmathlibtests.scripts."+ fileS +".suite());\n";
        }
        
        s+="                                                            \n";
        s+="            return suite;                                   \n";
        s+="        }                                                   \n";
        s+="    }                                                       \n";

    
        try 
        {
            File   f    = new File(testCaseDirS+"/AllTests.java");
            BufferedWriter outWriter = new BufferedWriter( new FileWriter(f));

            outWriter.write(s);
            outWriter.close();
        }
        catch(Exception e)
        {
            System.out.println("AllTests exception - " + e.getMessage());
        }

    
    } // createAllTestsFile
    
}


