package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.functions.FunctionLoader;
import jmathlib.core.functions.FileFunctionLoader;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import java.io.File;

/**External function to remove an item from the search path*/
public class rmpath extends ExternalFunction
{
    /**removes an item from the search path
    @param operands[0] = item to remove*/
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {
        if (getNArgIn(operands)!=1)
            throwMathLibException("rmpath: number of arguments != 1");
        
        if ((operands[0] instanceof CharToken))
            throwMathLibException("rmpath: works only on char arrays");

        File path = new File(((CharToken)operands[0]).toString());
        
        for (int i=0;i<globals.getFunctionManager().getFunctionLoaderCount();i++) 
        {
            FunctionLoader loader = globals.getFunctionManager().getFunctionLoader(i);
            
            if (loader instanceof FileFunctionLoader) 
            {
                FileFunctionLoader ffl = (FileFunctionLoader)loader;
                                    
                if (ffl.getBaseDirectory().compareTo(path) == 0) 
                {
                    globals.getFunctionManager().removeFunctionLoader(loader);
                    break;
                }
            }
        }
        
        return DoubleNumberToken.one;
            
    }
}

/*
@GROUP
system
@SYNTAX
rmpath(path)
@DOC
Removes path from the current search path
@NOTES
Using rmpath will stop the system from finding any external functions
and M-files stored within the path.
@EXAMPLES
rmpath("./Functions/General")
@SEE
addpath, path
*/

