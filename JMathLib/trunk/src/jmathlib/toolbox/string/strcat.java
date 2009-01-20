package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.CharToken;

/**An external function for concatenating strings*/
public class strcat extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		String result = "";
		
		for(int index = 0; index < operands.length; index++)
		{
			result += operands[index].toString().trim();
		}
		
		return new CharToken(result);
	}
}

/*
@GROUP
char
@SYNTAX
STRCAT(string1, string2, ...)
@DOC
Concatenates a set of strings together.
@NOTES
@EXAMPLES
STRCAT("Hello", "World")
HelloWorld
@SEE
strvcat
*/

