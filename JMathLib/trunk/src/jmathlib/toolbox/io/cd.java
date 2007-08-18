package jmathlib.toolbox.io;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.*;
import java.io.*;

/**An external function for changing to another directory         */
public class cd extends ExternalFunction
{

    /* @param operands[0] string which specifies the directory               *
     *   to change to (optional).                                            *
	 *   If invoked with now paramenter the current directory is returned.   */
	public OperandToken evaluate(Token[] operands)
	{

		String path=".";

		// at least one operand
        if (getNArgIn(operands) != 1)
			throwMathLibException("cd: number of arguments != 1");

		// check if a directory is specified
		if ((operands[0] instanceof CharToken)) 
		{
			path = ((CharToken)operands[0]).toString();
		}
		else
        	throwMathLibException("cd: argument must be a string");

		try
		{
			File dir = new File(getWorkingDirectory(), path);

			//getInterpreter().displayText("canonical path = "+dir.getCanonicalPath());		

			if (dir.isDirectory())
			{
				setWorkingDirectory(dir);		

				if (getNoOfLeftHandArguments()==1)
					return new CharToken(dir.getCanonicalPath());
				else if (operands[0] == null || operands[0].toString().equals(""))
					getInterpreter().displayText(getWorkingDirectory().getCanonicalPath());

			}
		}
		catch (Exception e)
		{
			ErrorLogger.debugLine("cd: IO exception");
		}		    

		return null;		

	} // end eval
}

/*
@GROUP
IO
@SYNTAX
cd(directory)
@DOC
Sets the working directory to directory.
@EXAMPLES
<programlisting>
cd("C:\barfoo");
</programlisting>
@SEE
dir, cd
*/

