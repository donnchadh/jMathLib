package jmathlib.core.functions;

import jmathlib.core.interpreter.*;
import java.io.*;
import java.net.*;


/**Class for storing and managing the m- and p-functions
 *
 * JMH Should be more aptly named  "WebFunctionLoader"
 */
public class MFileWebLoader extends FunctionLoader
{

    boolean pFileCachingEnabledB = false;
    private URL codeBase;
    private String directory; 
    
    /**Default constructor*/
    public MFileWebLoader(URL _codeBase, String _directory) 
    {
        codeBase = _codeBase;
        directory = _directory;
    }
    
    public URL getCodeBase() {
      return codeBase;
    }
    
    public String getDirectory() {
      return directory;
    }

    /**loads an .m-file via the web
    @param directory = the directory containing the file
    @param mFileName = the name of the m file
    @return the result of the file as a FunktionToken*/
    public Function findFunction(String functionName)
    {
        //JMH TBD find functions on the classpath
        
        
        
        String       code     = "";
        UserFunction function = (UserFunction)getCachedFunction(functionName);
        if (function != null)
            return function;
     
        ErrorLogger.debugLine("MFileWebLoader: loading >"+functionName+".m<");
        
        // open file and read m-file line by line
        try 
        {          
            URL            url      = new URL(codeBase, directory+"/"+functionName+".m");
            InputStream    in       = url.openStream();
            BufferedReader inReader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = inReader.readLine()) != null)
            {               
                code += line + "\n";
            }
            inReader.close();
        }
        catch (Exception e)
        {
            Errors.throwMathLibException("MFileWebLoader: m-file exception via web");
        }          
 
        ErrorLogger.debugLine("MFileWebLoader: code: begin \n"+code+"\ncode end");
        
        // send code to function parser and return function
        FunctionParser funcParser = new FunctionParser();
        function = funcParser.parseFunction(code);

        // set name of user function
        // remember: the name of the called function could be different compared
        // to the function's name inside the m-file
        function.setName(functionName);
        
        cacheFunction(function);
 
        ErrorLogger.debugLine("MFileWebLoader: finished webloading >" + functionName + ".m<");

        return function;
     }
    
    
    /** set caching of p-file to on of off
     * 
     * @param pFileCaching  true= caching of p-files on; false: caching of p-files off
     */
    public void setPFileCaching(boolean pFileCaching)
    {
        pFileCachingEnabledB = pFileCaching;
    }

    /** return whether of not caching of p-files is enabled of not 
     * 
     * @return status of caching p-files
     */
    public boolean getPFileCaching()
    {
        return pFileCachingEnabledB;
    }
    
    public void checkAndRehashTimeStamps() {
      //TODO: Add timestamp checks
    }
}
