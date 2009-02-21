package jmathlib.toolbox.io;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

import java.io.*;

/**An external function used to execute an external program
in a seperate process*/
public class systemcommand extends ExternalFunction
{
	/**execute a program
	@param operands[0] = filename
	@param operands[n] = arguments
	@return 1*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		String[] cmdarray = new String[operands.length];
		
        if (getNArgIn(operands) < 1)
			throwMathLibException("SystemCommand: number of arguments > 0");

        
        for(int index = 0; index < operands.length; index++)
		{
			cmdarray[index] = operands[index].toString();
		}
		
		try
		{
			Runtime.getRuntime().exec(cmdarray);
		}
		catch(IOException exception)
		{
			throwMathLibException("SystemCommand");			
		}
		
		return null;
	}
}

/*
@GROUP
IO
@SYNTAX
systemcommand(command name)
@DOC
A function for running external commands
@EXAMPLES
<programlisting>
systemcommand("EMACS")
</programlisting>
@SEE
runfile
*/

