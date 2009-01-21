package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.CharToken;

/**An external function for creating random numbers*/
public class lower extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		// one operand
		if (operands[0]==null)  return null;

		// operand must be a string
		if (!(operands[0] instanceof CharToken)) return null;

		String data = ((CharToken)operands[0]).getValue().toLowerCase();
		
		return new CharToken(data);		
	}
}

/*
@GROUP
char
@SYNTAX
answer=FINDSTR(string1, string2)
@DOC
Finds all occcurences of the shorter string within the longer
@NOTES
.
@EXAMPLES
<programlisting>
.
</programlisting>
@SEE
strfind, upper
*/

