package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.Errors;

/**An external function for displaying error messages
aborts the current function being processed*/
public class usage extends ExternalFunction
{
	/**write operand to main display then abort processing
	@param operand[0] = error messages to display*/
	public OperandToken evaluate(Token[] operands)
	{	
        Errors.throwUsageException(operands[0].toString());
		//Errors.throwMathLibException(ERR_USER_ERROR, new Object[] {operands[0]});
		return  null;
	}
}

/*
@GROUP
system
@SYNTAX
USAGE(message)
@DOC
Used within script files to display the paramaters for the script.
@NOTES
@EXAMPLES
Usage("Function(paramaters)")
@SEE
error, warning
*/

