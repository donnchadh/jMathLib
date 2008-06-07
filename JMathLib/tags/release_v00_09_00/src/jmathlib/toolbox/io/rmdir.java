package jmathlib.toolbox.io;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import java.io.*;

/**An external function for changing to another directory         */
public class rmdir extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 1)
			throwMathLibException("rmdir: number of arguments != 1");

		if (!(operands[0] instanceof CharToken)) 
            throwMathLibException("rmdir: argument must be a string");

        String name = ((CharToken)operands[0]).toString();

        File file = null;
        
		try
		{
			file = new File(getWorkingDirectory(),name);
		}
		catch (Exception e)
		{
            throwMathLibException("rmdir: IO exception");
		}		    

        // check if file is really a directory and not a file
        if (!file.isDirectory())
            throwMathLibException("rmdir: is not a directory");
            
        // delete directroy
        if (!file.delete())
                throwMathLibException("rmdir: did not work");

		return null;		

	} // end eval
}

/*
@GROUP
IO
@SYNTAX
rmdir(directory)
@DOC
Deletes a given directory. 
@EXAMPLES
<programlisting>
rmdir("bar");
</programlisting>
@NOTES
Directory must be empty in order to be removed.
@SEE
cd, createnewfile, dir, exist, mkdir, delete, isfile, isdirectory, ishidden, lastmodified
*/

