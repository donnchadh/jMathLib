package jmathlib.tools.testcasegenerator;

import java.io.*;

import jmathlib.core.interpreter.ErrorLogger;

/**An external function for getting a directory listing         */
public class testcasegenerator 
{
    
    static String  testCaseDirS = "src/jmathlibtests";
    
    public static void main(String[] args) {
        System.out.println("testcasegenerator");
        
        String baseS = "src/jmathlib";

        
        processDir(baseS);
        
    }
    
    
    public static void processDir(String baseS)
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

                processFile(baseS+"/"+files[i]);
            }
            
        }

    }

    public static void processFile(String fileS)
    {
        
        //System.out.println("anaylzing "+ fileS);
        
        // open file and read m-file line by line
        String bufferS="";
        try 
        {          
            File   f    = new File(fileS);
            BufferedReader inReader = new BufferedReader(new FileReader(f));
            String line;
            while ((line = inReader.readLine()) != null)
            {               
                if (line.startsWith("%!"))
                {
                    System.out.println("******* "+line);
                    bufferS += "         "+ line.substring(2) + "\n";
                }
            }
            inReader.close();
        }
        catch (Exception e)
        {
            System.out.println(" exception "+fileS);
        }          

        
        if (!bufferS.equals(""))
        {
            createTestCase(fileS, bufferS);
        }
        
    } // end process File
    
    
    public static void createTestCase (String fileS, String testcaseS)
    {
        
        fileS = fileS.replace('/', '_');
        fileS = fileS.replace('.', '_');
        fileS = "test_"+fileS;
        
        String s = "";
        s+="// "+fileS + "\n";
        s+="package jmathlibtests; \n";
        s+="";
        s+="import jmathlib.core.interpreter.Interpreter; \n";
        s+="import jmathlib.tools.junit.framework.*; \n";
        s+="import jmathlibtests.Compare; \n";
        s+="\n";
        s+="public class "+ fileS +" extends TestCase { \n";
        s+="    protected Interpreter ml; \n";
        s+="     \n";
        s+="    public "+ fileS +"(String name) { \n";
        s+="        super(name); \n";
        s+="    } \n";
        s+="    public static void main (String[] args) { \n";
        s+="        jmathlib.tools.junit.textui.TestRunner.run (suite()); \n";
        s+="    } \n";
        s+="    protected void setUp() { \n";
        s+="        ml = new Interpreter(true); \n";
        s+="    } \n";
        s+="    protected void tearDown() { \n";
        s+="        ml = null; \n";
        s+="    } \n";
        s+=" \n";
        s+="    public static Test suite() { \n";
        s+="        return new TestSuite("+ fileS +".class); \n";
        s+="    } \n";
    
        s+="    public void test_"+ fileS +"001() \n";
        s+="    { \n";
        //s+="        ml.executeExpression(\"a=testFunction001(134);\"); ";
        //s+="        assertTrue(136.0 == ml.getScalarValueRe(\"a\")); ";
        s+= testcaseS;
        s+="    }\n";
        
        
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

        
    }    
}


