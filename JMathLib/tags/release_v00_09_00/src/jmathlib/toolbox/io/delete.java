package jmathlib.toolbox.io;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import java.io.*;

public class delete extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 1)
			throwMathLibException("delete: number of arguments != 1");

		if (!(operands[0] instanceof CharToken)) 
            throwMathLibException("delte: argument must be a string");

        String name = ((CharToken)operands[0]).toString();

        File file = null;
        
		try
		{
			file = new File(getWorkingDirectory(),name);
		}
		catch (Exception e)
		{
            throwMathLibException("delete: exception");
		}		    
        
		if (!file.delete())
                throwMathLibException("delete: did not work");

		return null;		

	} // end eval
}

/*
@GROUP
IO
@SYNTAX
delete(filename)
@DOC
delete a file
@EXAMPLES
<programlisting>
delete("bar.txt");
</programlisting>
@SEE
cd, createnewfile, dir, exist, mkdir, rmdir, isfile, isdirectory, ishidden, lastmodified
*/

