package jmathlib.toolbox.jmathlib.system;

/* This file is part or MATHLIB 


   Author: Stefan Mueller 2002/03/31
*/

// ToDO: do not include empty directories in the list
//       relative path names

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.interpreter.*;
import jmathlib.core.functions.FunctionLoader;
import jmathlib.core.functions.FileFunctionLoader;
import jmathlib.core.functions.ExternalFunction;
import java.util.Vector;
import java.io.*;

/**An external function for creating a filelist used by the class
   loader for java applets      */
public class createFunctionsList extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands)
	{

        Vector pathVector = new Vector();
        
        for (int i=0;i<getFunctionManager().getFunctionLoaderCount();i++) 
        {

            FunctionLoader loader = getFunctionManager().getFunctionLoader(i);
            
            if (loader instanceof FileFunctionLoader) 
            {
                FileFunctionLoader ffl = (FileFunctionLoader)loader;
                
                for (int pathIdx=0;pathIdx<ffl.getPathCount();pathIdx++) 
                {
                    File path = ffl.getPath(pathIdx);
                        
                    pathVector.add(path.toString());                    
                }                                        
            }
        }        
        
        
        int    size       = pathVector.size();
        String path       = "";
        String line       = "";

        try 
        {
			// read current user directory from system properties
            String userDir = System.getProperty("user.dir");
            
            // create file for class, m files and images
            File funcFile = new File(userDir,
                                     //getWorkingDirectory(),
			                         File.separator + "bin" +
                                     File.separator + "jmathlib" +
                                     File.separator + "webFunctionsList.dat");
			ErrorLogger.debugLine("working directory: "+userDir);
 
			BufferedWriter outWriter = new BufferedWriter( new FileWriter(funcFile));
	        
            /* The first line of the file is a comment */
            line = "# created with createFunctionsList.java";
            outWriter.write(line, 0, line.length());
			outWriter.newLine();
            line = "# This is a generated file. DO NOT EDIT!";
            outWriter.write(line, 0, line.length());
			outWriter.newLine();
            
	        ErrorLogger.debugLine("funcFile ="+funcFile.toString());
	        
	        for(int n = 0; n < size; n++)
	        {
	            path = (String)pathVector.elementAt(n);
	            ErrorLogger.debugLine("path = "+path);
	            
	           	File dir       = new File(path);
				String[] files = dir.list();

                // do not use empty directories
                if (files != null)
                {
                    for(int fileNo = 0; (fileNo < files.length); fileNo++)
                    {
                        line = path + "/" + files[fileNo];
                    
                        // use unix-style file separator as default
                        line = line.replace('\\', '/'); 

                        // do not put CVS directories and files into functions list
                        if (!line.endsWith("/CVS")            &&
                            !line.endsWith("/CVS/Entries")    &&
                            !line.endsWith("/CVS/Root")       &&
                            !line.endsWith("/CVS/Repository") &&
                            !line.contains(".svn")              )
                        {
                            // remove preceding absolute path
                            line = line.substring((userDir+"/bin/").length());

                            ErrorLogger.debugLine("path = "+ line);

                            // write relative path and filename to dat-file
                            outWriter.write(line, 0, line.length());
                            outWriter.newLine();

                        }

                    }
                }
	        }
			outWriter.close();

        }
        catch (Exception e)
        {
            ErrorLogger.debugLine("createFunctionsList");
            e.printStackTrace();
        }
        return DoubleNumberToken.one;		

	} // end evaluate
}

/*
@GROUP
system
@SYNTAX
createfunctionslist()
@DOC
Creates a file called webFunctionList.dat containing the name 
of all external functions.
This functions is used when running JMathLib as an applet.
@NOTES
@EXAMPLES
createfunctionslist()
@SEE
path, setpath, rmpath
*/

