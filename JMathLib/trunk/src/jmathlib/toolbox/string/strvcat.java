package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.tokens.MatrixToken;

/**An external function for concatenating strings into
a vertical vector*/
public class strvcat extends ExternalFunction
{
	/**Concatenates strings into a vertical vector
	@param operands[n] = the strings to concatenate*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		OperandToken[][] values = new OperandToken[operands.length][1];
		
		for(int index = 0; index < operands.length; index++)
		{
			if(operands[index] instanceof CharToken)
				values[index][0] = ((OperandToken)operands[index]);
			else
				values[index][0] = new CharToken(operands[index].toString());
		}
		
		return new MatrixToken(values);
	}
}

/*
@GROUP
char
@SYNTAX
STRVCAT(string1, string2, ...)
@DOC
A function to join a group of strings into a vertical matrix
@NOTES
@EXAMPLES
STRVCAT("a", "list", "of", "strings")
@SEE
strcat
*/

