package jmathlib.toolbox.io;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

import java.io.*;

public class ishidden extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

        if (getNArgIn(operands) != 1)
			throwMathLibException("isthidden: number of arguments != 1");

		if (!(operands[0] instanceof CharToken)) 
            throwMathLibException("ishidden: argument must be a string");

        String name = ((CharToken)operands[0]).toString();

		try
		{
			File file = new File(globals.getWorkingDirectory(), name);

            if (!file.exists())
                throwMathLibException("ishidden: file does not exist");
            
			if (file.isHidden())
			    return new DoubleNumberToken(1);
            else
                return new DoubleNumberToken(0);
		}
		catch (Exception e)
		{
            throwMathLibException("ishidden: IO exception");
		}		    

		return null;		

	} // end eval
}

/*
@GROUP
IO
@SYNTAX
ishidden(filename)
@DOC
Check the file attribute of filename and returns if the file is a hidden file.
@EXAMPLES
<programlisting>
ishidden("bar.txt");
</programlisting>
@NOTES
@SEE
cd, createnewfile, dir, exist, mkdir, rmdir, delete, isfile, isdirectory, lastmodified
*/

