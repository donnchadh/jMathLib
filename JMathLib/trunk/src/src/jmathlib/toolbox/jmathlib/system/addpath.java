package jmathlib.toolbox.jmathlib.system;

import java.io.File;
import jmathlib.core.functions.FileFunctionLoader;
import jmathlib.core.functions.FunctionLoader;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;


/**External function to add an item to the search path*/
public class addpath extends ExternalFunction
{
    /**adds an item to the search path
    @param operands[0] = item to add*/
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {
        if (getNArgIn(operands)!=1)
            throwMathLibException("addpath: number of arguments != 1");

        boolean prepend = true;
        
		for(int index = 0; index < operands.length; index++)
		{
            
            // check if operand is of type char token
            if (!(operands[index] instanceof CharToken))
                throwMathLibException("addpath: parameter "+index+" is not a char array");
            
            String path = operands[index].toString();
            
            if(path.equalsIgnoreCase("end") || path.equals("1"))
                prepend = false;
            else if(path.equalsIgnoreCase("begin") || path.equals("0"))
                prepend = true;
        }
               
		for(int index = 0; index < operands.length; index++)
		{
            String path = operands[index].toString();
            if(!(path.equalsIgnoreCase("end") || path.equals("1") || path.equalsIgnoreCase("begin") || path.equals("0")))
            {
                FunctionLoader loader = new FileFunctionLoader(new File(path), true);
                if (!prepend)
                    globals.getFunctionManager().addFunctionLoader(loader);
                else globals.getFunctionManager().addFunctionLoaderAt(0, loader);
            }
        }
            
        return DoubleNumberToken.one;
    }
}

/*
@GROUP
system
@SYNTAX
addpath(path)
@DOC
Adds path to the current search path.
@NOTES
@EXAMPLES
addpath("../newpath")
@SEE
path, rmpath
*/

