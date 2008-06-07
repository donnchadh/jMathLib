package jmathlib.toolbox.io;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import java.io.*;

public class mkdir extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 1)
			throwMathLibException("mkdir: number of arguments != 1");

		if (!(operands[0] instanceof CharToken)) 
            throwMathLibException("mkdir: argument must be a string");

        String name = ((CharToken)operands[0]).toString();

        File file=null;
		try
		{
			file = new File(getWorkingDirectory(),name);
		}
		catch (Exception e)
		{
            throwMathLibException("mdkir: IO exception");
		}		    

        if(!file.mkdir())
            throwMathLibException("mkdir: did not work");

		return null;		

	} // end eval
}

/*
@GROUP
IO
@SYNTAX
mkdir(directory)
@DOC
Creates a new directory.
@EXAMPLES
<programlisting>
mkdir("bar");
</programlisting>
@SEE
cd, createnewfile, dir, exist, rmdir, delete, isfile, isdirectory, ishidden, lastmodified
*/

