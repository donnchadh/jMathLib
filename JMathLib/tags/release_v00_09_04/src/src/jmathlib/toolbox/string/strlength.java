package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.interpreter.*;


/**An external function for returning the length of a string*/
public class strlength extends ExternalFunction
{
	/**Calculate the length of the string
	@param operands[0] the string to get the length for*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		OperandToken result = null;

        if (getNArgIn(operands) !=1)
            throwMathLibException("strlength: number of arguments !=1");
		
		if(operands[0] instanceof CharToken)
		{
			int length = ((CharToken)operands[0]).toString().length();
			result = new DoubleNumberToken(length);
		}
		else
			Errors.throwMathLibException(ERR_INVALID_PARAMETER, new Object[] {"CharToken", operands[0].getClass().getName()});
		
		return result;

	}
}

/*
@GROUP
char
@SYNTAX
length=STRLENGTH(string)
@DOC
Returns the length of a string.
@NOTES
@EXAMPLES
STRLENGTH("HELLO WORLD") = 11
@SEE
*/

