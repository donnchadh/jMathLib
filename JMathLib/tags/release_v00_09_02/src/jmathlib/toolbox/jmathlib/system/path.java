package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.functions.FunctionLoader;
import jmathlib.core.functions.FileFunctionLoader;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.tokens.OperandToken;

import java.io.File;

/**External function to display the current search path*/
public class path extends ExternalFunction
{
    /**return the search path as a string token*/
    public OperandToken evaluate(Token[] operands)
    {
        StringBuffer pathString = new StringBuffer();
        
        for (int i=0; i<getFunctionManager().getFunctionLoaderCount(); i++) 
        {
            // get one of the function loaders
            FunctionLoader loader = getFunctionManager().getFunctionLoader(i);
            
            // check if loader is loading files
            if (loader instanceof FileFunctionLoader) 
            {
                FileFunctionLoader ffl = (FileFunctionLoader)loader;
                
                // get paths from current function loader
                for (int pathIdx=0; pathIdx<ffl.getPathCount(); pathIdx++) 
                {
                    File path = ffl.getPath(pathIdx);
                    
                    pathString.append(path.toString() + "\n");                    
                }
            }
            else
                throwMathLibException("path: wrong type of loader");
        }

        return new CharToken(new String(pathString));
    }
}

/*
@GROUP
system
@SYNTAX
path()
@DOC
Lists the current search path.
@NOTES
@EXAMPLES
<programlisting>
path()
</programlisting>
@SEE
addpath, rmpath, createfunctionslist
*/

