package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.CharToken;

/**An external function for creating random numbers*/
public class upper extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

		// one operand
		if (operands[0]==null)  return null;

		// operand must be a string
		if (!(operands[0] instanceof CharToken)) return null;

		String data = ((CharToken)operands[0]).getValue().toUpperCase();
		
		return new CharToken(data);		
	}
}

/*
@GROUP
char
@SYNTAX
answer = upper(string)
@DOC
Converts a string to upper case.
@NOTES
.
@EXAMPLES
.
@SEE
lower
*/

