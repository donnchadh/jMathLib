package jmathlib.toolbox.io;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import java.io.*;

public class createnewfile extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 1)
			throwMathLibException("createnewfile: number of arguments != 1");

		if (!(operands[0] instanceof CharToken)) 
            throwMathLibException("createnewfile: argument must be a string");

        String name = ((CharToken)operands[0]).toString();

        File file=null;

        try
		{
			file = new File(getWorkingDirectory(),name);

            if(!file.createNewFile())
                throwMathLibException("create new file: did not work");
		}
		catch (Exception e)
		{
            throwMathLibException("create new file: exception");
		}		    

		return null;		

	} // end eval
}

/*
@GROUP
IO
@SYNTAX
createnewfile(filename)
@DOC
use this script to create a new file.
@EXAMPLES
<programlisting>
createnewfile("bar.txt");
</programlisting>
@SEE
cd, dir, exist, mkdir, rmdir, delete, isfile, isdirectory, ishidden, lastmodified
*/

